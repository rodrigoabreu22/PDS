public class Cliente implements Observer{
    private String name;
    private Gestor manager;


    private Cliente(String name) {
        this.name = name;

    }

    public static Cliente createClient(String name) {
        return new Cliente(name);
    }

    public String getName() {
        return this.name;
    }

    public void setGestor(Gestor manager) {
        this.manager = manager;
    }

    public Gestor getGestor(){
        return this.manager;
    }

    public void bid(Produto product, double money) {
        manager.bid(product, this, money);
    }

    public void auctionProducts(){
        for (Produto product : manager.getAuctionProducts()) {
            System.out.println(product.getDescription());
        }
    }

    @Override
    public void bidNotifier(Produto product, Cliente client, double money) {
        System.out.println(String.format("Client (%s) bid %s€ on product (%.2f)", client.getName(), money, product.getDescription()));
    }

    @Override
    public void auctionResultNotifier(Produto product, Cliente winner) {
        System.out.println(String.format("Product (%s) sold to %s for %.2f€", product.getDescription(), winner.getName(), product.getHighestBid()));
    }

}
