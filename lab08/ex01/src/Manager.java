package ex01.src;

import java.util.Date;

/**
 * The Manager class represents a manager employee.
 * It extends the EmployeeDecorator class and adds the role "Manager" to the employee.
 */
public class Manager extends EmployeeDecorator{

    /**
     * Constructs a new Manager object with the specified employee.
     *
     * @param employee the employee to be associated with the manager
     */
    public Manager(EmployeeInterface employee){
        super(employee);
    }

    @Override
    public void start(Date date) {
        super.start(date);
    }

    @Override
    public void terminate(Date date) {
        super.terminate(date);
    }

    @Override
    public String work() {
        return super.work()+manage();
    }
    
    /**
     * Manages the tasks of the manager employee.
     * Prints a message indicating that the manager is managing.
     */
    public String manage(){
       return String.format("Manager employee %s is managing.\n", super.getName());
    }

    public String toString(){
        return super.toString()+" (Manager)";
    }
}
