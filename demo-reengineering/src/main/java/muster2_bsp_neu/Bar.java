package muster2_bsp_neu;

public class Bar extends Bezahlung {
	public void ausfuehren(double betrag) {
		this.ausgeben();
		this.berechneRabattBar(betrag);
	}

	public void berechneRabattBar(double betrag) {
		System.out.println("Zu zahlender Betrag: " + betrag + " - 10% = " + betrag * 0.9 + " Euro.");
		System.out.println();
	}
}
