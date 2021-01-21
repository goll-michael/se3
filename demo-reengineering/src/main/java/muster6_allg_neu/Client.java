package muster6_allg_neu;

public class Client {
	public static void main(String[] args) {
		// Plugin A
		Manager.getObjektreferenz().findePlugin("A");

		// Plugin B
		Manager.getObjektreferenz().findePlugin("B");
	}
}
