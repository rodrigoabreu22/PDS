package lab09.ex02.src;

/**
 * Represents an employee.
 */
class Employee {

	private Person person;
	private double salary;
	private BankAccount bankAccount;
	private EmployeeCard card;

	/**
	 * Constructs an Employee object with the specified person and salary.
	 * Creates a a proxy of the original bank so the company can only access the allowed methods.
	 * But the person has full access to the respective account.
	 *
	 * @param p the person associated with the employee
	 * @param s the salary of the employee
	 */
	public Employee(Person p, double s) {
		person = p;
		salary = s;
		bankAccount = new BankAccountProxy(person.getBankAccount());
	}

	/**
	 * Returns the salary of the employee.
	 *
	 * @return the salary of the employee
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Returns the person associated with the employee.
	 *
	 * @return the person associated with the employee
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Returns the bank account of the employee.
	 *
	 * @return the bank account of the employee
	 */
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	/**
	 * Returns the employee card of the employee.
	 *
	 * @return the employee card of the employee
	 */
	public String getCard() {
		return card.createCard();
	}

	/**
	 * Sets the employee card of the employee.
	 *
	 * @param card the employee card of the employee
	 */
	public void setCard(EmployeeCard card) {
		this.card = card;
	}
}