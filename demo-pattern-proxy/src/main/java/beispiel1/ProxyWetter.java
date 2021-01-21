package beispiel1;

public class ProxyWetter implements WetterI {
	Wetter eineWetterstation;

	public ProxyWetter() {
	}

	public double getTemperatur() {
		eineWetterstation = new Wetter();
		double eineTemperaturInFahrenheit = eineWetterstation.getTemperatur();
		return (eineTemperaturInFahrenheit - 32) * 5 / 9;
	}
}