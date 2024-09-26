package lab05.ex02.src;

public class PlasticBottle extends Container {
    
    protected PlasticBottle(Portion portion) {
        super(portion);
    }

    @Override
    public String toString() {
        return "PlasticBottle with portion = " + super.toString();
    }
}
