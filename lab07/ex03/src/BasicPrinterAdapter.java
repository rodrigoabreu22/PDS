package ex03.src;

import java.util.List;
import java.util.ArrayList;

public class BasicPrinterAdapter implements AdvancedPrinterInterface {
    private BasicPrinter printer;
    private List<PrintJob> printQueue;

    public BasicPrinterAdapter(BasicPrinter printer) {
        this.printer = printer;
        this.printQueue = new ArrayList<>();
    }

    @Override
    public int print(Document doc) {
        System.out.println("Spooling 1 document.");
        PrintJob job = new PrintJob(doc);
        printQueue.add(job);
        if (this.printer.print(doc.getContent())) {
            printQueue.remove(job);
        }
        return job.getJobID();
    }
    
    @Override
    public List<Integer> print(List<Document> docs) {
        System.out.println("Spooling " + docs.size() + " documents.");
        List<Integer> jobs = new ArrayList<>();
        for (Document doc : docs) {
            PrintJob job = new PrintJob(doc);
            printQueue.add(job);
            jobs.add(job.getJobID());
            if (this.printer.print(doc.getContent())) {
                printQueue.remove(job);
            }
        }
        return jobs;
    }

    @Override
    public void showQueuedJobs() {
        System.out.println("Spooled jobs:");
        for (PrintJob job : printQueue) {
            System.out.println("    * " + job);
        }
        System.out.println();
    }

    @Override
    public boolean cancelJob(int job) {
        for (PrintJob j : printQueue) {
            if (j.getJobID() == job) {
                System.out.println("Cancelled " + j);
                printQueue.remove(j);
                return true;
            }
        }
        return false;
    }

    @Override
    public void cancelAll() {
        System.out.println("Job rejected by spool: service shutting down?");
        printQueue.clear();
    }
}
