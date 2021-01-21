package server.application;

import static server.schnittstelle.KommandoKonstante.*;
import java.util.List;

public class ServerProtokoll 
{
    public String bearbeiteEingabe(String eingabe) 
    {
        String ausgabe = null;
        
        if(eingabe.startsWith(CMD_EINFUEGE_KUNDE_PREFIX))
        {
        	List<String> args = getCommandArguments(eingabe);
        	if(args.size() == 2) 
        	{
        		KundenContainerEinfach.getObjektreferenz().einfuegeKunde(args.get(1), Integer.parseInt(args.get(0)));
        	}
        	ausgabe = OKAY;
        }
        else if(eingabe.equals(CMD_GET_NAECHSTE_KUNDEN_NR))
        {
        	int kundennr = KundenContainerEinfach.getObjektreferenz().getNaechsteKundenNr();
        	ausgabe = String.format(OKAY_1_PARAM, kundennr);
        }
        else if(eingabe.startsWith(CMD_GET_KUNDE_ZU_NR_PREFIX))
        {
        	ausgabe = OKAY;
        	List<String> args = getCommandArguments(eingabe);
        	if(!args.isEmpty()) 
        	{
        		int kndNr = Integer.parseInt(args.get(0));
        		Kunde k = new Kunde();
        		k.setName(KundenContainerEinfach.getObjektreferenz().getKundeZuNr(kndNr));
        		k.setNummer(kndNr);
        		if(!(k.getName().equals("")))
        		{
        			ausgabe = String.format(OKAY_1_PARAM, k.getName());
        		}
        	}
        }
        else if(eingabe.equals(CMD_ENDE_ANWENDUNG))
        {
        	KundenContainerEinfach.getObjektreferenz().endeAnwendung();
        	ausgabe = "Bye.";
        }    
        else
        {
        	ausgabe = FEHLER; // Kommando unbekannt
        }
        	
        return ausgabe;
    }
}
