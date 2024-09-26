package ex03.src;

/**
 * The Doce class represents a type of product that is a sweet.
 * It implements the ProdutoInterface interface.
 */
public class Doce implements ProdutoInterface{
    private String name;
    private double weight;

    /**
     * Constructs a Doce object with the specified name and weight.
     * 
     * @param name   the name of the sweet
     * @param weight the weight of the sweet
     */
    public Doce(String name, double weight){
        this.name=name;
        this.weight=weight;
    }

    /**
     * Returns the name of the sweet.
     * 
     * @return the name of the sweet
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the weight of the sweet.
     * 
     * @return the weight of the sweet
     */
    public double weight(){
        return this.weight;
    }

    /**
     * Prints the Doce object.
     */
    public void draw(){
        System.out.print(this);
    }

    /**
     * Returns a string representation of the Doce object.
     * 
     * @return a string representation of the Doce object
     */
    public String toString(){
        String output = "Doce: \'" + this.getName() + "\' - Weight : " + this.weight();
        return output;
    }
}
