package lab04.Ex2;

public class Reservation {
    private String fligth_code;
    private Integer num_seats;
    private Classe classe;
    private String seats;  
    private Integer id;

    /**
     * Reservation constructor
     * @param fligth_code
     * @param num_seats
     * @param classe
     * @param id
     */
    public Reservation(String fligth_code, Integer num_seats, Classe classe, int id) {
        this.fligth_code = fligth_code;
        this.num_seats = num_seats;
        this.classe = classe;
        this.id = id;
    }

    /**
     * Get the flight code
     * @return the flight code
     */
    public String getFligth_code() {
        return fligth_code;
    }

    /**
     * Get the number of seats
     * @return the number of seats
     */
    public Integer getNum_seats() {
        return num_seats;
    }

    /**
     * Get the class
     * @return the class
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * Get the seats
     * @return the seats
     */
    public String getSeats() {
        return seats;
    }

    /**
     * Get the id
     * @return the id
     */
    public Integer getReservationID() {
        return id;
    }

    /**
     * Decrement the reservation id
     */
    public void decrementReservationID() {
        this.id--;
    }

    /**
     * Set the seats
     * @param seats
     */
    public void setSeats(String seats) {
        this.seats = seats;
    }
}
