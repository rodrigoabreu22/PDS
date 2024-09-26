package lab05.ex02.src;

public class Tuna extends FoodType{

    public Tuna() {
        super(Temperature.COLD, State.Solid);
    }

    @Override
    public String toString() {
        return "Tuna: " + super.toString();
    }
}
