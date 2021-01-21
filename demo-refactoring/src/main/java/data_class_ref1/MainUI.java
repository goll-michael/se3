package data_class_ref1;

public class MainUI {
	public static void main(String[] args) {
		Person p = new Person();
		p.setName("Schmidt");
		p.setAge(18);

		if (p.checkGrownUp()) {
			System.out.println("Alter>=18");
		} else {
			System.out.println("Alter<18");
		}
	}
}
