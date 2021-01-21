package muster3_allg_neu;

public class A implements Zustand {
	@Override
	public Zustand naechsterZustand() {
		System.out.println("A->B");
		return new B();
	}
}
