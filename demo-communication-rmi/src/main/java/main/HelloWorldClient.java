package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloWorldClient {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry();	
			HelloWorld remoteObjekt = (HelloWorld) registry.lookup("HelloWorld");
			remoteObjekt.printHelloWorld();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}