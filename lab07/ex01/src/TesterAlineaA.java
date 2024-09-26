package ex01.src;

import ex01.src.Employee;

public class TesterAlineaA {
    public static void main(String[] args){
        //Teste dos Registos
        System.out.println("----------- Petiscos -------------");
        Registos registos = new Registos();
        Empregado empregado1 = new Empregado("Anatoly", "Trubin", 0001, 700);
        Empregado empregado2 = new Empregado("Alexander", "Bah", 0002, 701);
        Empregado empregado3 = new Empregado("Nicolas", "Otamendi", 0003, 600);
        Empregado empregado4 = new Empregado("Antonio", "Silva", 0004, 100);

        registos.insere(empregado1);
        registos.insere(empregado2);
        registos.insere(empregado3);
        registos.insere(empregado4);

        if (registos.isEmpregado(0001)){
            System.out.println("Trubin encontrado");
        }
        registos.remove(001);
        if (!registos.isEmpregado(0001)){
            System.out.println("Trubin não encontrado");
        }

        registos.insere(empregado1);

        System.out.println("Lista de Empregados da empresa Petiscos");
        for (Empregado emp: registos.listaDeEmpregados()){
            System.out.println(emp);
        }

        //Teste do Database
        System.out.println();
        System.out.println("----------- Sweets ------------");
        Database database = new Database();
        Employee employee1 = new Employee("Fredrik Aursnes", 0001, 700);
        Employee employee2 = new Employee("Florentino Luis", 0002, 701);
        Employee employee3 = new Employee("Joao Neves", 0003, 600);
        Employee employee4 = new Employee("Rafa Silva", 0004, 100);

        database.addEmployee(employee1);        
        database.addEmployee(employee2);
        database.addEmployee(employee3);
        database.addEmployee(employee4);

        if (database.hasEmployee(employee1)){
            System.out.println("Aursnes encontrado");
        }
        
        database.deleteEmployee(0001);

        if (!database.hasEmployee(employee1)){
            System.out.println("Aursnes não encontrado");
        }

        database.addEmployee(employee1);

        Employee[] employees = database.getAllEmployees();

        System.out.println("Lista de Employees da empresa Sweets");
        for (Employee emp: employees){
            System.out.println(emp);
        }

        if (!database.addEmployee(employee4)){
            System.out.println("Rafa já existe");
        }
    }
}
