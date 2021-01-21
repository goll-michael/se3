package greybox;

import inout.Console;

public class FernbedienungRadio {
	public static void main(String args[]) {
		System.out.print("Radio einschalten (e)oder ausschalten (a): ");
		char eingabe = Console.readChar();
		IRadio einRadio = FernbedienungFactory.getRadio();
		if (eingabe == 'e')
			einRadio.einschaltenRadio();
		else if (eingabe == 'a')
			einRadio.ausschaltenRadio();
	}
}