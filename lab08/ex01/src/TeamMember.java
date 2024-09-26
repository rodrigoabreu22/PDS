package ex01.src;

import java.util.Date;

/**
 * The TeamMember class represents a team member employee.
 * It extends the EmployeeDecorator class and adds the role "Team Member" to the employee.
 */
public class TeamMember extends EmployeeDecorator{

    /**
     * Represents a team member.
     * Inherits from the Employee class.
     */
    public TeamMember (EmployeeInterface employee){
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
        return super.work()+colaborate();
    }
    
    /**
     * Performs collaboration as a team member.
     * Prints a message indicating that the team member is collaborating.
     */
    public String colaborate(){
        return String.format("Team Member employee %s is collaborating.\n", super.getName());
    }

    public String toString(){
        return super.toString() + " (Team Member)";
    }
}
