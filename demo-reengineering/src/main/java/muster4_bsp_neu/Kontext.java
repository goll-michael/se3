package muster4_bsp_neu;

public class Kontext {
	private Strategie meineStrategie;

	public Kontext(Strategie eineStrategie) {
		meineStrategie = eineStrategie;
	}

	public void ausfuehrenSortierung(int[] arr) {
		meineStrategie.sortiere(arr);
	}
}
