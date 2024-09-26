package lab04.Ex2;

public class Menu {

    /**
     * Returns all options available in the menu
     * @return menu (String)
     */
    public static String menu() {
        String output = "I filename - Ler ficheiro de texto contendo informação sobre um voo\n";
        output += "M flight_code - Exibe o mapa das reservas do voo\n";
        output += "F flight_code num_seats_executive num_seats_tourist - Acrescenta um novo voo\n";
        output += "R flight_code class number_seats - Acrescente uma nova reserva num voo\n";
        output += "C reservation_code - Cancela uma reserva\n";
        output += "Q - Termina o programa\n";
        return output;
    }
}
