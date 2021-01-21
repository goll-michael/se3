package beispiel2.client;

import beispiel2.protect.*;

public class Client {
	public static void main(String[] args) {
		PersonalI einProxy = new ProxyPersonal("Sommer", "geheim");
		String[] liste = einProxy.getListe();
		for (int i = 0; i < liste.length; i++)
			System.out.println("Gehaltsliste: " + liste[i]);
		einProxy = new ProxyPersonal("Herb", "geheim");
		liste = einProxy.getListe();
		for (int i = 0; i < liste.length; i++)
			System.out.println("Gehaltsliste: " + liste[i]);
		/*
		 * Umgehen Personal einPersonal = new Personal(); liste =
		 * einPersonal.getListe(); for (int i = 0; i < liste.length; i++)
		 * System.out.println("Gehaltsliste: " + liste[i]);
		 */
	}
}