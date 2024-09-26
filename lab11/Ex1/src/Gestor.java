import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Gestor implements Observer{
    private String name;
    private ArrayList<Produto> products_stock;
    private TreeMap<Produto, ArrayList<Cliente>> product_bidders;
    private ArrayList<Produto> products_sold;
    private final ScheduledExecutorService scheduler;

    private Gestor(String name) {
        this.name = name;
        this.products_stock = new ArrayList<Produto>();
        this.product_bidders = new TreeMap<Produto, ArrayList<Cliente>>();
        this.products_sold = new ArrayList<Produto>();
        this.scheduler = Executors.newScheduledThreadPool(5);
        this.scheduler.scheduleAtFixedRate(this::startAuction, 0, 15, TimeUnit.SECONDS);
    }

    public static Gestor createGestor(String name) {
        return new Gestor(name);
    }

    public String getName() {
        return this.name;
    }

    public void startAuction() {
        if (!products_stock.isEmpty()) {
            Produto product = null;
            try {
                product = products_stock.remove(0);
                ArrayList<Cliente> bidders = new ArrayList<>();
                
                product_bidders.put(product, bidders);
                startAuction(product, 10);
            } catch (Exception e) {
                e.printStackTrace();
                if (product != null) {
                    products_stock.add(0, product);
                }
            }
        }
        else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startAuction();
        }
    }

    public void startAuction(Produto product, int auction_time){
        product.startAuction(auction_time);
    }

    public void addProduct(Produto product) {
        products_stock.add(product);
    }

    public void removeProduct(Produto product) {
        products_stock.remove(product);
    }

    public double getHighestBid(Produto product){
        return product.getHighestBid();
    }

    public void bid(Produto product, Cliente client, double money) {
        System.out.println("Client (" + client.getName() + ") bid " + money + "€ on product (" + product.getDescription() + ")");
        if (product_bidders.keySet().contains(product)) {
            if (product.getHighestBid()< money) {
                product.bid(client, money);
                ArrayList<Cliente> bidders = product_bidders.get(product);
                synchronized (bidders){
                    if (!bidders.contains(client)) {
                        bidders.add(client);
                    }
                }
                bidders.add(client);
                product_bidders.put(product, bidders);
                System.out.println("New highest bid on product (" + product.getDescription() + ") by client (" + client.getName() + ")");
            }
            else {
                System.out.println("Invalid bid on product (" + product.getDescription() + ")" + " by client (" + client.getName() + ")");
            }
        }
        else {
            System.out.println("The product is not in auction");
        }
    }  

    public Set<Produto> getAuctionProducts(){
        return this.product_bidders.keySet();
    }

    @Override
    public void bidNotifier(Produto product, Cliente client, double money) {
        for (Cliente c : product_bidders.get(product)) {
            if (!client.equals(c)) {
                c.bidNotifier(product, client, money);
            }
        }
    }

    @Override
    public void auctionResultNotifier(Produto product, Cliente client) {
        for (Cliente c : product_bidders.get(product)) {
            c.auctionResultNotifier(product, client);
        }

        if (client==null){
            products_stock.add(product);
        }
        else{
            products_sold.add(product);
            if (products_sold.size() >= 10) {
                scheduler.shutdown();
            }
        }
        product_bidders.remove(product);
    }

    public String toString() {
        return "Manager "+this.getName();
    }
}
