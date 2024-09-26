package lab09.ex02.src;
import java.util.ArrayList;
import java.util.List;

public class Insurance {
    private List<Employee> insuranceList;

    public Insurance() {
        insuranceList = new ArrayList<>();
    }

    public void regist(Employee e) {
        insuranceList.add(e);
    }

    public List<Employee> getInsuranceList() {
        return insuranceList;
    }
}
