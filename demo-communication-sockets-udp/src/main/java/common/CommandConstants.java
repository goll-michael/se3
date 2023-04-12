package common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CommandConstants 
{
	private static final String DELIMITER = "#";
	
	//Kommandos, die vom Client kommen
	public static final String CMD_CREATE_PREFIX = "create";
	public static final String CMD_CREATE = CMD_CREATE_PREFIX + DELIMITER + "%s" + DELIMITER + "%s";
	public static final String CMD_GETNEXTNUMBER = "getNextNumber";
	public static final String CMD_GETCUSTOMERNAME_PREFIX = "getCustomerName";
	public static final String CMD_GETCUSTOMERNAME = CMD_GETCUSTOMERNAME_PREFIX + DELIMITER + "%s"; //1 Argument
	public static final String CMD_CLOSE = "close";
	
	// Ruekgabewerte, die der Server dem Client sendet
	public static final String FAILED = "failed";
	public static final String SUCCESS = "success";
	public static final String SUCCESS_1_PARAM = SUCCESS + DELIMITER + "%s";
	
	//Auslesen der Argumente aus dem Kommando
	public static List<String> getCommandArguments(String command)
	{
		List<String> tokens = new ArrayList<String>();
		StringTokenizer st1 = new StringTokenizer(command, DELIMITER);
		if(st1.hasMoreTokens())
		{
			//den ersten ueberlesen:
			st1.nextToken();
			while(st1.hasMoreTokens())
			{
				tokens.add(st1.nextToken());
			}
		}
		return tokens;
	}
}