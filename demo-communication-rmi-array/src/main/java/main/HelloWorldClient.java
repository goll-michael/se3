package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloWorldClient {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry();
			HelloWorld remoteObjekt = (HelloWorld) registry.lookup("HelloWorld");
			remoteObjekt.printHelloWorld();
			int value = remoteObjekt.addition(new int[] { 1, 2, 3 });
			System.out.println(value);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}