package ohne;

public class Route {
	private RouteImpl link = null;

	public int ermittleRoute(String ortA, String ortB) {
		// Ermittle GPS-Koordinaten zu ortA
		int posA = 100;
		// Ermittle GPS-Koordinaten zu ortB
		int posB = 200;
		link = new RouteImpl();
		return link.gibEntfernung(posA, posB);
	}
}
