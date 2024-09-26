package lab05.ex02.src;

public class Tupperware extends Container {
    
    protected Tupperware(Portion portion) {
        super(portion);
    }

    @Override
    public String toString() {
        return "Tupperware with portion = " + super.toString();
    }
}
