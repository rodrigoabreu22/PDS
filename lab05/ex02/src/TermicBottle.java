package lab05.ex02.src;

public class TermicBottle extends Container {

    protected TermicBottle(Portion portion) {
        super(portion);
    }

    @Override
    public String toString() {
        return "TermicBottle with portion = " + super.toString();
    }
}
