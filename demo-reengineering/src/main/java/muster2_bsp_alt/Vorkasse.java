package muster2_bsp_alt;

public class Vorkasse extends Bezahlung {
	public void berechneRabattVorkasse(double betrag) {
		System.out.println("Zu zahlender Betrag: " + betrag + " - 5% = " + betrag * 0.95 + " Euro.");
		System.out.println();
	}
}
