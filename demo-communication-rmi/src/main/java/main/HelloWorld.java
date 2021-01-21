package main;

import java.rmi.*;

public interface HelloWorld extends Remote {
	void printHelloWorld() throws RemoteException;
}
