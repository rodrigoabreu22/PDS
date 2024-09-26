public class ProdutoStock implements ProdutoEstado{
    private Produto produto;

    private ProdutoStock(Produto product) {
        this.produto = product;
    }

    public static ProdutoStock create(Produto product) {
        return new ProdutoStock(product);
    }

    @Override
    public void nextEstado() {
        this.produto.setState(ProdutoLeilao.create(this.produto, this.produto.getInitialPrice(), 10));
    }

    @Override
    public void bid(Cliente c, double money) {
        System.out.println("Produto não está em leilão");
    }

    @Override
    public String Estado() {
        return "Produto em Stock";
    }

    @Override
    public double HighestBid() {
        return 0;
    }
}
