package server.schnittstelle;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KommandoKonstante 
{
	private static final String TRENNZEICHEN = "#"; //zum Trennen von Argumenten
	
	//Kommandos, die vom Client kommen
	public static final String CMD_EINFUEGE_KUNDE_PREFIX = "einfuegeKunde";
	public static final String CMD_EINFUEGE_KUNDE = CMD_EINFUEGE_KUNDE_PREFIX +
		TRENNZEICHEN + "%s" + TRENNZEICHEN + "%s"; //2 Argumente
	
	public static final String CMD_GET_NAECHSTE_KUNDEN_NR = "getNaechsteKundenNr";

	public static final String CMD_GET_KUNDE_ZU_NR_PREFIX = "getKundeZuNr";
	public static final String CMD_GET_KUNDE_ZU_NR = CMD_GET_KUNDE_ZU_NR_PREFIX + 
		TRENNZEICHEN + "%s"; //1 Argument

	public static final String CMD_ENDE_ANWENDUNG = "endeAnwendung";
	
	
	// Ruekgabewerte, die Server an Client sendet
	public static final String FEHLER = "Fehler ist aufgetreten";
	public static final String OKAY = "Okay";
	public static final String OKAY_1_PARAM = OKAY + TRENNZEICHEN + "%s";
	
	//Auslesen der Argumente aus dem Kommando
	public static List<String> getCommandArguments(String command)
	{
		List<String> tokens = new ArrayList<String>();
		StringTokenizer st1 = new StringTokenizer(command, TRENNZEICHEN);
		
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