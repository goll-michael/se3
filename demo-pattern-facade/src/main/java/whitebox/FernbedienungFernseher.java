package whitebox;

import inout.Console;

public class FernbedienungFernseher {
	public static void main(String args[]) {
		System.out.print("Fernseher einschalten (e)oder ausschalten (a): ");
		char eingabe = Console.readChar();
		Fernseher einFernseher = new Fernseher();
		if (eingabe == 'e')
			einFernseher.einschaltenFernseher();
		else if (eingabe == 'a')
			einFernseher.ausschaltenFernseher();
	}
}
