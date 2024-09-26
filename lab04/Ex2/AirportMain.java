package lab04.Ex2;

import java.io.File;
import java.util.Scanner;

public class AirportMain { 
    public static void main(String[] args) {
        System.out.println("Sistema de gestão de voos");

        // instanciar classes
        CommandHandler commandHandler = new CommandHandler();

        // programa executado sem argumentos
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            String[] command;

            // ciclo para ler comandos
            do {
                System.out.println("Escolha uma opção: (H para ajuda)");
                command = sc.nextLine().split(" ");
                commandHandler.CommandsHandler(command);
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
                    commandHandler.CommandsHandler(command);
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

    public static void error(String str) {
        System.err.println("ERROR " + str);
        System.exit(1);
    }
}