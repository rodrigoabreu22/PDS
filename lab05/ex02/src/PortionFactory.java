package lab05.ex02.src;

public class PortionFactory {

    public static Portion create(String aliment_type, Temperature temperature) {
        switch (aliment_type) {
            case "Meat":
                if (temperature == Temperature.WARM)
                    return new Pork();
                else if (temperature == Temperature.COLD)
                    return new Tuna();
            
            case "Beverage":
                if (temperature == Temperature.WARM)
                    return new Milk();
                else if (temperature == Temperature.COLD)
                    return new FruitJuice();

            default:
                return null;
        }
    }
}
