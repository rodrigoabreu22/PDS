import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ProdutoLeilao implements ProdutoEstado, Observer{
    private Produto product;

    private double highest_bid;
    private Cliente highest_bidder;

    private final Integer auction_time;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledTask;

    private ProdutoLeilao(Produto product, double starting_price, Integer auction_time) {
        this.product = product;
        this.highest_bid = starting_price;
        this.highest_bidder = null;
        this.auction_time = auction_time;
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduleNextState();
    }

    public static ProdutoLeilao create(Produto product, double starting_price, int auction_time) {
        return new ProdutoLeilao(product, starting_price, auction_time);
    }

    private void scheduleNextState() {
        if (scheduledTask != null && !scheduledTask.isDone()) {
            scheduledTask.cancel(false);
        }
        scheduledTask = this.scheduler.schedule(this::nextEstado, auction_time, TimeUnit.SECONDS);
    }

    @Override
    public void nextEstado() {
        auctionResultNotifier(product, highest_bidder);
        scheduler.shutdown(); // Stop the scheduled task
        if (highest_bidder == null) {
            product.setState(ProdutoStock.create(this.product));
            return;
        }
        product.auctionResultNotifier(highest_bidder);
        product.setState(ProdutoVendas.create(this.product, highest_bidder, highest_bid));
    }

    @Override
    public void bid(Cliente client, double money) {
        if (money > highest_bid) {
            highest_bid = money;
            highest_bidder = client;
        }
    }

    @Override
    public String Estado() {
        return "Produto em leilão. Valor atual" + highest_bid + "€. Cliente: " + highest_bidder.getName() + ".";
    }

    @Override
    public double HighestBid() {
        return highest_bid;
    }

    @Override
    public void bidNotifier(Produto product , Cliente highest_bidder, double highest_bid){
        product.getManager().bidNotifier(product, highest_bidder, highest_bid);
    }

    @Override
    public void auctionResultNotifier( Produto product , Cliente highest_bidder){
        product.getManager().auctionResultNotifier(product, highest_bidder);
    }
}
