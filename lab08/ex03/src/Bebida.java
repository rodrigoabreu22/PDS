package ex03.src;

/**
 * The `Bebida` class represents a beverage.
 * It implements the `ProdutoInterface` interface.
 */
public class Bebida implements ProdutoInterface{
    private String name;
    private double weight;

    /**
     * Constructs a new Bebida object with the specified name and weight.
     * 
     * @param name   the name of the Bebida
     * @param weight the weight of the Bebida
     */
    public Bebida(String name, double weight){
        this.name=name;
        this.weight=weight;
    }

    /**
     * Returns the name of the Bebida.
     * 
     * @return the name of the Bebida
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the weight of the Bebida.
     * 
     * @return the weight of the Bebida
     */
    public double weight(){
        return this.weight;
    }

    /**
     * Prints the Bebida object.
     */
    public void draw(){
        System.out.print(this);
    }

    /**
     * Returns a string representation of the Bebida object.
     * 
     * @return a string representation of the Bebida object
     */
    public String toString(){
        String output = "Bebida: \'" + this.getName() + "\' - Weight : " + this.weight();
        return output;
    }
}
