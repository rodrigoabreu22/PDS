public interface Observer {
    public void bidNotifier(Produto product, Cliente client, double money);
    public void auctionResultNotifier(Produto product, Cliente client);
}
