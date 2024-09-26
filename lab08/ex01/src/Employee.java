package ex01.src;

import java.util.Date;
/**
 * The Employee class represents an employee in a company.
 * It implements the EmployeeInterface.
 */
public class Employee implements EmployeeInterface{
    private String name;
    private Date startDate;
    private Date endDate;

    /**
     * Constructs an Employee object with the given name.
     * @param name the name of the employee
     */
    public Employee(String name) {
        this.name = name;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Sets the start date of the employee.
     * @param date the start date
     */
    public void start(Date date) {
        this.startDate = date;
        System.out.println("Start date is " + this.startDate);
    }

    /**
     * Sets the end date of the employee.
     * @param date the end date
     */
    public void terminate(Date date) {
        this.endDate = date;
        System.out.println("End date is " + this.endDate);
    }

    /**
     * Performs the work of the employee.
     * 
     * @return a string representing the work being done by the employee.
     */
    public String work() {
        String work = this.getName() + " is working.\n";
        return work;
    }

    /**
     * Returns the name of the employee.
     * @return the name of the employee
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a string representation of the Employee object.
     *
     * @return a string representation of the Employee object.
     */
    public String toString(){
        return "Empregado " + this.getName();
    }

}