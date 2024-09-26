package ex01.src;

public class CakeMaster {

    private CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cakeBuilder){
        this.cakeBuilder = cakeBuilder;
    }

    public void createCake(String message){
        cakeBuilder.createCake();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(message);
    }

    public void createCake(Shape shape, int numCakeLayers, String message){
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);
        for(int i=0; i<numCakeLayers; i++){
            cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(message);
    }

    public void createCake(int numCakeLayers, String message){
        cakeBuilder.createCake();
        for(int i=0; i<numCakeLayers; i++){
            cakeBuilder.addCakeLayer();
        }
        if (numCakeLayers>1){
            cakeBuilder.addCreamLayer();
        }
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(message);
    }

    public Cake getCake(){
        return cakeBuilder.getCake();
    }
}
