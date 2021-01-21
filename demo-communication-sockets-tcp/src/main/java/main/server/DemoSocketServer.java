package main.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoSocketServer {
	private ServerSocket listenSocket;

	public DemoSocketServer(int port) throws IOException {
		// Socket reservieren
		listenSocket = new ServerSocket(port);
	}

	private void starteDienstleistung() {
		System.out.println("Start des Servers");
		while (true) {
			Socket commSocket = null;
			try {
				// Auf Verbindungswunsch warten
				commSocket = listenSocket.accept();
				kommunizieren(commSocket);
			} catch (IOException e) {
				System.out.println("Accept fehlgeschlagen");
				System.exit(-1);
			} finally {
				if (commSocket != null)
					try {
						commSocket.close();
						System.out.println("Ende des Servers");
					} catch (IOException e) {
						e.printStackTrace();
					}
			} // end finally
		} // end while
	}

	private void kommunizieren(Socket commSocket) throws IOException {
		// Ströme vorbereiten
		BufferedReader in = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
		PrintWriter out = new PrintWriter(commSocket.getOutputStream(), true);
		// Eine Zeile lesen
		String nachricht1 = in.readLine();
		System.out.println("Server - Erhielt Nachricht: " + nachricht1);
		// Weitere Zeile lesen
		String nachricht2 = in.readLine();
		System.out.println("Server - Erhielt Nachricht: " + nachricht2);
		// Zeile schreiben
		out.println(nachricht1 + " Server " + nachricht2);
		System.out.println("Server - Sendete Nachricht: " + nachricht1 + " Server " + nachricht2);
		out.close();
		in.close();
	}

	public static void main(String[] args) throws IOException {
		DemoSocketServer server = new DemoSocketServer(4445);
		server.starteDienstleistung();
	}
}