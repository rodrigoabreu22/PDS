package ex01.src;
import java.util.Date;

public interface EmployeeInterface {
    public void start(Date date);
    public void terminate(Date date);
    public String work();
    public String getName();
    public String toString();
}
