package ex01.src;

import java.util.Date;

/**
 * The TeamLeader class represents a team leader employee.
 * It extends the EmployeeDecorator class and adds the role of "Team Leader" to the employee.
 */
public class TeamLeader extends EmployeeDecorator {

    /**
     * Constructs a new TeamLeader object with the specified employee.
     *
     * @param employee the employee to be assigned as the team leader
     */
    public TeamLeader(EmployeeInterface employee){
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
        return super.work() + plan(); 
    }
    
    /**
     * Plans the work for the team leader employee.
     * Prints a message indicating that the team leader is planning.
     */
    public String plan(){
        return String.format("Team leader employee %s is planning.\n", super.getName());
    }

    public String toString(){
        return super.toString() + " (Team Leader)";
    }
}
