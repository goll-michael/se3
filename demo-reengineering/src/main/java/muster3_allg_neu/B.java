package muster3_allg_neu;

public class B implements Zustand {
	@Override
	public Zustand naechsterZustand() {
		System.out.println("B->C");
		return new C();
	}
}
