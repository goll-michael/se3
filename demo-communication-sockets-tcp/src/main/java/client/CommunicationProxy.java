package client;

import static common.CommandConstants.CMD_CLOSE;
import static common.CommandConstants.CMD_CREATE;
import static common.CommandConstants.CMD_GETCUSTOMERNAME;
import static common.CommandConstants.CMD_GETNEXTNUMBER;
import static common.CommandConstants.SUCCESS;
import static common.CommandConstants.getCommandArguments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import common.CustomerContainerI;

public class CommunicationProxy implements CustomerContainerI
{
	private static CommunicationProxy serverProxy = null;
	
	private static final String SERVER_NAME = "localhost";
	private static final int PORT_NUMBER = 4444;
	
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
    
	public CommunicationProxy()
	{
		socket = null;
        out = null;
        in = null;
        
        try 
        {
			socket = new Socket(SERVER_NAME, PORT_NUMBER);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (UnknownHostException e) 
        {
        	System.out.println("host unknown: " + SERVER_NAME);
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
		out.close();
		try
		{
			in.close();
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private String send(String msg) {
		out.println(msg);
		String response = null; 
		try 
		{
			response = in.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return response;
    }
}
