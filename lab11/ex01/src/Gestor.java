package ex01.src;
import java.util.ArrayList;

public class Gestor implements Observer{
    private String name;
    private ArrayList<Produto> products_stock;
    private ArrayList<Produto> products_auction;
    private ArrayList<Produto> products_sold;

    public Gestor(String name) {
        this.name = name;
        this.products_auction = new ArrayList<Produto>();
        this.products_stock = new ArrayList<Produto>();
        this.products_sold = new ArrayList<Produto>();
    }

    public String getName() {
        return this.name;
    }

    public void addProductStock(Produto product) {
        this.products_stock.add(product);
    }

    public void addProductAuction(Produto product) {
        this.products_auction.add(product);
    }

    public void addProductSold(Produto product) {
        this.products_sold.add(product);
        this.products_stock.remove(product);
        this.products_auction.remove(product);
    }

    public void removeProductStock(Produto product) {
        this.products_stock.remove(product);
    }

    public void removeProductAuction(Produto product) {
        this.products_auction.remove(product);
    }

    public void checkAuction(){
        System.out.println("Checking for available products in auction for manager " + this.name);
        for (Produto product : products_auction) {
            System.out.println("Product: " +  product);
        }
    }

    public void checkStock(){
        System.out.println("Checking for available products in stock for manager " + this.name);
        for (Produto product : products_stock) {
            System.out.println("Product: " +  product);
        }
    }

    public void checkSold(){
        System.out.println("Checking for products sold for manager " + this.name);
        for (Produto product : products_sold) {
            System.out.println("Product: " +  product);
        }
    }

    public ArrayList<Produto> getProductsStock() {
        return this.products_stock;
    }

    @Override
    public void update(String message) {
        System.out.println("Manager " + this.getName() + " received an update: \n> " + message);
    }

    public String toString() {
        return "Manager "+this.getName();
    }
}
