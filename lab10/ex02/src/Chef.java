package ex02.src;

import java.util.Random;

public abstract class Chef implements ChefHandler {
    protected ChefHandler nextChef;

    @Override
    public ChefHandler setNextChef(ChefHandler next) {
        this.nextChef = next;
        return this;
    }

    @Override
    public void handleRequest(String request) {
        if (nextChef != null) {
            nextChef.handleRequest(request);
        }
        else {
            System.out.println("We're sorry but that request can't be satisfied by our service!");
        }
    }

    public void cook(String request) {
        System.out.println(getClass().getSimpleName() + ": Starting to cook " + request + ".Out in " + randomTime() + " minutes.");
    }

    private int randomTime() {
        Random rand = new Random();
        return rand.nextInt(30) + 10;
    }
}
