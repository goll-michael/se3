package mit;

public class RouteFussgaenger extends Route {
	public int ermittleRoute(String ortA, String ortB) {
		// Ermittle GPS-Koordinaten zu ortA
		int posA = 100;
		// Ermittle GPS-Koordinaten zu ortB
		int posB = 200;
		link = new RouteImpl2();
		return link.gibEntfernung(posA, posB);
	}
}
