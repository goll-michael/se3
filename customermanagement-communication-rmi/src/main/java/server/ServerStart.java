package server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.application.KundenContainerObjekt;
import server.schnittstelle.KundenContainerObjektI;

public class ServerStart {
	private String serviceName;
	private KundenContainerObjektI service;

	private static Registry registry = null;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws UnknownHostException {
		// Server laeuft auf den lokalen Rechner.
		final ServerStart dataServ = new ServerStart("kundenverwaltung");

		try {
			// RMI-Registry starten.
			registry = LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
		}

		// Server starten
		dataServ.start();

		// Die Methode Server.stop soll beim Beenden des Programms einmal
		// aufgerufen werden, um Daten abzuspeichern.
		Runtime rt = Runtime.getRuntime();
		rt.addShutdownHook(new Thread() {
			@Override
			public void run() {
				dataServ.stop();
				System.out.println("Server wird gestoppt...");
			}
		});

		// Auf Benutzer-Eingabe warten.
		try {
			int i = System.in.read();
		} catch (IOException ioException) {
		}

		System.exit(0);
	}

	public ServerStart(String serviceName) {
		this.serviceName = serviceName;
		service = null;
	}

	public boolean start() {
		try {
			service = new KundenContainerObjekt();
			KundenContainerObjektI stub = (KundenContainerObjektI) UnicastRemoteObject
					.exportObject((KundenContainerObjektI) service, 0);

			// Service registrieren.
			registry.rebind(getServiceName(), stub);

			System.out.println("Anzeigenverwaltung-Server@" + getServiceName() + " bereit...");
			System.out.println("Geben Sie <Return> ein, um den Server abzuschalten.");
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean stop() {
		try {
			service.endeAnwendung();
			UnicastRemoteObject.unexportObject(service, true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Service vom RMI-Registry entfernen.
				registry.unbind(getServiceName());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public String getServiceName() {
		return serviceName;
	}

	public KundenContainerObjektI getService() {
		return service;
	}
}
