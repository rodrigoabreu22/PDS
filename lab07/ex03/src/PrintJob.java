package ex03.src;

public class PrintJob implements Runnable {
    private Document doc;
    private int jobID;
    private static int jobCounter = 0;

    public PrintJob(Document doc) {
        this.doc = doc;
        this.jobID = jobCounter++;
    }

    public int getJobID() {
        return this.jobID;
    }

    public void run() {
        System.out.println("Finished " + this);
    }

    @Override
    public String toString() {
        return "job " + this.jobID + ": " + this.doc;
    }
}
