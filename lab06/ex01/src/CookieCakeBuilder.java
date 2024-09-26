package ex01.src;

public class CookieCakeBuilder implements CakeBuilder{
    
    private Cake cookieCake;
    
    public void setCakeShape(Shape shape) {
        cookieCake.setShape(shape);
    }

    public void addCakeLayer() {
        cookieCake.addLayer();
    }

    public void addCreamLayer() {
        cookieCake.setMidLayerCream(Cream.Whipped_Cream);
    }

    public void addTopLayer() {
        cookieCake.setTopLayerCream(Cream.Vanilla);
    }

    public void addTopping() {
        cookieCake.setTopping(Topping.Cookie);
    }

    public void addMessage(String m) {
        cookieCake.setMessage(m);
    }

    public void createCake() {
        cookieCake = new Cake("Cookie");
        addTopping();
        addTopLayer();
        addCreamLayer();
    }

    public Cake getCake() {
        return cookieCake;
    }
}
