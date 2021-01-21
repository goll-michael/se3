package data_class_ref2;

public class Person {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String checkGrownUp() {
		if (age >= 18)
			return "Alter>=18";
		else
			return "Alter<18";
	}
}
