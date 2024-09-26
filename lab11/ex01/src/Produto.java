package ex01.src;
import java.util.HashMap;
import java.util.Random;

public class Produto {
    private int id;
    private String description;
    private double initial_price;
    private ProdutoEstado state; 
    private double auction_start;

    private double auction_time;
    private double auction_price;

    private Gestor manager;

    private HashMap<Cliente, Double> bidders;

    public Produto(String description, double initial_price, Gestor manager) {
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.description = description;
        this.initial_price = initial_price;
        this.state = ProdutoEstado.STOCK;
        this.auction_price = initial_price;
        this.manager = manager;
        this.bidders = new HashMap<Cliente, Double>();
    }    
    
    public int getId() {
        return this.id;
    }

    public ProdutoEstado getState() {
        return this.state;
    }

    public double getAuctionPrice() {
        return this.auction_price;
    }

    public String getDescription() {
        return this.description;
    }

    public double getInitialPrice() {
        return this.initial_price;
    }

    public void startAuction(double auction_time) {
        if (this.state == ProdutoEstado.STOCK) {
            this.state = ProdutoEstado.LEILAO;
            this.auction_start = System.currentTimeMillis();
            this.auction_time = auction_time;
            this.manager.addProductAuction(this);
            this.manager.update("Product " + this.description + " is now in auction");
        }
    }

    public int bid(Cliente client, double price) {
        if (this.state == ProdutoEstado.LEILAO) {
            if (auctionExpired()) {
                this.endAuction();
                this.manager.update("Product " + this.description + " auction has ended");
                return -1;
            }
            else {
                if (price > this.auction_price) {
                    this.auction_price = price;
                    this.bidders.put(client, price);
                    this.manager.update("Client " + client.getName() + " bid " + price + " euros for product " + this.description);
                    return 0;
                }
                else {
                    this.manager.update("Invalid bid. Client " + client.getName() + " bid " + price + " euros for product " + this.description + " but it was lower than the current price");
                    return 1;
                }
            }
        }
        this.manager.update("Product " + this.description + " is not in auction");
        return 1;
    }

    public boolean auctionExpired() {
        if (this.state == ProdutoEstado.LEILAO) {
            if (System.currentTimeMillis() - this.auction_start > this.auction_time) {
                return true;
            }
        }
        return false;
    }

    public void endAuction() {
        if (this.state != ProdutoEstado.LEILAO) {
            manager.update(description + " is not in auction");
            return;
        }
        if (auctionExpired()) {
            if (this.bidders.size() > 0) {
                Cliente winner = null;
                double max = 0;
                for (Cliente client : this.bidders.keySet()) {
                    if (this.bidders.get(client) > max) {
                        max = this.bidders.get(client);
                        winner = client;
                    }
                }
                if (winner == null) {
                    this.state = ProdutoEstado.STOCK;
                }
                else {
                    this.state = ProdutoEstado.VENDAS;
                    manager.update("Product " + this.description + " was sold to client " + winner.getName() + " for " + max + " euros");
                    this.manager.addProductSold(this);
                }
            }
            else {
                this.state = ProdutoEstado.STOCK;
                manager.removeProductAuction(this);
            }
        }
    }

    public String toString() {
        return "Product: " + this.description + " - ID: " + this.id + " - State: " + this.state + " - Initial Price: " + this.initial_price + " - Auction Price: " + this.auction_price;
    }


}

