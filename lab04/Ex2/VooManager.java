package lab04.Ex2;

import java.util.HashMap;

public class VooManager {
    private HashMap<String, Voo> voos;

    /**
     * VooManager constructor
     */
    public VooManager() {
        this.voos = new HashMap<String, Voo>();
    }
    
    /**
     * Create a new Voo and the respective Plane
     * @param flight_code
     * @param seats_executiva
     * @param seats_turistica
     * @return the new Voo or {@code null} if the flight code already exists
     */
    public Voo createVoo(String flight_code, String seats_executiva, String seats_turistica) {
        if (voos.containsKey(flight_code)) return null;

        //split the string to get the number of rows and seats
        String regex = "(?i)x";
        String[] turistica = seats_turistica.split(regex);
        String[] executiva = seats_executiva.split(regex);

        //rows x seats
        Integer row_turistica = Integer.parseInt(turistica[0]);
        Integer seats_turistica_ = Integer.parseInt(turistica[1]);
        Integer row_executiva = Integer.parseInt(executiva[0]);
        Integer seats_executiva_ = Integer.parseInt(executiva[1]);

        //criar classes com as dimensões dadas
        Integer[][] turistica_seats = new Integer[row_turistica][seats_turistica_];
        Integer[][] executiva_seats = new Integer[row_executiva][seats_executiva_];

        Plane plane = new Plane(turistica_seats, executiva_seats);
        Voo voo = new Voo(flight_code, plane);
        voos.put(flight_code, voo);

        return voo;
    }

    /**
     * Get the number of ocupated seats in the turistic class of a flight 
     * @param flight_code
     * @return the number of ocupated seats or {@code null} if the flight code does not exist
     */
    public Integer getOcupatedSeatsTuristica(String flight_code) {
        int ocupatedSeats = 0;

        if (!voos.containsKey(flight_code)) return null;
        Voo voo = voos.get(flight_code);

        Plane plane = voo.getPlane();
        Integer[][] turistica = plane.getTuristica();    

        for (int i = 0; i < turistica.length; i++) {
            for (int j = 0; j < turistica[i].length; j++) {
                if (turistica[i][j] != 0) {
                    ocupatedSeats++;
                }
            }
        }
        return ocupatedSeats;
    }

    /**
     * Get the number of ocupated seats in the executive class of a flight
     * @param flight_code
     * @return the number of ocupated seats or {@code null} if the flight code does not exist
     */
    public Integer getOcupatedSeatsExecutiva(String flight_code) {
        int ocupatedSeats = 0;

        if (!voos.containsKey(flight_code)) return null;
        Voo voo = voos.get(flight_code);

        Plane plane = voo.getPlane();
        Integer[][] executiva = plane.getExecutiva();    

        for (int i = 0; i < executiva.length; i++) {
            for (int j = 0; j < executiva[i].length; j++) {
                if (executiva[i][j] != 0) {
                    ocupatedSeats++;
                }
            }
        }
        return ocupatedSeats;
    }

    /**
     * Get the number of available seats in the executive class of a flight
     * @param flight_code
     * @return the number of available seats or {@code null} if the flight code does not exist
     */
    public Integer getAvailableSeatsTuristica(String flight_code) {
        if (!voos.containsKey(flight_code)) return null;
        Voo voo = voos.get(flight_code);
        
        Plane plane = voo.getPlane();
        return plane.getMaxCapacityTuristica() - getOcupatedSeatsTuristica(flight_code);
    }

    /**
     * Get the number of available seats in the executive class of a flight
     * @param flight_code
     * @return the number of available seats or {@code null} if the flight code does not exist
     */
    public Integer getAvailableSeatsExecutiva(String flight_code) {
        if (!voos.containsKey(flight_code)) return null;
        Voo voo = voos.get(flight_code);
        
        Plane plane = voo.getPlane();
        return plane.getMaxCapacityExecutiva() - getOcupatedSeatsExecutiva(flight_code);
    }

    /**
     * Get the information of a flight
     * @param flight_code
     * @return the information of the flight or {@code null} if the flight code does not exist
     */
    public String getVooInfo(String flight_code) {
        if (!voos.containsKey(flight_code)) return null;
        Voo voo = voos.get(flight_code);
        return voo.toString();
    }

