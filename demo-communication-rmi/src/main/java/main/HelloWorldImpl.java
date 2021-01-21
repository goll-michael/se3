package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloWorldImpl implements HelloWorld {	
	@Override
	public void printHelloWorld() {
		System.out.println("Hello World");
	}
	
	public static void main(String[] args) {
		try {
			HelloWorld server = new HelloWorldImpl();
			HelloWorld stub = (HelloWorld) UnicastRemoteObject.exportObject((HelloWorld) server, 0);
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("HelloWorld", stub);
			
			System.out.println("Objekt erzeugt, erwarte Aufrufe");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}