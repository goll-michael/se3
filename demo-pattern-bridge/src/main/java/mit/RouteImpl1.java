package mit;

public class RouteImpl1 implements RouteI {
	public int gibEntfernung(int posX, int posY) {
		return (posY - posX) * 2;
	}
}
