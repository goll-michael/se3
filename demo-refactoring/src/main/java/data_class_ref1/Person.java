package data_class_ref1;

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

	public boolean checkGrownUp() {
		if (age >= 18)
			return true;
		else
			return false;
	}
}
