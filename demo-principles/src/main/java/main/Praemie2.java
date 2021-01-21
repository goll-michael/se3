package main;

import inout.Console;

class Praemie2 {
	public static void main(String args[]) {
		final int grundpraemie1 = 200, grundpraemie2 = 100, zulage1 = 20, zulage2 = 100; // Konstanten
		int dienstjahre, alter; // Eingabe
		int praemie = 0; // Ausgabe
		System.out.println("Dienstjahre?");
		dienstjahre = Console.readInt();
		System.out.println("Alter?");
		alter = Console.readInt();
		if (dienstjahre > 5) {
			assert (dienstjahre > 5) : "Dienstjahre ist nicht > 5";
			praemie = grundpraemie2 + zulage1 * dienstjahre;
			if (alter > 45) {
				assert (dienstjahre > 5 && alter > 50) : "Dienstjahre oder Alter falsch";
				praemie = praemie + zulage2;
			}
		} else // dienstjahre <= 5
		if (dienstjahre > 2) {
			assert (!(dienstjahre > 5)) : "Dienstjahre ist nicht <= 5";
			praemie = grundpraemie1;
		}
		System.out.println("Dienstjahre:\t" + dienstjahre);
		System.out.println("Alter:\t\t" + alter);
		System.out.println("Prämie:\t\t" + praemie);
	}
}