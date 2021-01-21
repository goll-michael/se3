package ohne;

public class StrategieAuswahl {
	public void sortiere(int[] arr) {
		System.out.println("Auswahl");
		int posMin, pos;
		for (int i = 0; i < arr.length; i++) {
			posMin = i;
			pos = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < pos) {
					posMin = j;
					pos = arr[posMin];
				}
			}
			int tmp = arr[i];
			arr[i] = arr[posMin];
			arr[posMin] = tmp;
		}
	}
}