package muster2_bsp_alt;

public class Bar extends Bezahlung {
	public void berechneRabattBar(double betrag) {
		System.out.println("Zu zahlender Betrag: " + betrag + " - 10% = " + betrag * 0.9 + " Euro.");
		System.out.println();
	}
}
