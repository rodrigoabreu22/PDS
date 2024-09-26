package lab04.Ex2;

public class Voo {
    private String code;
    private Plane plane;
    private int num_reservations;

    /**
     * Voo constructor
     * @param code
     * @param plane
     */
    public Voo(String code, Plane plane) {
        this.code = code;
        this.plane = plane;
        this.num_reservations = 0;
    }

    /**
     * Get the code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get the plane
     * @return the plane
     */
    public Plane getPlane() {
        return plane;
    }

    /**
     * Get the number of reservations
     * @return the number of reservations
     */
    public int getNumReservations() {
        return num_reservations;
    }

    /**
     * Increment the number of reservations
     */
    public void incrementNumReservations() {
        this.num_reservations++;
    }

    /**
     * Decrement the number of reservations
     */
    public void decrementNumReservations() {
        this.num_reservations--;
    }

    @Override
    public String toString() {
        return "Código de voo " + this.code + ". " + this.plane.toString();
    }
}
