package data_class_ref2;

public class MainUI {
	public static void main(String[] args) {
		Person p = new Person();
		p.setName("Schmidt");
		p.setAge(18);

		System.out.println(p.checkGrownUp());
	}
}
