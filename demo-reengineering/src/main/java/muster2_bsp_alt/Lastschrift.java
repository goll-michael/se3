package muster2_bsp_alt;

public class Lastschrift extends Bezahlung {
	public void berechneRabattLastschrift(double betrag) {
		System.out.println("Zu zahlender Betrag: " + betrag + " - 0% = " + betrag + " Euro.");
		System.out.println();
	}
}
