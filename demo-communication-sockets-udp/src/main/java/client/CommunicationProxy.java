package client;

import static common.CommandConstants.CMD_CLOSE;
import static common.CommandConstants.CMD_CREATE;
import static common.CommandConstants.CMD_GETCUSTOMERNAME;
import static common.CommandConstants.CMD_GETNEXTNUMBER;
import static common.CommandConstants.SUCCESS;
import static common.CommandConstants.getCommandArguments;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import common.CustomerContainerI;

public class CommunicationProxy implements CustomerContainerI
{
	private static CommunicationProxy serverProxy = null;
	
	private static final String SERVER_NAME = "localhost";
	private static final int PORT_NUMBER = 4444;
	
//	private PrintWriter out;
//	private BufferedReader in;
//	private Socket socket;
	private DatagramSocket socket;
    
	private byte[] buffer = new byte[1024];
	
	public CommunicationProxy()
	{
		socket = null;
        try 
        {
        	socket = new DatagramSocket();
        } 
        catch (IOException e) 
        {
        	System.out.println("could not create connection" + SERVER_NAME);
        }
        
	}

	public static CommunicationProxy getReference()
	{
		if (serverProxy == null)
			serverProxy = new CommunicationProxy();
		return serverProxy;
	}
	
	public void create(String name, int number) 
	{
		send(String.format(CMD_CREATE, number, name));
	}

	public int getNextNumber() 
	{
		String response = send(CMD_GETNEXTNUMBER);
		if(response.startsWith(SUCCESS))
		{
			List<String> args = getCommandArguments(response);
	    	if(!args.isEmpty()) 
	    	{
	    		return Integer.parseInt(args.get(0));
	    	}
		}
		return 0;
	}
	
	public String getCustomerName(int nummer) 
	{
		String response = send(String.format(CMD_GETCUSTOMERNAME, nummer));
		if(response.startsWith(SUCCESS))
		{
			List<String> args = getCommandArguments(response);
        	if(!args.isEmpty())  {
        		return args.get(0);
        	}
        	return "";
		}
		return "";
	}

	public void close() 
	{
		send(CMD_CLOSE);
		socket.close();
	}
	
	private String send(String msg) {
		try {
			InetAddress serverIP = InetAddress.getByName(SERVER_NAME);
			DatagramPacket senden = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverIP, PORT_NUMBER);
			socket.send(senden);
			DatagramPacket empfangen = new DatagramPacket(buffer, buffer.length);
			socket.receive(empfangen);
			return new String(empfangen.getData(), 0, empfangen.getLength());
		} catch (Exception e) {
			return "";
		}
    }
}
