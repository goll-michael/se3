package whitebox;

import inout.Console;

//Alle Methoden aus DVDSpieler sind in dieser Klasse bekannt
public class FernbedienungDVDSpieler {
	public static void main(String args[]) {
		System.out.print("DVDSpieler einschalten (e)oder ausschalten (a): ");
		char eingabe = Console.readChar();
		DVDSpieler einDVDSpieler = new DVDSpieler();
		if (eingabe == 'e')
			einDVDSpieler.einschaltenDVDSpieler();
		else if (eingabe == 'a')
			einDVDSpieler.ausschaltenDVDSpieler();
	}
}