package blackbox;

import java.util.List;

public interface IFassade {
	List<IGeraet> getGeraete();

	void ausfuehrenFunktion(int index, char funktion);
}
