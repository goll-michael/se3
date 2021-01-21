package server.application;

import static server.schnittstelle.KommandoKonstante.*;
import java.util.List;

import server.schnittstelle.Kunde;

public class ServerProtokoll 
{
    public Object bearbeiteEingabe(String eingabe) 
    {
        Object ausgabe = null;
        
        if(eingabe.startsWith(CMD_EINFUEGE_KUNDE_PREFIX))
        {
        	List<String> args = getCommandArguments(eingabe);
        	if(args.size() == 2) 
        	{
        		KundeContainerEinfach.getObjektreferenz().einfuegeKunde(args.get(1), Integer.parseInt(args.get(0)));
        	}
        	ausgabe = OKAY;
        }
        else if(eingabe.equals(CMD_GET_NAECHSTE_KUNDEN_NR))
        {
        	int kundennr = KundeContainerEinfach.getObjektreferenz().getNaechsteKundenNr();
        	ausgabe = String.format(OKAY_1_PARAM, kundennr);
        }
        else if(eingabe.startsWith(CMD_GET_KUNDE_ZU_NR_PREFIX))
        {
        	List<String> args = getCommandArguments(eingabe);
        	if(!args.isEmpty()) 
        	{
        		int kndNr = Integer.parseInt(args.get(0));
        		ausgabe = new Kunde();
        		((Kunde) ausgabe).setName(KundeContainerEinfach.getObjektreferenz().getKundeZuNr(kndNr));
        		((Kunde) ausgabe).setNummer(kndNr);
        	}
        }
        else if(eingabe.equals(CMD_ENDE_ANWENDUNG))
        {
        	KundeContainerEinfach.getObjektreferenz().endeAnwendung();
        	ausgabe = "Bye.";
        }    
        else
        {
        	ausgabe = FEHLER; // Kommando unbekannt
        }
        	
        return ausgabe;
    }
}
