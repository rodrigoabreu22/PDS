package ex03.src;

import java.util.ArrayList;

/**
 * The `Caixa` class represents a box that can contain products. It implements the `ProdutoInterface` interface.
 */
public class Caixa implements ProdutoInterface{
    private String name;
    private double weight;
    private ArrayList<ProdutoInterface> produtos;

    /**
     * Constructs a `Caixa` object with the specified name and weight.
     * @param name The name of the box.
     * @param weight The weight of the box.
     */
    public Caixa(String name, double weight){
        this.name=name;
        this.weight=weight;
        this.produtos = new ArrayList<>();
    }

    /**
     * Returns the name of the box.
     * @return The name of the box.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Calculates and returns the total weight of the box and its contents.
     * @return The total weight of the box and its contents.
     */
    public double weight(){
        double total_weight=this.weight;
        for (int i=0;i< this.produtos.size(); i++){
            total_weight+=this.produtos.get(i).weight();
        }
        return total_weight;
    }

    /**
     * Adds a product to the box.
     * @param produto The product to be added.
     */
    public void add(ProdutoInterface produto){
        this.produtos.add(produto);
    }

    /**
     * Prints the box and its contents.
     */
    public void draw(){
        System.out.println(this);
    }

    /**
     * Returns the weight of the box.
     * @return The weight of the box.
     */
    public double boxWeight(){
        return this.weight;
    }

    /**
     * Returns a string representation of the box and its contents.
     * @return A string representation of the box and its contents.
     */
    public String toString() {
        String output = "* Caixa \'" + this.name + "\' [ Weight: " + this.boxWeight() + " ; Total: " + this.weight() + "]\n";
        for (ProdutoInterface produto : produtos) {
            if (produto instanceof Caixa) {
                output += "\t" + produto.toString().replaceAll("\n", "\n\t") + "\n";
            } else {
                output += "\t" + produto + "\n";
            }
        }
        //removes empty lines from the output
        output=output.substring(0,output.length()-1);
        return output;
    }
}
