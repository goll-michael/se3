package blackbox;

import java.util.ArrayList;
import java.util.List;

public class Fassade implements IFassade {
	private List<IGeraet> geraete = new ArrayList<IGeraet>();

	// Weitere Geraete koennen hinzugefuegt werden, ohne dass die Klasse
	// FernbedienungUniversal
	// veraendert werden muss.
	public Fassade() {
		geraete.add(new DVDSpieler());
		geraete.add(new Fernseher());
		geraete.add(new Radio());
	}

	public List<IGeraet> getGeraete() {
		return this.geraete;
	}

	public void ausfuehrenFunktion(int index, char funktion) {
		IGeraet geraet = this.geraete.get(index - 1);
		if (funktion == 'e')
			geraet.einschalten();
		else if (funktion == 'a')
			geraet.ausschalten();
	}
}
