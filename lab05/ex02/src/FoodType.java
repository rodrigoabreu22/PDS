package lab05.ex02.src;

public abstract class FoodType implements Portion {
    private Temperature temperature;
    private State state;

    public FoodType(Temperature temperature, State state) {
        this.temperature = temperature;
        this.state = state;
    }

    @Override
    public Temperature getTemperature() {
        return this.temperature;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return "Temperature " + this.temperature + ", State " + this.state;
    }
}
