package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class StartServerUI extends Thread {

	private static final int PORT_NUMBER = 4444;
	
	ServerSocket serverSocket = null;
	
    public StartServerUI() {
    	try {
    		serverSocket = new ServerSocket(PORT_NUMBER);
			System.out.println("Server started...");
		} catch (IOException e) {
			System.out.println("Port not available: " + PORT_NUMBER);
		}
    }

    public void run() {
    	boolean running = true;
    	ServerProtokoll protokoll = new ServerProtokoll();

    	Socket clientSocket = null;
        while (running) {
        	try {
	            clientSocket = serverSocket.accept();
		        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
		        String input, output;
		        while ((input = in.readLine()) != null) 
		        {
		        	output = protokoll.processInput(input);
		            out.println(output);
		            if ("close".equals(output)) {
		            	running = false;
		            }
		        }
	        	out.close();
				in.close();
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("error reading");
			}
        }
    }
    
    public static void main(String[] args) throws SocketException  {
    	new StartServerUI().start();
    }
}