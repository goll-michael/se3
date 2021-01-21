package mit;

public class ClientBruecke {
	public static void main(String args[]) {
		Route meineRoute = new RouteFussgaenger();
		int km = meineRoute.ermittleRoute("DO", "M");
		System.out.println("Entfernung Fu�g�nger: " + km);
		meineRoute = new RouteAutofahrer();
		km = meineRoute.ermittleRoute("DO", "M");
		System.out.println("Entfernung Autofahrer: " + km);
	}
}
