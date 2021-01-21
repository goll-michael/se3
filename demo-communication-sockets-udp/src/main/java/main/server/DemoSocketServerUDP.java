package main.server;

import java.net.*;

public class DemoSocketServerUDP {
	DemoSocketServerUDP(int portNr) throws java.io.IOException {
		// Speicherplatz für Pakete vorbereiten
		byte[] empfangenDaten = new byte[1024];
		byte[] sendenDaten = new byte[1024];
		String botschaft;
		// Socket binden
		DatagramSocket socket = new DatagramSocket(portNr);
		System.out.println("Start des Servers");
		while (true) {
			// Ein Paket empfangen
			DatagramPacket empfangen = new DatagramPacket(empfangenDaten, empfangenDaten.length);
			socket.receive(empfangen);
			// Infos ermitteln & ausgeben
			InetAddress senderIP = empfangen.getAddress();
			int senderPort = empfangen.getPort();
			botschaft = new String(empfangen.getData(), 0, empfangen.getLength());
			System.out.println("Erhalten: " + botschaft + " von " + senderIP + " Port: " + senderPort);
			// Antwort erzeugen
			sendenDaten = "Hello Server World".getBytes();
			DatagramPacket senden = new DatagramPacket(sendenDaten, sendenDaten.length, senderIP, senderPort);
			socket.send(senden); // Antwort senden
		}
	}

	public static void main(String args[]) throws java.io.IOException {
		new DemoSocketServerUDP(10015);
	}
}