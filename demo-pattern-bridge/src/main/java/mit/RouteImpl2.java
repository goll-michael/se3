package mit;

public class RouteImpl2 implements RouteI {
	public int gibEntfernung(int posX, int posY) {
		return posY - posX;
	}
}