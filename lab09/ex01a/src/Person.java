package lab09.ex01a.src;

class Person {
	
private String name;
private BankAccount bankAccount;

	public Person(String n) {
		name = n;
		bankAccount = new BankAccountProxy(new BankAccountImpl(n, 0)); 
	}

	public String getName() {
		return name;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}
}