package muster4_bsp_alt;

public class StrategieBubblesort {
	public void sortiere(int[] arr) {
		System.out.println("Bubblesort");
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					int tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}
}
