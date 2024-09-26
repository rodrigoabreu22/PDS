package ex03.src;

import java.util.ArrayList;
import java.util.List;

public class PrinterTest {
    
    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AdvancedPrinterInterface p = new AdvancedPrinter();
        BasicPrinter b = new BasicPrinter();
        BasicPrinterAdapter adapter = new BasicPrinterAdapter(b);

        List<Document> docs = new ArrayList<Document>();
        docs.add(new Document("./lab07/Ex3/text1.txt"));
        docs.add(new Document("./lab07/Ex3/text2.txt"));
        docs.add(new Document("./lab07/Ex3/text3.txt"));

        p.print(docs.get(0));   // print first document only
        pause(2000);            // wait for a while

        p.print(docs);
        p.showQueuedJobs();
        pause(4000);            // wait for a while

        p.print(docs);
        p.cancelJob(6);
        p.showQueuedJobs();
        pause(4000);            // wait for a while

        p.print(docs);
        p.cancelAll();
        p.showQueuedJobs();

        pause(2000);            // wait for a while

        System.out.println("\nAdapter test:");

        adapter.print(docs.get(0));   // print first document only

        pause(2000);            // wait for a while

        adapter.print(docs);
        pause(2000);            // wait for a while

        System.exit(0);
    }
}
