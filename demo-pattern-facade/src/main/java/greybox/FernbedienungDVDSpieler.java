package greybox;

import inout.Console;

//Die Schnittstelle IDVDSpieler gibt die Methoden vor
public class FernbedienungDVDSpieler {
	public static void main(String args[]) {
		System.out.print("DVDSpieler einschalten (e)oder ausschalten (a): ");
		char eingabe = Console.readChar();
		IDVDSpieler einDVDSpieler = FernbedienungFactory.getDVDSpieler();
		if (eingabe == 'e')
			einDVDSpieler.einschaltenDVDSpieler();
		else if (eingabe == 'a')
			einDVDSpieler.ausschaltenDVDSpieler();
	}
}