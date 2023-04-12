package server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.CustomerContainerI;
import server.application.CustomerContainer;

public class StartServerUI  {

	private String serviceName;
	private CustomerContainerI customerContainer;

	private static Registry registry = null;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws UnknownHostException {
		// Server laeuft auf den lokalen Rechner.
		final StartServerUI dataServ = new StartServerUI("customermanagement");

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

	public StartServerUI(String serviceName) {
		this.serviceName = serviceName;
		customerContainer = null;
	}

	public boolean start() {
		try {
			customerContainer = new CustomerContainer();
			CustomerContainerI stub = (CustomerContainerI) UnicastRemoteObject
					.exportObject((CustomerContainerI) customerContainer, 0);

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
			customerContainer.close();
			UnicastRemoteObject.unexportObject(customerContainer, true);
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

	public CustomerContainerI getService() {
		return customerContainer;
	}
}