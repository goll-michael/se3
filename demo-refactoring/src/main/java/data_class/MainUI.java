package data_class;

public class MainUI {
	public static void main(String[] args) {
		Person p = new Person();
		p.setName("Schmidt");
		p.setAge(16);

		if (p.getAge() >= 18) {
			System.out.println("Alter>=18");
		} else {
			System.out.println("Alter<18");
		}
	}
}
