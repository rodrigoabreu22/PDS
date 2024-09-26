package lab05.ex02.src;

public class Container {
    private Portion portion;

    protected Container(Portion portion) {
        this.portion = portion;
    }

    public static Container create(Portion portion) {
        switch (portion.getState()) {
            case Liquid:
                if (portion.getTemperature() == Temperature.COLD)
                    return new PlasticBottle(portion);
                else
                    return new TermicBottle(portion);

            case Solid:
                if (portion.getTemperature() == Temperature.COLD)
                    return new PlasticBag(portion);
                else
                    return new Tupperware(portion);
        
            default:
                System.out.println("Invalid state");
                break;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.portion.toString();
    }
}
