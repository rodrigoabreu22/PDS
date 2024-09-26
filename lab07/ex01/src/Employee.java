package ex01.src;

// Sweets
class Employee {
    private String name;
    private long emp_num;
    private double salary;

    public Employee(String name, long emp_num, double salary) {
        this.name = name;
        this.emp_num = emp_num;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public long getEmpNum() {
        return emp_num;
    }
    
    public double getSalary() {
        return salary;
    }

    public String toString() {

        return String.format("%-20s | %-6d | %-10.2f", name, emp_num, salary);
    }
}