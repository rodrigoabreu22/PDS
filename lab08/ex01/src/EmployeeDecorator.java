package ex01.src;

import java.util.Date;

/**
 * The EmployeeDecorator class is a decorator for the EmployeeInterface.
 * It allows additional functionality to be added to an existing Employee object.
 */
public class EmployeeDecorator implements EmployeeInterface{
    private EmployeeInterface wrappee;

    /**
     * Constructs a new EmployeeDecorator object.
     * @param wrappee the EmployeeInterface object to be decorated
     */
    public EmployeeDecorator(EmployeeInterface wrappee){
        this.wrappee=wrappee;
    }

    @Override
    public void start(Date date) {
        wrappee.start(date);
    }

    @Override
    public void terminate(Date date) {
        wrappee.terminate(date);
    }

    @Override
    public String work() {
        return wrappee.work(); 
    }

    @Override
    public String getName() {
        return wrappee.getName();
    }

    @Override
    public String toString(){
        return wrappee.toString();
    }

}
