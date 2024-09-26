package lab03.ex02.src;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class Ex2 {  
    static HashMap<String, Voo> voos = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("Sistema de gestão de voos");

        // programa executado sem argumentos
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            String[] command;

            // ciclo para ler comandos
            do {
                System.out.println("Escolha uma opção: (H para ajuda)");
                command = sc.nextLine().split(" ");
                CommandsHandler(command);
            } while (!command[0].toUpperCase().equals("Q"));

            sc.close();
        }
        // programa executado com um ficheiro de argumentos
        else if (args.length == 1) {
            try {
                File file = new File(args[0]);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    // inputs do file
                    String line = scanner.nextLine();
                    System.out.println(line);

                    String[] command = line.split(" ");
                    CommandsHandler(command);
                }

                scanner.close();
            }
            catch (Exception e) {
                error("Erro na leitura do ficheiro");
            }
        }
        else {
            error("- Argumentos inválidos");
        }

    }

    public static String Menu() {
        String output = "I filename - Ler ficheiro de texto contendo informação sobre um voo\n";
        output += "M flight_code - Exibe o mapa das reservas do voo\n";
        output += "F flight_code num_seats_executive num_seats_tourist - Acrescenta um novo voo\n";
        output += "R flight_code class number_seats - Acrescente uma nova reserva num voo\n";
        output += "C reservation_code - Cancela uma reserva\n";
        output += "Q - Termina o programa\n";
        return output;
    }

    public static void CommandsHandler(String[] args) {
        String command = args[0].toUpperCase();

        switch (command){

            case "H":
                System.out.println(Menu());
                break;
            
            case "I":
                if (args.length != 2) {
                    System.out.println("Número de argumentos inválido\n");
                    break;
                }
                String filename = args[1];
                readVooFromFile(filename, false);
                break;

            case "M":
                if (args.length != 2) {
                    System.out.println("Número de argumentos inválido\n");
                    break;
                }

                String flight_code = args[1];

                if (voos.containsKey(flight_code)) {
                    
                    Voo voo = voos.get(flight_code);
                    voo.planeSeats();
                }
                else {
                    System.out.println("Voo não encontrado\n");
                    break;
                }

                break;

            case "F":
                if (args.length == 4 || args.length == 3) {

                    String args_input = "";
                    
                    for (int i = 1; i < args.length; i++) {
                        args_input += args[i] + " ";
                    }

                    Voo voo = ReadVooHeader(args_input);
                    if (voo != null) {
                        System.out.println("Voo criado com sucesso\n");
                    }
                    voos.put(voo.getCode(), voo);
                }
                else {
                    System.out.println("Número de argumentos inválido\n");
                    break;
                }

                break;

            case "R":
                if (args.length != 4) {
                    System.out.println("Número de argumentos inválido\n");
                    break;
                }
                if (!hasVoo(args[1])) {
                    System.out.println("Voo não encontrado\n");
                    break;
                }

                Voo voo2 = voos.get(args[1]);

                String[] seat = Arrays.copyOfRange(args,2, 4);
                String fail = AddReserve(voo2, seat, true);
                
                if (!fail.equals("")) {
                    System.out.println("Não foi possivel efetuar a reserva: " + fail + "\n");
                }
                break;

            case "C":
                if (args.length != 2) {
                    System.out.println("Número de argumentos inválido\n");
                    break;
                }

                String reservation_code = args[1];
                String[] parametros = reservation_code.split(":");
                int num_reserva = Integer.parseInt(parametros[1]);

                for (Voo voo4 : voos.values()) {
                    if (voo4.getCode().equals(parametros[0])) {
                        if (voo4.cancelReservation(num_reserva)) {
                            System.out.println("Reserva cancelada com sucesso\n");
                        }
                        else {
                            System.out.println("Reserva não encontrada\n");
                        }
                    }
                }
                break;

            case "Q":
                System.out.println("A terminar o programa");
                System.exit(0);
                break;

            default:
                System.out.println("Comando inválido\n");
                break;
        }
    }

    public static void readVooFromFile(String filename, boolean print){
        Voo voo = null;
        ArrayList<String> fail_reserve = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int line_counter = 0;

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                
                if (line_counter == 0){
                    voo = ReadVooHeader(line);
                }
                else {
                    String[] seats = line.split(" ");
                    String fail = AddReserve(voo, seats, print);

                    if (!fail.equals("")) {
                        fail_reserve.add(fail);
                    }
                }
                line_counter++;
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            return;
        }

        // print do voo
        System.out.println(voo.toString());

        for (String fail : fail_reserve) {
            System.out.println("Não foi possivel efetuar a reserva: " + fail);
        }
        System.out.println();
    }

    public static String AddReserve(Voo voo, String[] seats, boolean print){
        String fail = "";
        Classes classe = null;

        if (seats.length != 2) {
            error("Erro na leitura do ficheiro");
        }
        //reservas do ficheiro
        if (seats[0].equals("T")) {
            classe = Classes.TURISTICA;
        }
        else if (seats[0].equals("E")) {
            classe = Classes.EXECUTIVA;
        }
        else {
            error("Erro na leitura do ficheiro");
        }

        if (!voo.reserve(classe, Integer.parseInt(seats[1]), print)) {
            // guardar a reserva que falhou
            fail += seats[0] + " " + seats[1];
        }
        return fail;
    }


    public static Voo ReadVooHeader(String line){
        Voo voo = null;

        Scanner lineScanner = new Scanner(line);
        String flight_code = lineScanner.next();
        if (flight_code.charAt(0) == '>'){
            flight_code = flight_code.substring(1,flight_code.length());
        }

        if (hasVoo(flight_code)) {
            error("Voo já existe");
        }

        String seats1 = lineScanner.next();
        String regex = "(?i)x";

        if (lineScanner.hasNext()){

            String executiveSeats = seats1;
            String touristSeats = lineScanner.next();

            int num_lines_tourist = Integer.parseInt(touristSeats.split(regex)[0]);
            int num_seats_tourist = Integer.parseInt(touristSeats.split(regex)[1]);
            int num_lines_executive = Integer.parseInt(executiveSeats.split(regex)[0]);
            int num_seats_executive = Integer.parseInt(executiveSeats.split(regex)[1]);

            Integer[][] turistica = new Integer[num_lines_tourist][num_seats_tourist];
            Integer[][] executiva = new Integer[num_lines_executive][num_seats_executive];

            Plane plane = new Plane(turistica, executiva);
            voo = new Voo(flight_code, plane);
            voos.put(flight_code, voo);
        }
        else {
            String touristSeats = seats1;

            Integer num_lines_tourist = Integer.parseInt(touristSeats.split(regex)[0]);
            Integer num_seats_tourist = Integer.parseInt(touristSeats.split(regex)[1]);

            Integer[][] turistica = new Integer[num_lines_tourist][num_seats_tourist];
            Integer[][] executiva = new Integer[0][0];

            Plane plane = new Plane(turistica, executiva);
            voo = new Voo(flight_code, plane);
            voos.put(flight_code, voo);
            
        }
        lineScanner.close();
        return voo;
    }

    public static boolean hasVoo(String flight_code){
        if (voos.containsKey(flight_code)) {
            return true;
        }
        return false;
    }

    public static void error(String str) {
        System.err.println("ERROR " + str);
        System.exit(1);
    }
}
