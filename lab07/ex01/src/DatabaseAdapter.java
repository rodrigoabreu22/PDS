package ex01.src;

import ex01.src.Employee;

/**
 * This class serves as an adapter for the Database class.
 * It implements the Adapter interface and provides methods to add, remove, and check employees.
 */
public class DatabaseAdapter implements Adapter{
    private Database database;

    /**
     * Constructs a new DatabaseAdapter with the given Database.
     * @param database the Database to be adapted
     */
    public DatabaseAdapter(Database database) {
        this.database = database;
    }

    /**
     * Adds a new employee to the database.
     * @param empregado the employee to be added
     * @return true if the employee was added successfully, false otherwise
     */
    public boolean addEmployee(Empregado empregado) {
        return database.addEmployee(new EmpregadoAdapter(empregado));
    }

    /**
     * Removes an employee from the database.
     * @param codigo the code of the employee to be removed
     */
    public void removeEmployee(int codigo){
        for (Employee employee : database.getAllEmployees()) {
            if (codigo==employee.getEmpNum()){
                database.deleteEmployee(codigo);
                System.out.println("Employee " + employee.getName() + " removed");
                return;
            }
        }
        System.out.println("Employee not found");
    }

    /**
     * Checks if an employee exists in the database.
     * @param codigo the code of the employee to be checked
     * @return true if the employee exists, false otherwise
     */
    public boolean isEmployee(int codigo) {
        for (Employee employee : database.getAllEmployees()) {
            if (codigo==employee.getEmpNum()){
                System.out.println("Employee " + employee.getName() + " found");
                return true;
            }
        }
        System.out.println("Employee not found");
        return false;
    }

    /**
     * Prints a table conatining a list off all employees in the database.
     */
    public void listAllEmployees(){
        System.out.printf("%-20s | %-6s | %-12s \n", "NAME", "CODE", "SALARY");
        for (Employee employee : database.getAllEmployees()){
            System.out.println(employee);
        }
    }

    
}