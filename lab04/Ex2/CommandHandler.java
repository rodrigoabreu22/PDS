package lab04.Ex2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CommandHandler {

    public static VooManager vooManager = new VooManager();
    public static ReservationManager reservationManager = new ReservationManager();

    /**
     * Command handler (responsabel for the main logic of the program)
     * determines which command was inputed and calls the respective method
     * @param args
     */
    public void CommandsHandler(String[] args) {
        String command = args[0].toUpperCase();

        switch (command){

            case "H":
                System.out.println(Menu.menu());
                break;
            
            case "I":
                readVooFromFile(args);
                break;

            case "M":
                vooMap(args);
                break;

            case "F":
                createVooCommand(args);
                break;

            case "R":
                reservationCommand(args);
                break;

            case "C":
                removeReservation(args);
                break;

            case "Q":
                quitProgram();
                break;

            default:
                System.out.println("Comando inválido\n");
                break;
        }
    }

    /**
     * Check if the number of arguments is valid
     * @param args
     * @param expected
     * @return {@code true} if the number of arguments is valid
     */
    public static boolean validNumberOfArguments(int args, int expected) {
        if (args != expected) {
            return false;
        }
        return true;
    }

    /**
     * Read a voo from a file. The file should contain the flight code and the reservations.
     * The first line of the file should contain the flight code and the number of seats for each class.
     * The following lines should contain the reservations.
     * @param args
     */
    public static void readVooFromFile(String[] args){

        if (!validNumberOfArguments(args.length, 2)) {
            System.out.println("Número de argumentos inválido\n");
            return;
        }

        String filename = args[1];

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int line_counter = 0;
            String flight_code = "";

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line_counter == 0){
                    flight_code = ReadVooHeader(line);
                }
                else {
                    String[] seats = line.split(" ");

                    if (seats.length != 2){
                        System.out.println("Erro. Codigo de reserva inválido\n");
                        scanner.close();
                        return;
                    }

                    if (isValidClass(seats[0]) && isNumber(seats[1])){
                        Classe classe = getClasse(seats[0]);
                        Integer num_seats = Integer.parseInt(seats[1]);

                        Voo voo = vooManager.getVoo(flight_code);
                        int reserve_id = voo.getNumReservations()+1;
                        
                        Reservation reserva = reservationManager.createReservation(flight_code, num_seats, classe, reserve_id);
                        voo.incrementNumReservations();

                        if(!vooManager.reserve(reserva)){
                            System.out.println("Não foi possivel efetuar a reserva: " + classe + " " + num_seats);
                            reservationManager.removeAndDecrementReservation(reserva);
                            voo.decrementNumReservations();
                        }
                    }
                }
                line_counter++;
            }
            scanner.close();
            System.out.println();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            return;
        }
    }

    /**
     * Read the voo header from a string
     * @param line
     * @return the flight code
     */
    public static String ReadVooHeader(String line){
        String[] line_args = line.split(" ");

        if (!validNumberOfArguments(line_args.length, 2) && !validNumberOfArguments(line_args.length, 3)) {
            System.out.println("Número de argumentos inválido\n");
            return null;
        }

        String flight_code = line_args[0];

        if (flight_code.charAt(0) == '>'){
            flight_code = flight_code.substring(1,flight_code.length());
        }

        String seats_turistica = line_args[1];
        String seats_executiva = "0x0";

        if (line_args.length == 3) {
            seats_executiva = seats_turistica;
            seats_turistica = line_args[2];
        }

        Voo voo = vooManager.createVoo(flight_code, seats_executiva, seats_turistica);

        if (voo == null){
            return null;
        }

        // print do voo
        System.out.println(vooManager.getVooInfo(flight_code));
        return flight_code;
    }   

    /**
     * Print the map of the plane seats
     * @param args
     */
    public static void vooMap(String[] args){
        if (!validNumberOfArguments(args.length, 2)) {
            System.out.println("Número de argumentos inválido\n");
            return;
        }

        String flight_code = args[1];
        String map = vooManager.mapPlaneSeats(flight_code);

        if (map != null) { 
            System.out.println(map + "\n");
        }
        else {
            System.out.printf("Voo %s não encontrado\n\n", flight_code);
        }
    }

    /**
     * Create a reservation
     * @param args
     */
    public static void reservationCommand(String[] args){
        if (!validNumberOfArguments(args.length, 4)) {
            System.out.println("Número de argumentos inválido\n");
            return;
        }

        String flight_code = args[1];

        // check if the flight exists
        if (!vooManager.flightExists(flight_code)){
            System.out.println("Voo não encontrado\n");
            return;
        }

        if (isValidClass(args[2]) && isNumber(args[3])){

            Classe classe = getClasse(args[2]);
            Integer num_seats = Integer.parseInt(args[3]);
            Voo voo = vooManager.getVoo(flight_code);
            int reserve_id = voo.getNumReservations()+1;

            Reservation reserva = reservationManager.createReservation(flight_code, num_seats, classe,reserve_id);
            voo.incrementNumReservations();

            if (reserva == null) {
                System.out.println("A reserva não existe\n");
                return;
            }

            if (!vooManager.reserve(reserva)) {
                System.out.println("Não foi possivel efetuar a reserva: " + classe + " " + num_seats + "\n");
                reservationManager.removeAndDecrementReservation(reserva);
                voo.decrementNumReservations();
                return; 
            }

            String reservation_output = reservationManager.getReservationSeats(flight_code, reserva.getReservationID());

            if (reservation_output.equals("")) {
                System.out.println("erro na reserva de lugares\n");
            }
            else{
                System.out.println(flight_code + ":" + reserva.getReservationID() + " = " + reservation_output);
            }
        }
        else{
            System.out.println("Argumentos de reserva invalidos\n");
        }
    }

    /**
     * Check if the class is valid
     * @param classe
     * @return {@code true} if the class is valid
     */
    public static boolean isValidClass(String classe){
        if (classe.equals("T") || classe.equals("E")){
            return true;
        }
        return false;
    }

    /**
     * Check if a string is a number
     * @param number
     * @return {@code true} if the string is a number
     */
    public static boolean isNumber(String number){
        if (number.matches("[0-9]+")){
            return true;
        }
        return false;
    }

    /**
     * Get the class from a string
     * @param classe
     * @return the class
     */
    public static Classe getClasse(String classe){
        if (classe.equals("T")){
            return Classe.TURISTICA;
        }
        else if (classe.equals("E")){
            return Classe.EXECUTIVA;
        }
        return null;
    }

    /**
     * Remove a reservation
     * @param args
     */
    public static void removeReservation(String[] args){
        if (!validNumberOfArguments(args.length, 2)) {
            System.out.println("Número de argumentos inválido\n");
            return;
        }

        String reservation_code = args[1];
        String[] parametros = reservation_code.split(":");
        String flight_code = parametros[0];
        int num_reserva = Integer.parseInt(parametros[1]);

        Reservation reserva = reservationManager.getReservation(flight_code, num_reserva);

        if (reserva == null) {
            System.out.println("A reserva não existe\n");
            return;
        }

        if (!vooManager.cancelReservation(reserva)) {
            System.out.println("A reserva não existe\n");
            return;
        }
        reservationManager.removeReservation(reserva);
        System.out.println("Reserva cancelada com sucesso\n");
    }

    /**
     * Convert the voo header to a string
     * @param args
     * @return the voo header
     */
    public static String vooHeaderToString(String[] args){
        String args_input = "";
        for (int i = 1; i < args.length; i++) {
            args_input += args[i] + " ";
        }
        return args_input;
    }

    /**
     * Function to create a voo from the command "F" input
     * @param args - input arguments (flight code and number of seats for each class)
     */
    public void createVooCommand(String[] args){
        String voo_header = vooHeaderToString(args);
        String flight_code = ReadVooHeader(voo_header);

        if (flight_code != null){
            System.out.printf("Voo %s criado com sucesso\n\n", flight_code);
        }
        else {
            System.out.println("Erro na criação do voo\n");
        }
    }

    /**
     * Function to quit the program
     */
    public static void quitProgram(){
        System.out.println("A terminar o programa");
        System.exit(0);
    }
}