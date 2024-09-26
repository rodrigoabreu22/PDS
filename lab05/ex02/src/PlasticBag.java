package lab05.ex02.src;

public class PlasticBag extends Container {
    
    protected PlasticBag(Portion portion) {
        super(portion);
    }

    @Override
    public String toString() {
        return "PlasticBag with portion = " + super.toString();
    }
}
