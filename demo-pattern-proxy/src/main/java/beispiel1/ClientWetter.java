package beispiel1;

public class ClientWetter {
	public static void main(String[] args) {
		WetterI eineWetterstation = new ProxyWetter();
		System.out.println("Aktuelle Temperatur 1: " + eineWetterstation.getTemperatur());
		System.out.println("Aktuelle Temperatur 2: " + eineWetterstation.getTemperatur());
	}
}