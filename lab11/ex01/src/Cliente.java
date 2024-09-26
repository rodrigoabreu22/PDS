package ex01.src;
import java.util.ArrayList;

public class Cliente implements Observer{
    private String name;
    private ArrayList<Produto> products_auction;
    private ArrayList<Produto> products_bidded;


    public Cliente(String name) {
        this.name = name;
        this.products_auction = new ArrayList<Produto>();
        this.products_bidded = new ArrayList<Produto>();
    }

    public String getName() {
        return this.name;
    }

    public void addProductAuction(Produto product) {
        this.products_auction.add(product);
    }

    public void addProductBidded(Produto product) {
        this.products_bidded.add(product);
    }

    public void removeProductAuction(Produto product) {
        this.products_auction.remove(product);
    }

    public void removeProductBidded(Produto product) {
        this.products_bidded.remove(product);
    }

    public void checkAuction(){
        System.out.println("Checking for available products in auction for client " + this.name);
        for (Produto product : products_auction) {
            System.out.println("Product: " +  product);
        }
    }
    
    public void bid(Produto product, double price) {
        int res = product.bid(this, price);

        switch (res) {
            case -1:
                if (products_auction.contains(product)) {
                    products_auction.remove(product);
                }
            case 0:
                addProductBidded(product);
            case 1:
                return;

        }
    }

    @Override
        public void update(String message) {
            System.out.println("Client " + this.name + " received message: " + message);
        }

}
