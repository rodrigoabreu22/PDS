package ex01.src;

import ex01.src.Employee;

public class TesterAlineaB {
    public static void main(String[] args) {

        System.out.println("-------------- Testing Alinea B --------------");
        System.out.println();
        
        //Add employees to the database
        Database database = new Database();

        Employee employee1 = new Employee("Marcos Leonardo", 1001, 1000);
        Employee employee2 = new Employee("Tiago Gouveia", 1002, 2000);
        Employee employee3 = new Employee("Samuel Soares", 1003, 3000);

        database.addEmployee(employee1);
        database.addEmployee(employee2);
        database.addEmployee(employee3);

        //Add empregados to registos
        Registos registos = new Registos();

        Empregado empregado1 = new Empregado("David","Neres", 1004, 1000);
        Empregado empregado2 = new Empregado("Angel","Di Maria", 1005, 2000);
        Empregado empregado3 = new Empregado("Casper","Tengstedt", 1006, 3000);
        
        registos.insere(empregado1);
        registos.insere(empregado2);
        registos.insere(empregado3);
        
        DatabaseAdapter adapter = new DatabaseAdapter(database);

        //Print all empregados from registos
        System.out.println("Empregados from Petiscos");
        for (Empregado emp: registos.listaDeEmpregados()){
            System.out.println(emp);
        }

        System.out.println();

        //Print all employees from database
        System.out.println("Employees from Sweets");
        for (Employee emp: database.getAllEmployees()){
            System.out.println(emp);
        }

        System.out.println();

        //Add all empregados from registos to database
        System.out.println("Adding all empregados from Registos to Database");
        for (Empregado emp: registos.listaDeEmpregados()){
            adapter.addEmployee(emp);
        }

        System.out.println();

        System.out.println("----- Company merge between Petiscos and Sweets -------");
        //Database now stores all empregados and employees due tom the company merge
        System.out.println("All employees");
        adapter.listAllEmployees();

        //Adding more empregados and employees to the common database
        Empregado empregado4 = new Empregado("Rui","Costa", 1007, 2000);
        Employee employee4 = new Employee("Arthur Cabral", 1004, 1000);

        adapter.addEmployee(empregado4);
        database.addEmployee(employee4);

        System.out.println();

        //Print all employees from database
        System.out.println("All employees");
        adapter.listAllEmployees();
        
        System.out.println(); 
        
        //Test removing and searching methods
        System.out.println("Testing remove and search methods");

        adapter.isEmployee((int)employee2.getEmpNum());
        adapter.removeEmployee((int)employee2.getEmpNum());
        adapter.isEmployee((int)employee2.getEmpNum());
        adapter.removeEmployee((int)employee3.getEmpNum());
        adapter.isEmployee((int)employee3.getEmpNum());
        adapter.removeEmployee((int)employee1.getEmpNum());
        adapter.removeEmployee((int)employee4.getEmpNum());
        adapter.isEmployee((int)employee1.getEmpNum());

        System.out.println();

        //Print all employees from database
        System.out.println("All employees");
        adapter.listAllEmployees();
        
    }
}