    /**
     * Get the map seats of a flight
     * @param flight_code
     * @return the map of the seats or {@code null} if the flight code does not exist
     */
    public String mapPlaneSeats(String flight_code) {
        if (!voos.containsKey(flight_code)) return null;
        Voo voo = voos.get(flight_code);
        
        Plane plane = voo.getPlane();

        Integer[][] seats_turistica = plane.getTuristica();
        Integer[][] seats_executiva = plane.getExecutiva();
        final int FIRST_LETTER_ASCII = 65;

        int filas_turistica = seats_turistica.length;
        int filas_executiva = seats_executiva.length;
        int max_filas = filas_executiva + filas_turistica;
        int max_seats_fila = 0;

        // número máximo de lugares por fila
        if (filas_executiva == 0) {
            max_seats_fila = seats_turistica[0].length;
        }
        else {
            int seats = Math.max(seats_turistica[0].length, seats_executiva[0].length);
            max_seats_fila = seats;
        }

        // String que vai conter o mapa dos lugares
        StringBuilder map = new StringBuilder();

        // adicionar número de filas 
        for (int i = 1; i <= max_filas; i++) {
            map.append("\t" + i);
        }
        map.append("\n");

        // adicionar lugares
        for (int i = 0; i < max_seats_fila; i++) {
            char letter = (char) (FIRST_LETTER_ASCII + i);
            map.append(letter);

            for (int j = 0; j < max_filas; j++) {
                if (j < filas_executiva) {
                    if (i < seats_executiva[j].length) {
                        map.append("\t" + seats_executiva[j][i]);
                    }
                    else {
                        map.append("\t");
                    }
                }
                else {
                    if (filas_executiva > 0) {
                        if (i < seats_turistica[j - filas_executiva].length) {
                            map.append("\t" + seats_turistica[j - filas_executiva][i]);
                        }
                        else {
                            map.append("\t");
                        }
                    }
                    else {
                        if (i < seats_turistica[j].length) {
                            map.append("\t" + seats_turistica[j][i]);
                        }
                        else {
                            map.append("\t");
                        }
                    }
                }
            }
            map.append("\n");
        } 
        return map.toString();
    }

