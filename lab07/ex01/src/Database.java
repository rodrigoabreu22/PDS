package ex01.src;

import java.util.Vector;

import ex01.src.Employee;

class Database { // Data elements
    private Vector<Employee> employees; // Stores the employees
    public Database() {
        employees = new Vector<>();
    }
    public boolean addEmployee(Employee employee) {
    // Code to add employee
        if (!hasEmployee(employee)){
            employees.add(employee);
            return true;
        }
        return false;
    }
    public void deleteEmployee(long emp_num) {
    // Code to delete employee
        for (Employee emp: employees){
            if (emp.getEmpNum()==emp_num){
                employees.remove(emp);
                return;
            }
        }
        System.out.printf("O employee com o código %d não existe.\n", emp_num);
    }
    public Employee[] getAllEmployees() {
    // Code to retrieve collection
        Employee[] employee_list = new Employee[employees.size()];
        for (int i=0; i<employees.size(); i++){
            employee_list[i]=employees.get(i);
        }
        return employee_list;
    }

    public boolean hasEmployee(Employee employee){
        for (Employee emp: employees){
            if (employee==emp){
                return true;
            }
        }
        return false;
    }
}