package muster3_allg_neu;

public class C implements Zustand {
	@Override
	public Zustand naechsterZustand() {
		System.out.println("C");
		return new C();
	}
}
