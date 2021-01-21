package main.client;

import java.io.*;
import java.net.*;

public class DemoSocketClient {
	public static void main(String[] args) throws java.io.IOException {
		Socket server = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// Neues Socket-Objekt erzeugen
			server = new Socket("localhost", 4445);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			out = new PrintWriter(server.getOutputStream(), true);
			System.out.println("Start des Client");
			// Daten an den Server übertragen
			out.println("Hallo");
			out.println("World");
			// Ergebnis vom Server empfangen
			String ergebnis = in.readLine();
			System.out.println("Ergebnis vom Server: " + ergebnis);
		} catch (UnknownHostException e) {
			System.err.println("Der Server ist unbekannt");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Keine Verbindung zum Server herstellbar");
			System.exit(1);
		} finally {
			if (server != null)
				try {
					out.close();
					in.close();
					server.close();
					System.out.println("Ende des Client");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}