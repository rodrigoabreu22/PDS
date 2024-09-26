package lab09.ex02.src;
public class SharkCompany2 {
	public static void main(String[] args) {
		Person[] persons = { new Person("Maria Silva"),
			new Person("Manuel Pereira"), 
			new Person("Aurora Machado"), 
			new Person("Augusto Lima") 
		};

		Company shark = new Company();
		Company.user = User.COMPANY;

		shark.admitEmployee(persons[0], 1000);
		shark.admitEmployee(persons[1], 900);
		shark.admitEmployee(persons[2], 1200);
		shark.admitEmployee(persons[3], 1100);

		System.out.println("----Insurance----");
		Insurance sharkInsurance = shark.getInsurance();
		for (Employee e : sharkInsurance.getInsuranceList()){
			System.out.println(e.getCard());
		}

		System.out.println("\n----Parking----");
		Parking sharkParking = shark.getParking();
		for (Employee e : sharkParking.getParkingList()){
			System.out.println(e.getCard());
		}
	}
}