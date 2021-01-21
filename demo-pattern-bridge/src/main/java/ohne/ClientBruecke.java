package ohne;

public class ClientBruecke {
	public static void main(String args[]) {
		Route meineRoute = new Route();
		int km = meineRoute.ermittleRoute("DO", "M");
		System.out.println("Entfernung " + km);
	}
}
