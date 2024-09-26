package lab09.ex02.src;
public class EmployeeCard {
    private Employee employee;

    public EmployeeCard(Employee e) {
        employee = e;
    }

    public String createCard() {
        return "Employee: " + employee.getPerson().getName() + "\n" +
               "Salary: " + employee.getSalary();
    }
}
