package muster2_allg_alt;

public class Client {
	public static void main(String[] args) {
		A obj = new B();
		init(obj);

		obj = new C();
		init(obj);

		obj = new D();
		init(obj);
	}

	public static void init(A obj) {
		if (obj instanceof B) {
			obj.init(); // A
			((B) obj).x(); // B
		} else if (obj instanceof C) {
			obj.init(); // A
			((C) obj).y(); // C
		} else if (obj instanceof D) {
			((D) obj).z(); // D
		}
	}
}
