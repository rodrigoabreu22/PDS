package lab05.ex02.src;

public class Milk extends FoodType {
    
    public Milk() {
        super(Temperature.WARM, State.Liquid);
    }

    @Override
    public String toString() {
        return "Milk: " + super.toString();
    }
}
