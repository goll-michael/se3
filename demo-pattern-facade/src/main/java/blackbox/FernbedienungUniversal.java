package blackbox;

import inout.Console;

//Alternativ zu der FactoryFassade koennte Reflection eingesetzt werden.
//Wird in Fassade ein weiteres Geraet hinzugefuegt, dann tangiert es diese Klasse nicht.
public class FernbedienungUniversal {
	public static void main(String args[]) {
		IFassade fassade = FactoryFassade.getFassade();
		System.out.println("Bitte w�hlen Sie das Ger�t");
		int cnt = 1;
		for (IGeraet g : fassade.getGeraete()) {
			System.out.println("(" + cnt + ") " + g.getClass().getSimpleName());
			cnt++;
		}
		int auswahlGeraet = Console.readInt();
		System.out.print("Ger�t einschalten (e) oder ausschalten (a): ");
		char auswahlFunktion = Console.readChar();
		fassade.ausfuehrenFunktion(auswahlGeraet, auswahlFunktion);
	}
}