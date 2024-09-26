public interface ProdutoEstado {
    public void nextEstado();
    public void  bid(Cliente client, double money);
    public String Estado();
    public double HighestBid();
}
