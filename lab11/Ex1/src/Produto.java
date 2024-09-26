import java.util.Random;

public class Produto implements Comparable<Produto>{
    private int id;
    private String description;
    private double initial_price;
    private ProdutoEstado state; 
    private Gestor manager;

    private Produto(String description, Double initial_price) {
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.description = description;
        this.initial_price = initial_price;
        this.state = ProdutoStock.create(this);
    }

    public static Produto createProduct(String description, Double initial_price) {
        return new Produto(description, initial_price);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getInitialPrice() {
        return initial_price;
    }

    public Gestor getManager() {
        return manager;
    }

    public void setManager(Gestor manager){
        this.manager=manager;
    }

    public void setState(ProdutoEstado state) {
        this.state = state;
    }

    public String getState() {
        return this.state.Estado();
    }

    public void startAuction(int auction_time) {
        if (this.getState()=="Produto em Stock") {
            this.setState(ProdutoLeilao.create(this, this.getInitialPrice(), auction_time));
        }
        else {
            System.out.println("Não é possível iniciar leilão");
        }
    }

    public void bid(Cliente client, double money) {
        this.state.bid(client, money);
    }

    public double getHighestBid() {
        return this.state.HighestBid();
    }

    public void auctionResultNotifier(Cliente client) {
        this.manager.bidNotifier(this, client, this.getHighestBid());
    }

    public void bidNotifier(Cliente client, double money) {
        this.manager.bidNotifier(this, client, money);
    }

    @Override
    public String toString() {
        return String.format("Produto (%s) com id %d e preço inicial de %s€", this.description, this.id, this.initial_price);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public int compareTo(Produto o) {
        return this.id - o.getId();
    }
}

