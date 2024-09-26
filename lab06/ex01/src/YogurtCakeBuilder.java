package ex01.src;

public class YogurtCakeBuilder implements CakeBuilder{
    
    private Cake yogurtCake;
    
    @Override
    public void setCakeShape(Shape shape) {
        yogurtCake.setShape(shape);
    }

    @Override
    public void addCakeLayer() {
        yogurtCake.addLayer();
    }

    @Override
    public void addCreamLayer() {
        yogurtCake.setMidLayerCream(Cream.Yogurt);
    }

    @Override
    public void addTopLayer() {
        yogurtCake.setTopLayerCream(Cream.Yogurt);
    }

    @Override
    public void addTopping() {
        yogurtCake.setTopping(Topping.Fruit);
    }

    @Override
    public void addMessage(String m) {
        yogurtCake.setMessage(m);
    }

    @Override
    public void createCake() {
        yogurtCake = new Cake("Yogurt");
        addTopping();
        addTopLayer();
        addCreamLayer();
    }

    @Override
    public Cake getCake() {
        return yogurtCake;
    }
}
