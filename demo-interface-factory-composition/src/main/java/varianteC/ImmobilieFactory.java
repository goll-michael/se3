package varianteC;

public class ImmobilieFactory extends ImmobilieCreate {
	public ImmobilieIUse erzeugeImmobilie(char art) {
		return new ImmobilieImpl(art);
	}
}
