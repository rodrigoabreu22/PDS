package ex02.src;

public class PizzaChef extends Chef {

    @Override
    public ChefHandler setNextChef(ChefHandler nextChef) {
        return super.setNextChef(nextChef);
    }

    @Override
    public void handleRequest(String request) {
        if (request.toLowerCase().contains("pizza")) {
            this.cook(request);
        } else {
            System.out.println(getClass().getSimpleName() + ": I can't cook that.");
            super.handleRequest(request);
        }
    }
}
