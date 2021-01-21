package mit;

public class Client {
	public static void main(String[] args) {
		Kontext ctx;
		int[] arr1 = { 7, 2, 5, 3, 9, 8, 1, 4, 6 };
		int[] arr2 = { 7, 2, 5, 3, 9, 8, 1, 4, 6 };

		ctx = new Kontext(new StrategieAustauschen());
		ctx.ausfuehrenSortierung(arr1);
		for (int elem : arr1)
			System.out.println("" + elem);

		ctx = new Kontext(new StrategieAuswahl());
		ctx.ausfuehrenSortierung(arr2);
		for (int elem : arr2)
			System.out.println("" + elem);
	}
}
