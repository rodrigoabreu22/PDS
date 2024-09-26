package lab04.Ex2;

import java.util.ArrayList;

public class ReservationManager {
    private ArrayList<Reservation> allReservations;

    /**
     * ReservationManager constructor
     */
    public ReservationManager() {
        allReservations = new ArrayList<Reservation>();
    }

    /**
     * Creates a new reservation. Calls the Reservation constructor
     * @param fligth_code
     * @param num_seats
     * @param classe
     * @return the new reservation
     */
    public Reservation createReservation(String fligth_code, Integer num_seats, Classe classe, int id) {
        Reservation reservation = new Reservation(fligth_code, num_seats, classe, id);
        allReservations.add(reservation);
        return reservation;
    }

    /**
     * Get a reservation by its id and flight code
     * @param fligth_code
     * @param id
     * @return the reservation
     */
    public Reservation getReservation(String fligth_code, Integer id) {
        for (Reservation reservation : allReservations) {
            if (reservation.getFligth_code().equals(fligth_code) && reservation.getReservationID() == id) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Get the seats of a reservation
     * @param fligth_code
     * @param id
     * @return the seats
     */
    public String getReservationSeats(String fligth_code, Integer id) {
        Reservation reservation = getReservation(fligth_code, id);
        if (reservation != null) {
            return reservation.getSeats();
        }
        return null;
    }   

    /**
     * Set the seats of a reservation
     * @param fligth_code
     * @param id
     * @param seats
     */
    public void setReservationSeats(String fligth_code, Integer id, String seats) {
        Reservation reservation = getReservation(fligth_code, id);
        if (reservation != null) {
            reservation.setSeats(seats);
        }
    }

    /**
     * Remove a reservation
     * @param reservation
     */
    public void removeReservation(Reservation reservation) {
        allReservations.remove(reservation);
    }

    /**
     * Remove a reservation and decrement the reservation id (when is created a reservation object that is not possible to reserve in the flight)
     * @param reservation
     */
    public void removeAndDecrementReservation(Reservation reservation) {
        allReservations.remove(reservation);
        reservation.decrementReservationID();
    }
}
