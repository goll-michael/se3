package mit;

public abstract class Route {
	protected RouteI link = null;

	public abstract int ermittleRoute(String ortA, String ortB);
}
