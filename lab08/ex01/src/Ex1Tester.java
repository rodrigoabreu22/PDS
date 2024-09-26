package ex01.src;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Ex1Tester {
    public static void main(String[] args) throws ParseException{

        //Creating two dates representing the contract start and contract end
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date1_str = "16-04-2024";
        String date2_str = "16-04-2025";
        Date date1 = formatter.parse(date1_str);
        Date date2 = formatter.parse(date2_str);

        //Test employee 1 
        System.out.println("-------------- Testar o primeiro empregado: -----------------");
        Employee employee1 = new Employee("Cristiano Ronaldo");

        System.out.println(employee1);
        System.out.println();
        
        employee1.start(date1);
        System.out.print(employee1.work());
        employee1.terminate(date2);

        TeamLeader tleader1 = new TeamLeader(employee1);
        System.out.println(tleader1);

        System.out.print(tleader1.work());
        

        System.out.println();
        
        //Test Employee2
        System.out.println("----------- Testar o segundo empregado: ---------------");

        Employee employee2 = new Employee("Roberto Martinez");
        Manager manager1 = new Manager(employee2);
        TeamLeader tLeader1 = new TeamLeader(manager1);

        System.out.println(tLeader1);
        System.out.println();

        tLeader1.start(date1);
        System.out.print(tLeader1.work());
        tLeader1.terminate(date2);

        System.out.println();


        //Test Employee 3
        System.out.println("----------- Testar o terceiro empregado: ---------------");

        Employee employee3 = new Employee("Bernardo Silva");
        TeamLeader team_leader3 = new TeamLeader(employee3);
        Manager manager2 = new Manager(team_leader3);
        TeamMember team_member1 = new TeamMember(manager2);

        System.out.println(team_member1);
        System.out.println();

        tLeader1.start(date1);
        System.out.print(team_member1.work());
        tLeader1.terminate(date2);

        System.out.println();
        
    }
}
