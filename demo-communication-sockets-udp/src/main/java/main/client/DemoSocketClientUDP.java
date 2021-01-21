package main.client;

import java.net.*;

public class DemoSocketClientUDP {
	DemoSocketClientUDP(int portNr) throws java.io.IOException {
		byte[] empfangenDaten = new byte[1024];
		byte[] sendenDaten = new byte[1024];
		String botschaft;
		// Socket erzeugen
		DatagramSocket socket = new DatagramSocket();
		// Paket erzeugen & adressieren
		InetAddress serverIP = InetAddress.getByName("localhost");
		sendenDaten = "Hello".getBytes();
		DatagramPacket senden = new DatagramPacket(sendenDaten, sendenDaten.length, serverIP, portNr);
		// Paket senden
		socket.send(senden);
		// Antwort empfangen & ausgeben.
		DatagramPacket empfangen = new DatagramPacket(empfangenDaten, empfangenDaten.length);
		socket.receive(empfangen);
		botschaft = new String(empfangen.getData(), 0, empfangen.getLength());
		System.out.println("Erhalten: " + botschaft);
		// Socket schliessen
		socket.close();
	}

	public static void main(String args[]) throws java.io.IOException {
		new DemoSocketClientUDP(10015);
	}
}