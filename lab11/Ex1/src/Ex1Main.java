import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Ex1Main {
    // Generating the objects
    private final ScheduledExecutorService executor;
    private final Random random;
    private final Gestor g;
    private final Cliente c1, c2, c3;

    public Ex1Main() {
        executor = Executors.newScheduledThreadPool(3);
        random = new Random();
        g = Gestor.createGestor("Elon Musk");
        c1 = Cliente.createClient("João");
        c2 = Cliente.createClient("Maria");
        c3 = Cliente.createClient("Pedro");

        // Starting the auction
        c1.setGestor(g);
        c2.setGestor(g);
        c3.setGestor(g);

        // Schedule product addition with an initial delay
        executor.schedule(addRandomProduct(), 1, TimeUnit.SECONDS);
        // Schedule bids with an initial delay to ensure products are added first
        executor.schedule(makeRandomBid(c1), 15, TimeUnit.SECONDS);
        executor.schedule(makeRandomBid(c2), 15, TimeUnit.SECONDS);
        executor.schedule(makeRandomBid(c3), 15, TimeUnit.SECONDS);
    }

    Runnable addRandomProduct() {
        return () -> {
            String productName = "Product " + (random.nextInt(100) + 1); // Random product name
            Double basePrice = 50 + random.nextDouble() * 50; // Random base price between 50 and 100
            Produto product = Produto.createProduct(productName, basePrice);
            product.setManager(g);
            g.addProduct(product);

            long delay = 1 + random.nextInt(10); // Random delay between 1 and 10 seconds
            executor.schedule(addRandomProduct(), delay, TimeUnit.SECONDS);
        };
    }

    Runnable makeRandomBid(Cliente client) {
        return () -> {
            try {
                Object[] auctionList = client.getGestor().getAuctionProducts().toArray();
                if (auctionList.length == 0) {
                    long delay = 1 + random.nextInt(10); // Random delay between 1 and 10 seconds
                    executor.schedule(makeRandomBid(client), delay, TimeUnit.SECONDS); // No products to bid on
                    return;
                }

                int randomIndex = random.nextInt(auctionList.length);
                Produto product = (Produto) auctionList[randomIndex]; // Get a random product from the current bidding list
                double currentHighestBid = g.getHighestBid(product);
                double bidIncrement = 1 + random.nextDouble() * 10; // Random increment between 1 and 10
                double newBid = currentHighestBid + bidIncrement;

                // Probability of placing a bid decreases as the price increases
                double basePrice = product.getInitialPrice();
                double bidProbability = Math.exp(-(newBid - basePrice) / 50.0); // Adjust the denominator to change the rate of decrease
                if (random.nextDouble() < bidProbability) {
                    client.bid(product, newBid);
                }

                long delay = 1 + random.nextInt(10); // Random delay between 1 and 10 seconds
                executor.schedule(makeRandomBid(client), delay, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        new Ex1Main();
    }
}
