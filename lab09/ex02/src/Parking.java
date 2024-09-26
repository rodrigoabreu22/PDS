package lab09.ex02.src;
import java.util.List;
import java.util.ArrayList;

public class Parking {
    private List<Employee> parkingList;

    public Parking() {
        parkingList = new ArrayList<>();
    }

    public void allow(Employee e) {
        parkingList.add(e);
    }

    public List<Employee> getParkingList() {
        return parkingList;
    }
}
