package ex01.src;
class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;
    //.. restantes métodos
    
    public Cake(String cakeLayer){
        this.shape=Shape.Circle;
        this.cakeLayer=cakeLayer;
        this.numCakeLayers=1;
        this.midLayerCream=Cream.NULL;
        this.topLayerCream=Cream.NULL;
        this.topping=Topping.NULL;
        this.message="";
    }

    public void addLayer(){
        this.numCakeLayers++;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setCakeLayer(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public void setNumCakeLayers(int numCakeLayers) {
        this.numCakeLayers = numCakeLayers;
    }

    public void setMidLayerCream(Cream midLayerCream) {
        this.midLayerCream = midLayerCream;
    }

    public void setTopLayerCream(Cream topLayerCream) {
        this.topLayerCream = topLayerCream;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        String output = "";
        if (numCakeLayers == 1){
            output = String.format("%s cake with %d layers, topped with %s cream and %s. Message says: \"%s\"", cakeLayer, numCakeLayers, topLayerCream, topping, message);
        }
        else {
            output = String.format("%s cake with %d layers and %s cream, topped with %s cream and %s. Message says: \"%s\"", cakeLayer, numCakeLayers, midLayerCream, topLayerCream, topping, message);
        }
        
        return output;
    }
}