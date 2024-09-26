public class ProdutoVendas implements ProdutoEstado{
    private Produto product;
    private Cliente auction_winner;
    private double auction_price;
    
    private ProdutoVendas(Produto product, Cliente auction_winner, double auction_price) {
        this.product = product;
        this.auction_winner = auction_winner;
        this.auction_price = auction_price;
    }

    public static ProdutoVendas create(Produto product, Cliente auction_winner, double auction_price) {
        return new ProdutoVendas(product, auction_winner, auction_price);
    }

    @Override
    public void nextEstado() {
        System.out.println(String.format("Produto (%s) vendido a %s por %s€", product.getDescription(), auction_winner.getName(), auction_price));
    }
    
    @Override
    public void bid(Cliente client, double money) {
        System.out.println(String.format("Produto (%s) vendido a %s por %s€", product.getDescription(), auction_winner.getName(), auction_price));
    }

    @Override
    public String Estado() {
        return String.format("Produto vendido a %s por %s€", auction_winner.getName(), auction_price);
    }

    @Override
    public double HighestBid() {
        return 0;
    }
}
