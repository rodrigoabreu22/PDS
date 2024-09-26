package ex01.src;

public class ChocolateCakeBuilder implements CakeBuilder {
    private Cake chocolateCake;

    @Override
    public void setCakeShape(Shape shape) {
        chocolateCake.setShape(shape);
    }

    @Override
    public void addCakeLayer() {
        chocolateCake.addLayer();
    }

    @Override
    public void addCreamLayer() {
        chocolateCake.setMidLayerCream(Cream.NULL);
    }

    @Override
    public void addTopLayer() {
        chocolateCake.setTopLayerCream(Cream.Whipped_Cream);
    }

    @Override
    public void addTopping() {
        chocolateCake.setTopping(Topping.Fruit);
    }

    @Override
    public void addMessage(String m) {
        chocolateCake.setMessage(m);
    }

    @Override
    public void createCake() {
        chocolateCake = new Cake("Soft chocolate");
        addTopping();
        addTopLayer();
        addCreamLayer();
    }

    @Override
    public Cake getCake() {
        return chocolateCake;
    }

}
