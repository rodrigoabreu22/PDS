package ex03.src;

/**
 * The `Conserva` class represents a type of product that is a conserva.
 * It implements the `ProdutoInterface` interface.
 */
public class Conserva implements ProdutoInterface {
    private String name;
    private double weight;

    /**
     * Constructs a new Conserva object with the specified name and weight.
     * 
     * @param name   the name of the Conserva
     * @param weight the weight of the Conserva
     */
    public Conserva(String name, double weight){
        this.name=name;
        this.weight=weight;
    }

    /**
     * Returns the name of the Conserva.
     * 
     * @return the name of the Conserva
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the weight of the Conserva.
     * 
     * @return the weight of the Conserva
     */
    public double weight(){
        return this.weight;
    }

    /**
     * Prints the Conserva object.
     */
    public void draw(){
        System.out.print(this);
    }

    /**
     * Returns a string representation of the Conserva object.
     * 
     * @return a string representation of the Conserva object
     */
    public String toString(){
        String output = "Conserva: \'" + this.getName() + "\' - Weight : " + this.weight();
        return output;
    }
}
