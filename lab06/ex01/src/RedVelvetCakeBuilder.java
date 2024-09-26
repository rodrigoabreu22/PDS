package ex01.src;

public class RedVelvetCakeBuilder implements CakeBuilder{
    
    private Cake redVelvetCake;
    
    public void setCakeShape(Shape shape) {
        redVelvetCake.setShape(shape);
    }

    public void addCakeLayer() {
        redVelvetCake.addLayer();
    }

    public void addCreamLayer() {
        redVelvetCake.setMidLayerCream(Cream.Cheese);
    }

    public void addTopLayer() {
        redVelvetCake.setTopLayerCream(Cream.Cheese);
    }

    public void addTopping() {
        redVelvetCake.setTopping(Topping.Red_Berries);
    }

    public void addMessage(String m) {
        redVelvetCake.setMessage(m);
    }

    public void createCake() {
        redVelvetCake = new Cake("Red Velvet");
        addTopping();
        addTopLayer();
        addCreamLayer();
    }

    public Cake getCake() {
        return redVelvetCake;
    }
}
