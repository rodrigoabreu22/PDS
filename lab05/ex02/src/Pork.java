package lab05.ex02.src;

public class Pork extends FoodType {

    public Pork() {
        super(Temperature.WARM, State.Solid);
    }
    
    @Override
    public String toString() {
        return "Pork: " + super.toString();
    }
}
