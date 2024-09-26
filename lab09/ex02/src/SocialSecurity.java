package lab09.ex02.src;
import java.util.List;
import java.util.ArrayList;

public class SocialSecurity {
    private List<Employee> socialSecurityList;

    public SocialSecurity() {
        socialSecurityList = new ArrayList<>();
    }

    public void regist(Employee e) {
        socialSecurityList.add(e);
    }

    public List<Employee> getSocialSecurityList() {
        return socialSecurityList;
    }
}
