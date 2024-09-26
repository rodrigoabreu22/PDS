package lab09.ex02.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Company {
	public static User user;
	private List<Employee> emps = new ArrayList<>();
	private Parking parking = new Parking();
	private Insurance insurance = new Insurance();
	private SocialSecurity socialSecurity = new SocialSecurity();

	public void admitEmployee(Person person, double salary) {
		Employee e = new Employee(person, salary);
		emps.add(e);
		insurance.regist(e);
		socialSecurity.regist(e);
		EmployeeCard ec = new EmployeeCard(e);
		e.setCard(ec);

		if (e.getSalary() > avaregeSalary()) {
			parking.allow(e);
		}
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public SocialSecurity getSocialSecurity() {
		return socialSecurity;
	}

	public Parking getParking() {
		return parking;
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

	private double avaregeSalary() {
		double sum = 0;
		for (Employee e : emps) {
			sum += e.getSalary();
		}
		return sum / emps.size();
	}
}