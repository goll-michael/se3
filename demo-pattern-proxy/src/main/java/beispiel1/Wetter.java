package beispiel1;

public class Wetter implements WetterI {
	public Wetter() {
	}

	public double getTemperatur() {
		return (Math.floor(Math.random() * 101));
	}
}