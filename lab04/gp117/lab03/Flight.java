import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Flight {
    private String code = null;
    private int[][] touristSize = null;
    private int[][] executiveSize = null;
    private int reservationNum = 1;
    private boolean hasExec = false;
    private Map < String, Reservation > reservations = new HashMap < > ();

    public Flight(String flightCode, String flightExecutiveSize, String flightTouristSize) {
        this.code = flightCode;
        this.touristSize = new int[Integer.parseInt((flightTouristSize.split("x")[0]))][Integer.parseInt((flightTouristSize.split("x")[1]))];
        this.executiveSize = new int[Integer.parseInt((flightExecutiveSize.split("x")[0]))][Integer.parseInt((flightExecutiveSize.split("x")[1]))];
        this.hasExec = true;
    }

    public Flight(String flightCode, String flightTouristSize) {
        this.code = flightCode;
        this.touristSize = new int[Integer.parseInt((flightTouristSize.split("x")[0]))][Integer.parseInt((flightTouristSize.split("x")[1]))];
    }

    public String getFlightCode() {
        return code;
    }

    public void makeReservation(Scanner sc) {
        freeSeats();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] firstLine = line.split(" ");

            if (firstLine.length == 2 && firstLine[0].matches("[TE]") && firstLine[1].matches("[1-9][0-9]*")) {
                boolean reserved = checkReservation(Class.getEnum(firstLine[0]), Integer.parseInt(firstLine[1]));
                if (reserved) {
                    Reservation reservation = new Reservation(code, reservationNum, Class.getEnum(firstLine[0]));
                    reservations.put(reservation.getResCode(), reservation);
                    reservationNum++;
                }
            } else {
                System.out.println("");
            }
        }
    }

    public void makeReservation(Class classe, int numSeats) {
        boolean reserved = checkReservation(classe, numSeats);
        String string = "";

        if (reserved) {
            Reservation reservation = new Reservation(code, reservationNum, classe);
            reservations.put(reservation.getResCode(), reservation);
            reservationNum++;

            string = reservation.getResCode() + " = ";

            if (classe == Class.T) {
                for (int i = 0; i < touristSize.length; i++) {
                    for (int j = 0; j < touristSize[0].length; j++) {
                        if (touristSize[i][j] == reservation.getReservationNum() && hasExec) {
                            string += j + 1 + executiveSize[0].length + Character.toString((char)(65 + i)) + " | ";
                        }
                        else if (touristSize[i][j] == reservation.getReservationNum()) {
                            string += j + 1 + Character.toString((char)(65 + i)) + " | ";
                        }
                    }
                }
            } else {
                for (int i = 0; i < executiveSize.length; i++) {
                    for (int j = 0; j < executiveSize[0].length; j++) {
                        if (executiveSize[i][j] == reservation.getReservationNum()) {
                            string += j + 1 + Character.toString((char)(65 + i)) + " | ";
                        }
                    }
                }
            }
            string = string.substring(0, string.length()-3);
            System.out.println(string);
        }
    }

    public boolean checkReservation(Class classe, int numPassengers) {
        int numP = numPassengers;

        int[][] seats;
        int[][] seats_tmp;

        if (classe == Class.T) {
            seats = copyArray(touristSize);
            seats_tmp = copyArray(touristSize);
        } else if (classe == Class.E && hasExec) {
            seats = copyArray(executiveSize);
            seats_tmp = copyArray(executiveSize);
        } else {
            System.out.println("Classe executiva não disponível neste voo.");
            return false;
        }
        for (int i = 0; i < seats[0].length; i++) {
            boolean emptyRow = true;
            for (int j = 0; j < seats.length; j++) {
                if (seats[j][i] != 0) {
                    emptyRow = false;
                }
            }
            if (emptyRow) {
                for (int j = 0; j < seats.length; j++) {
                    seats_tmp[j][i] = reservationNum;
                    numPassengers--;
                    if (numPassengers == 0) {
                        if (classe == Class.T) {
                            touristSize = seats_tmp;
                        } else if (classe == Class.E) {
                            executiveSize = seats_tmp;
                        }
                        return true;
                    }
                }
            }
        }
        seats = copyArray(seats_tmp);

        for (int i = 0; i < seats[0].length; i++) {
            for (int j = 0; j < seats.length; j++) {
                if (seats[j][i] == 0) {
                    seats_tmp[j][i] = reservationNum;
                    numPassengers--;
                    if (numPassengers == 0) {
                        if (classe == Class.T) {
                            touristSize = seats_tmp;
                        } else if (classe == Class.E) {
                            executiveSize = seats_tmp;
                        }
                        return true;
                    }
                }
            }
        }

        if (numPassengers == 0) {
            if (classe == Class.T) {
                touristSize = seats_tmp;
            } else if (classe == Class.E) {
                executiveSize = seats_tmp;
            }
            return true;
        }

        System.out.println("Não foi possível obter lugares para a reserva: " + classe + " " + numP);
        System.out.println();
        return false;
    }

    public void freeSeats() {
        int numSeats = 0;

        System.out.print("Código de voo " + code + ". Lugares disponíveis: ");

        if (hasExec) {
            for (int i = 0; i < executiveSize.length; i++) {
                for (int j = 0; j < executiveSize[0].length; j++) {
                    if (executiveSize[i][j] == 0) {
                        numSeats++;
                    }
                }
            }
            System.out.print(numSeats + " lugares em classe Executiva; ");
        }

        numSeats = 0;

        for (int i = 0; i < touristSize.length; i++) {
            for (int j = 0; j < touristSize[0].length; j++) {
                if (touristSize[i][j] == 0) {
                    numSeats++;
                }
            }
        }

        System.out.print(numSeats + " lugares em classe Turística.\n");
    }

    public void cancelReservation(String reservationCode) {
        if (reservations.containsKey(reservationCode)) {
            Reservation reservation = reservations.get(reservationCode);

            if (reservation.getClasse() == Class.E) {
                for (int i = 0; i < executiveSize.length; i++) {
                    for (int j = 0; j < executiveSize[0].length; j++) {
                        if (executiveSize[i][j] == reservation.getReservationNum()) {
                            executiveSize[i][j] = 0;
                        }
                    }
                }
            } else {
                for (int i = 0; i < touristSize.length; i++) {
                    for (int j = 0; j < touristSize[0].length; j++) {
                        if (touristSize[i][j] == reservation.getReservationNum()) {
                            touristSize[i][j] = 0;
                        }
                    }
                }
            }
            reservations.remove(reservationCode);
        } else {
            System.out.println("O voo não contêm a reserva pretendida");
        }

    }

    public static int[][] copyArray(int[][] array) {
        int[][] arr = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                arr[i][j] = array[i][j];
            }
        }
        return arr;
    }


    @Override
    public String toString() {
        String string = " ";
        int numLines, numRows;

        if (hasExec) {
            numRows = executiveSize[0].length + touristSize[0].length;
            numLines = Math.max(executiveSize.length, touristSize.length);
        } else {
            numRows = touristSize[0].length;
            numLines = touristSize.length;
        }

        int ascii_A = 65;
        String[] letters = new String[numLines];

        for (int i = 0; i < numLines; i++) {
            int tmp = ascii_A++;
            letters[i] = Character.toString((char)tmp);
        }

        for (int i = 0; i < numRows; i++) {
            string += " " + String.format("%2d", i + 1);
        }

        string += "\n";

        for (int i = 0; i < numLines; i++) {
            string += letters[i];

            if (hasExec) {
                for (int j = 0; j < executiveSize[0].length; j++) {
                    if (i < executiveSize.length) {
                        string += " " + String.format("%2d", executiveSize[i][j]);
                    } else {
                        string = " ";
                    }
                }
            }

            for (int j = 0; j < touristSize[0].length; j++) {
                if (i < touristSize.length) {
                    string += " " + String.format("%2d", touristSize[i][j]);
                } else {
                    string = " ";
                }
            }

            if (numLines - 1 != i) {
                string += "\n";
            }
        }

        return string;
    }
}