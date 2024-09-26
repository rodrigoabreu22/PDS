package lab09.ex01b.src;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

class Company {

public static User user;
private List<Employee> emps = new ArrayList<>();

	//this method was adapted from the previous admitPerson to accept (Person person, double salary) as arguments and create employees
	public void admitEmployee(Person person, double salary) {
		Employee e = new Employee(person, salary);
		emps.add(e);
	}
	
	public void paySalaries(int month) {
		for (Employee e : emps) {
			BankAccount ba = e.getBankAccount();
			ba.deposit(e.getSalary());
		}
	}
	
	public List<Employee> employees() {
		return Collections.unmodifiableList(emps);
	}
}