    /**
     * Reserve a number of seats in a flight and class
     * @param reservation
     * @return {@code true} if the reservation was successful, {@code false} otherwise
     */
    public boolean reserve(Reservation reservation) {
        if (!voos.containsKey(reservation.getFligth_code())) return false;
        Voo voo = voos.get(reservation.getFligth_code());

        Plane plane = voo.getPlane();
        Integer[][] seats;
        boolean reserve_success = false;
        int seats_reserved = 0;
        int num_seats = reservation.getNum_seats();
        Classe classe = reservation.getClasse();

        switch (classe) {
            case TURISTICA:
                if (num_seats > getAvailableSeatsTuristica(reservation.getFligth_code())) return false;
                seats = plane.getTuristica();
                break;

            case EXECUTIVA:
                if (num_seats > getAvailableSeatsExecutiva(reservation.getFligth_code())) return false;
                seats = plane.getExecutiva();
                break;

            default:
                return false;
        }

        int num_filas = seats.length;
        int seats_fila = seats[0].length;
        int free_fila = -1;
        String lugares = "";

        // verificar se exitem filas vazias
        for (int i = 0; i < num_filas; i++) {
            boolean free = true;

            for (int j = 0; j < seats_fila; j++) {
                if (seats[i][j] != 0) {
                    free = false;
                    break;
                }
            }
            if (free) {
                // inicio das filas vazias
                free_fila = i;
                break;
            }
        }

        // existem filas vazias
        if (free_fila != -1) {
            // começar procura na fila vazia
            for (int i = free_fila; i < num_filas; i++) {
                for (int j = 0; j < seats_fila; j++) {

                    if (reserve_success) {
                        break;
                    }

                    if (seats[i][j] == 0) {
                        seats[i][j] = reservation.getReservationID();
                        seats_reserved++;

                        if (seats_reserved < num_seats) {
                            lugares += getReservationCode(i + 1, j, classe, plane) + " | ";
                        }
                        else{
                            lugares += getReservationCode(i + 1, j, classe, plane) + "\n";
                        }
                    }
                    if (seats_reserved == num_seats) {
                        reserve_success = true;
                        break;
                    }
                }
            }

            // caso não tenha sido possível reservar todos os lugares
            if (!reserve_success) {
                // procurar lugares nas filas anteriores
                for (int i = 0; i < free_fila; i++) {
                    if (reserve_success) {
                        break;
                    }

                    for (int j = 0; j < seats_fila; j++) {
                        if (seats[i][j] == 0) {
                            seats[i][j] = reservation.getReservationID();
                            seats_reserved++;

                            if (seats_reserved < num_seats) {
                                lugares += getReservationCode(i + 1, j, classe, plane) + " | ";
                            }
                            else{
                                lugares += getReservationCode(i + 1, j, classe, plane) + "\n";
                            }
                        }
                        if (seats_reserved == num_seats) {
                            reserve_success = true;
                            break;
                        }
                    }
                }
            }
        }
        // não existem filas vazias
        else {
            for (int i = 0; i < num_filas; i++) {
                if (reserve_success) {
                    break;
                }

                for (int j = 0; j < seats_fila; j++) {
                    if (seats[i][j] == 0) {
                        seats[i][j] = reservation.getReservationID();
                        seats_reserved++;

                        if (seats_reserved < num_seats) {
                            lugares += getReservationCode(i + 1, j, classe, plane) + " | ";
                        }
                        else{
                            lugares += getReservationCode(i + 1, j, classe, plane) + "\n";
                        }
                    }
                    if (seats_reserved == num_seats) {
                        reserve_success = true;
                        break;
                    }
                }
            }
        }
        if (!lugares.equals("")) {
            reservation.setSeats(lugares);
        }

        if (reserve_success) {
            switch (classe) {
                case TURISTICA:
                    plane.setTuristica(seats);
                    break;
                case EXECUTIVA:
                    plane.setExecutiva(seats);
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * Cancel a reservation through its ID
     * @param reservation
     * @return {@code true} if the reservation was canceled, {@code false} otherwise
     */
    public boolean cancelReservation(Reservation reservation) {
        if (!voos.containsKey(reservation.getFligth_code())) return false;
        Voo voo = voos.get(reservation.getFligth_code());

        Plane plane = voo.getPlane();

        Integer[][] seats_turistica = plane.getTuristica();
        Integer[][] seats_executiva = plane.getExecutiva();
    
        int filas_turistica = seats_turistica.length;
        int filas_executiva = seats_executiva.length;
    
        boolean found_executiva = false;
        boolean found_seats = false;
        int reserva = reservation.getReservationID();

        // Search for the reservation ID and cancel it
        for (int i = 0; i < filas_executiva; i++) {
            for (int j = 0; j < seats_executiva[i].length; j++) {
                if (seats_executiva[i][j] == reserva) {
                    seats_executiva[i][j] = 0;
                    found_executiva = true;
                    found_seats = true;
                }
            }
        }
    
        if (!found_executiva) {
            for (int i = 0; i < filas_turistica; i++) {
                for (int j = 0; j < seats_turistica[i].length; j++) {
                    if (seats_turistica[i][j] == reserva) {
                        seats_turistica[i][j] = 0;
                        found_seats = true;
                    }
                }
            }
        }
    
        // Update the seat layouts in the plane object
        if (found_executiva) {
            plane.setExecutiva(seats_executiva);
        }
        else {
            plane.setTuristica(seats_turistica);
        }
        return found_seats;
    }

    /**
     * Get the reservation code
     * @param fila
     * @param lugar_fila
     * @param classe
     * @param plane
     * @return the row and seat code
     */
    private String getReservationCode(int fila, int lugar_fila, Classe classe, Plane plane) {
        char letter = (char) (65 + lugar_fila);
        
        if (classe == Classe.TURISTICA) {
            fila = fila + plane.getNumFilasExecutiva();
        }
        return fila + "" + letter;
    }

    /**
     * Get the Voo object
     * @param flight_code
     * @return the Voo object or {@code null} if the flight code does not exist
     */
    public Voo getVoo(String flight_code) {
        return voos.get(flight_code);
    }
    
    /**
     * Get the number of reservations of a flight
     * @param flight_code
     * @return the number of reservations or {@code null} if the flight code does not exist
     */
    public int getNumReservations(String flight_code) {
        return voos.get(flight_code).getNumReservations();
    }

    /**
     * Increment the number of reservations of a flight
     * @param flight_code
     */
    public void incrementNumReservations(String flight_code) {
        voos.get(flight_code).incrementNumReservations();
    }

    /**
     * verify if a flight exists
     * @param flight_code
     */
    public boolean flightExists(String flight_code) {
        return voos.containsKey(flight_code);
    }
}
