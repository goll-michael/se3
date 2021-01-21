package mit;

public class Kontext {
	private Strategie meineStrategie;

	public Kontext(Strategie eineStrategie) {
		meineStrategie = eineStrategie;
	}

	public void ausfuehrenSortierung(int[] arr) {
		meineStrategie.sortiere(arr);
	}
}
