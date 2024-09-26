package ex01.src;

public class SpongeCakeBuilder implements CakeBuilder {
    private Cake spongeCake;

    @Override
    public void setCakeShape(Shape shape) {
        spongeCake.setShape(shape);
    }

    @Override
    public void addCakeLayer() {
        spongeCake.addLayer();
    }

    @Override
    public void addCreamLayer() {
        spongeCake.setMidLayerCream(Cream.Red_Berries);
    }

    @Override
    public void addTopLayer() {
        spongeCake.setTopLayerCream(Cream.Whipped_Cream);
    }

    @Override
    public void addTopping() {
        spongeCake.setTopping(Topping.Fruit);
    }

    @Override
    public void addMessage(String m) {
        spongeCake.setMessage(m);
    }

    @Override
    public void createCake() {
        spongeCake = new Cake("Sponge");
        addTopping();
        addTopLayer();
        addCreamLayer();
    }

    @Override
    public Cake getCake() {
        return spongeCake;
    }
}
