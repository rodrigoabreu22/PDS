public class Reservation {
    private String reservationCode;
    private int num;
    private Class classe;

    public Reservation(String flightCode, int reservationNum, Class classe) {
        this.reservationCode = flightCode + ":" + reservationNum;
        this.num = reservationNum;
        this.classe = classe;
    }

    public String getResCode() {
        return reservationCode;
    }

    public Class getClasse() {
        return classe;
    }

    public int getReservationNum() {
        return num;
    }
}