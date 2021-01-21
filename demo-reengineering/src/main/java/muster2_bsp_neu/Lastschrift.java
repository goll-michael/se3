package muster2_bsp_neu;

public class Lastschrift extends Bezahlung {
	public void ausfuehren(double betrag) {
		this.berechneRabattLastschrift(betrag);
	}

	public void berechneRabattLastschrift(double betrag) {
		System.out.println("Zu zahlender Betrag: " + betrag + " - 0% = " + betrag + " Euro.");
		System.out.println();
	}
}
