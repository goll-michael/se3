package ohne;

public class Client {
	public static enum SORTIERUNG {
		AUSTAUSCHEN, AUSWAHL
	};

	public static SORTIERUNG art;

	public static void main(String[] args) {
		int[] arr1 = { 7, 2, 5, 3, 9, 8, 1, 4, 6 };
		int[] arr2 = { 7, 2, 5, 3, 9, 8, 1, 4, 6 };

		art = SORTIERUNG.AUSTAUSCHEN;
		sortiere(arr1);
		for (int elem : arr1)
			System.out.println("" + elem);

		art = SORTIERUNG.AUSWAHL;
		sortiere(arr2);
		for (int elem : arr2)
			System.out.println("" + elem);
	}

	public static void sortiere(int[] arr) {
		switch (art) {
		case AUSTAUSCHEN:
			StrategieAustauschen bubble = new StrategieAustauschen();
			bubble.sortiere(arr);
			break;
		case AUSWAHL:
			StrategieAuswahl auswahl = new StrategieAuswahl();
			auswahl.sortiere(arr);
			break;
		default:
		}
	}
}
