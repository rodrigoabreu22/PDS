package lab05.ex02.src;

public class FruitJuice extends FoodType {
    private String fruit;

    public FruitJuice() {
        super(Temperature.COLD, State.Liquid);
        this.fruit = "Orange";
    }

    @Override
    public String toString() {
        return "FruitJuice: " + this.fruit + ", " + super.toString();
    }
}
