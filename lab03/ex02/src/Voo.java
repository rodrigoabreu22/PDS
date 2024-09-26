package lab03.ex02.src;

public class Voo {
    private String code;
    private Plane plane;
    private int capacity_turistica;
    private int capacity_executiva;
    private int reservas;

    public Voo(String code, Plane plane) {
        this.code = code;
        this.plane = plane;
        this.capacity_turistica = plane.getMaxCapacityTuristica();
        this.capacity_executiva = plane.getMaxCapacityExecutiva();
        this.reservas = 0;
    }

    public String getCode() {
        return code;
    }

    public Plane getPlane() {
        return plane;
    }

    public int getCapacity_turistica() {
        return capacity_turistica;
    }

    public int getCapacity_executiva() {
        return capacity_executiva;
    }

    public int getReservas() {
        return reservas;
    }

    public void incrementReservas() {
        this.reservas++;
    }
    
    public void planeSeats() {
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

        // imprimir número de filas
        for (int i = 1; i <= max_filas; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();

        // imprimir lugares
        for (int i = 0; i < max_seats_fila; i++) {
            char letter = (char) (FIRST_LETTER_ASCII + i);
            System.out.print(letter);

            for (int j = 0; j < max_filas; j++) {
                if (j < filas_executiva) {
                    if (i < seats_executiva[j].length) {
                        System.out.print("\t" + seats_executiva[j][i]);
                    }
                    else {
                        System.out.print("\t");
                    }
                }
                else {
                    if (filas_executiva > 0) {
                        if (i < seats_turistica[j - filas_executiva].length) {
                            System.out.print("\t" + seats_turistica[j - filas_executiva][i]);
                        }
                        else {
                            System.out.print("\t");
                        }
                    }
                    else {
                        if (i < seats_turistica[j].length) {
                            System.out.print("\t" + seats_turistica[j][i]);
                        }
                        else {
                            System.out.print("\t");
                        }
                    }
                }
            }
            System.out.println();
        } 
        System.out.println();
    }

    public boolean reserve(Classes classe, int num_seats, boolean print) {
        Integer[][] seats;
        boolean reserve_success = false;
        int seats_reserved = 0;
        
        // número da reserva
        this.reservas++;
        
        String output = this.getCode() + ":" + this.getReservas() + " = ";

        switch (classe) {
            case TURISTICA:
                if (plane.getFreeSeatsTuristica() < num_seats) {
                    this.reservas--;
                    return false;
                }
                seats = plane.getTuristica();
                break;
            case EXECUTIVA:
                if (plane.getFreeSeatsExecutiva() < num_seats) {
                    this.reservas--;
                    return false;
                }
                seats = plane.getExecutiva();
                break;
            default:
                return false;
        }

        // detalhes da classe
        int num_filas = seats.length;
        int seats_fila = seats[0].length;
        int free_fila = -1;

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
                        seats[i][j] = this.reservas;
                        seats_reserved++;

                        if (seats_reserved < num_seats) {
                            output += getReservationCode(i + 1, j, classe) + " | ";
                        }
                        else{
                            output += getReservationCode(i + 1, j, classe) + "\n";
                        }
                    }
                    if (seats_reserved == num_seats) {
                        if (print) {
                            System.out.println(output);
                        }
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
                            seats[i][j] = this.reservas;
                            seats_reserved++;

                            if (seats_reserved < num_seats) {
                                output += getReservationCode(i + 1, j, classe) + " | ";
                            }
                            else{
                                output += getReservationCode(i + 1, j, classe) + "\n";
                            }
                        }
                        if (seats_reserved == num_seats) {
                            if (print) {
                                System.out.println(output);
                            }
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
                        seats[i][j] = this.reservas;
                        seats_reserved++;

                        if (seats_reserved < num_seats) {
                            output += getReservationCode(i + 1, j, classe) + " | ";
                        }
                        else{
                            output += getReservationCode(i + 1, j, classe) + "\n";
                        }
                    }
                    if (seats_reserved == num_seats) {
                        if (print) {
                            System.out.println(output);
                        }
                        reserve_success = true;
                        break;
                    }
                }
            }
        }

        if (reserve_success) {
            switch (classe) {
                case TURISTICA:
                    this.plane.setTuristica(seats);
                    break;
                case EXECUTIVA:
                    this.plane.setExecutiva(seats);
                    break;
            }
            return true;
        }
        else {
            this.reservas--;
            return false;
        }
    }

    public boolean cancelReservation(int reserva) {
        Integer[][] seats_turistica = this.plane.getTuristica();
        Integer[][] seats_executiva = this.plane.getExecutiva();
    
        int filas_turistica = seats_turistica.length;
        int filas_executiva = seats_executiva.length;
    
        boolean found_executiva = false;
        boolean found_seats = false;
    
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
            this.plane.setExecutiva(seats_executiva);
        }
        else {
            this.plane.setTuristica(seats_turistica);
        }
        return found_seats;
    }

    public String getReservationCode(int fila, int lugar_fila, Classes classe) {
        char letter = (char) (65 + lugar_fila);
        
        if (classe == Classes.TURISTICA) {
            fila = fila + this.plane.getNumFilasExecutiva();
        }
        return fila + "" + letter;
    }

    @Override
    public String toString() {
        return "Código de voo " + this.code + ". " + this.plane.toString();
    }
}