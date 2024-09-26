import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FlightInfo {
    private File file = null;
    public Map<String, Flight> flights = new HashMap<>();
    private String s = null;

    public FlightInfo(File f) {
        this.file = f;
    }

    public FlightInfo(String s) {
        this.s = s;
    }

    public void getInfo(File file) throws FileNotFoundException {
        Scanner info = new Scanner(new FileReader(file));
        String[] flightSize = info.nextLine().split("\\s+");
        String flightCode = flightSize[0].split(">")[1];

        //Verificar se o ficheiro é válido (Código do voo na primeira linha) e se o avião contem apenas lugares para classe turística ou também executiva
        if (flightSize[0].startsWith(">") && flightSize.length == 3) {
            if(flights.containsKey(flightCode) == false && flightCode.matches("[0-9a-zA-Z]+") && flightSize[1].matches("[1-9][0-9]*x[1-9][0-9]*") && flightSize[2].matches("[1-9][0-9]*x[1-9][0-9]*")) {
                Flight flight = new Flight(flightCode, flightSize[1], flightSize[2]);
                flights.put(flight.getFlightCode(), flight);
                flight.makeReservation(info);
            } else {
                System.out.println("O voo pretendido ainda não foi criado");
            }
        } else if (flightSize[0].startsWith(">") && flightSize.length == 2) {
            if(flights.containsKey(flightCode) == false && flightCode.matches("[0-9a-zA-Z]+") && flightSize[1].matches("[1-9][0-9]*x[1-9][0-9]*")) {
                Flight flight = new Flight(flightCode, flightSize[1]);
                flights.put(flight.getFlightCode(), flight);
                flight.makeReservation(info);
            } else {
                System.out.println("O voo pretendido ainda não foi criado");
            }
        } else {
            System.out.println("Ficheiro inválido: Não contém o código do voo na primeira linha!");
            return;
        }
    }
    

    public void getMap(String s) {
        System.out.println(s);
        if(flights.containsKey(s) && s.matches("[0-9a-zA-Z]+")) {
            System.out.println(flights.get(s));
        } else {
            System.out.println("Código de voo inválido");
        }
    }

    public Map<String, Flight> getFlights() {
        return this.flights;
    }
}
