package ex02.src;
public class Place {
    private String name;
    private String city;
    private String country;

    /**
     * Constructor for Place
     * @param name
     * @param city
     * @param country
     */
    private Place(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    /**
     * Create a new Place
     * @param name
     * @param city
     * @param country
     * @return Place
     */
    public static Place createPlace(String name, String city, String country) {
        if (name == null || city == null || country == null) {
            return new Place("Unknown", "Unknown", "Unknown");
        }
        return new Place(name, city, country);
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
