package ex03.src;

public class Colleague {
    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public void sendMessage() {
        mediator.notify(this, "Hello, world!");
    }

    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
