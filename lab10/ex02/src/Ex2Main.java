package ex02.src;

import java.util.ArrayList;
import java.util.List;

public class Ex2Main {
    public static void main(String[] args) {
        List<String> requests = new ArrayList<>();
        requests.add("veggie burger");
        requests.add("Pasta Carbonara");
        requests.add("pizza");
        requests.add("salad with tuna");
        requests.add("sushi");
        requests.add("ice cream and waffles dessert");

        ChefHandler chef = new SushiChef().setNextChef(
            new PastaChef().setNextChef(
                new BurgerChef().setNextChef(
                    new PizzaChef().setNextChef(
                        new DessertChef()))));
                        
        for (String request : requests) {
            System.out.println("\nCan I please get a " + request + "?");
            chef.handleRequest(request);
        }
    }
}
