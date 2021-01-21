package muster2_bsp_neu;

public class Vorkasse extends Bezahlung {
	public void ausfuehren(double betrag) {
		this.ausgeben();
		this.berechneRabattVorkasse(betrag);
	}

	public void berechneRabattVorkasse(double betrag) {
		System.out.println("Zu zahlender Betrag: " + betrag + " - 5% = " + betrag * 0.95 + " Euro.");
		System.out.println();
	}
}
