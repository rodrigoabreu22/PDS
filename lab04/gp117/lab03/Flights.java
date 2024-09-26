import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Flights {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc;
        boolean bool = true;

        // Verificar se foi passado algum argumento
        if (args.length == 1) {
            sc = new Scanner(new File(args[0]));
        } else {
            sc = new Scanner(System.in);
            bool = false;
        }

        while (true) {
            if (bool) {
                if (sc.hasNextLine() == false) {
                    sc = new Scanner(System.in);
                    System.out.println("Escolha uma opção: (H para ajuda)");
                    bool = false;
                }
            } else {
                System.out.println("Escolha uma opção: (H para ajuda)");
            }

            String[] options = sc.nextLine().split("\\s+");
            switch (options.length) {
                case 1: //Verficar opções que apenas precisam de 1 argumentos ("H" e "Q")
                    if(options[0].equalsIgnoreCase("H")) {
                        System.out.println();
                        System.out.println("I 'filename': Lê um ficheiro de texto contedo informação sobre um voo.");
                        System.out.println("M 'fligh_code': Exibe um mapa das reservas de um voo.");
                        System.out.println("F 'flight_code' 'num_seats_executive' 'num_seats_tourist': Acrescenta um novo voo.");
                        System.out.println("R 'flight_code' 'class' 'number_seats': Acrescenta uma nova reserva a um voo.");
                        System.out.println("C 'reservation_code': Cancela uma reserva.");
                        System.out.println("Q: Termina o programa.");
                        System.out.println();
                    } else if (options[0].equalsIgnoreCase("Q")) {
                        System.out.println();
                        System.out.println("O programa vai terminar. Obrigado!");
                        System.out.println();
                        sc.close();
                        System.exit(0);
                    } else {
                        System.out.println();
                        System.out.println("Opção inválida! Tente outra vez.");
                        System.out.println();
                    }
                    break;
                
                case 2: //Verificar opções que precisam de 2 argumentos ("I", "M" e "C")
                    if(options[0].equalsIgnoreCase("I")) {
                        File filename = new File(options[1]);
                        FlightInfo info = new FlightInfo(filename);
                        info.getInfo(filename);

                    } else if (options[0].equalsIgnoreCase("M")) {
                        FlightInfo map = new FlightInfo(options[1]);
                        map.getMap(options[1]);

                    } else if (options[0].equalsIgnoreCase("C")) {
                        FlightInfo info = new FlightInfo(options[1]);
                        if (info.getFlights().containsKey(options[1].split(":")[0])){
                            Flight flight = info.getFlights().get(options[1].split(":")[0]);
                            flight.cancelReservation(options[1]);
                        } else {
                            System.out.println("O voo que pretende encontrar não existe");
                        }

                    } else {
                        System.out.println();
                        System.out.println("Opção inválida! Tente outra vez.");
                        System.out.println();
                    }
                    break;
                case 3: //Verificar opções que precisam de 3 argumentos ("F" se não existirem lugares em classe executiva, já que são opcionais)
                    if(options[0].equalsIgnoreCase("F")) {
                        FlightInfo info = new FlightInfo(options[1]);
                        if (info.getFlights().containsKey(options[1]) == false && options[1].matches("[0-9a-zA-Z]+") && options[2].matches("[1-9][0-9]*x[1-9][0-9]*")) {
                            Flight flight = new Flight(options[1], options[2]);
                            info.getFlights().put(flight.getFlightCode(), flight);
                            System.out.println(info.getFlights());
                        } else {
                            System.out.println("Argumentos inválidos!");
                        }    
                    } else {
                        System.out.println();
                        System.out.println("Opção inválida! Tente outra vez.");
                        System.out.println();
                    }
                    break;

                case 4: //Verificar opções que precisam de 4 argumentos ("F" e "R")
                    if (options[0].equalsIgnoreCase("F")) {
                        FlightInfo info = new FlightInfo(options[1]);
                        if (info.getFlights().containsKey(options[1]) == false && options[1].matches("[0-9a-zA-Z]+") && options[2].matches("[1-9][0-9]*x[1-9][0-9]*") && options[3].matches("[1-9][0-9]*x[1-9][0-9]*")) {
                            Flight flight = new Flight(options[1], options[2], options[3]);
                            info.getFlights().put(flight.getFlightCode(), flight);
                        } else {
                            System.out.println("Argumentos inválidos!");
                        }
                    } else if (options[0].equalsIgnoreCase("R")){
                        FlightInfo info = new FlightInfo(options[1]);
                        if (info.getFlights().containsKey(options[1]) && options[2].matches("[TE]") && options[3].matches("[1-9][0-9]*")) {
                            Flight flight = info.getFlights().get(options[1]);
                            flight.makeReservation(Class.getEnum(options[2]), Integer.parseInt(options[3]));          
                        }
                    } else {
                        System.out.println();
                        System.out.println("Opção inválida! Tente outra vez");
                        System.out.println();
                    }
                    break;

                default:
                    System.out.println();
                    System.out.println("Opção inválida! Tente outra vez");
                    System.out.println();
                    break;
            }
        }
    }
}