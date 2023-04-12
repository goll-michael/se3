package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class StartServerUI extends Thread {

	private static final int PORT_NUMBER = 4444;
	
	private DatagramSocket serverSocket = null;
	private byte[] buffer = new byte[1024];
	
    public StartServerUI() {
    	try {
    		serverSocket = new DatagramSocket(PORT_NUMBER);
			System.out.println("Server started...");
		} catch (SocketException e) {
			System.out.println("Port not available: " + PORT_NUMBER);
		}
    }

    public void run() {
    	boolean running = true;
    	ServerProtokoll protokoll = new ServerProtokoll();
        while (running) {
        	try {
        		DatagramPacket datagramPacketInput = new DatagramPacket(buffer, buffer.length);
        		serverSocket.receive(datagramPacketInput);
    			String input = new String(datagramPacketInput.getData(), 0, datagramPacketInput.getLength());
    			String output = protokoll.processInput(input);
    			DatagramPacket datagramPacketOutput = new DatagramPacket(output.getBytes(), output.getBytes().length, datagramPacketInput.getAddress(), datagramPacketInput.getPort());
    			serverSocket.send(datagramPacketOutput);
    			if ("close".equals(output)) {
	            	running = false;
	            }
			} catch (IOException e) {
				System.out.println("error reading");
			}
        }
    }
    
    public static void main(String[] args) throws SocketException  {
    	new StartServerUI().start();
    }
}