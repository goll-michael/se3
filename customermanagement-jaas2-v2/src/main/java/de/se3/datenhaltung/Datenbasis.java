package de.se3.datenhaltung;

import java.util.*;

import de.se3.applikation.Anzeige;
import de.se3.applikation.Kunde;

public class Datenbasis
{
	private ArrayList<Kunde> meineKunden =
      new ArrayList<Kunde>();
    private ArrayList<Anzeige> meineAnzeigen = 
      new ArrayList<Anzeige>();
    
	public ArrayList<Kunde> getMeineKunden() 
	{
		return meineKunden;
	}
	public void setMeineKunden(ArrayList<Kunde> meineKunden) 
	{
		this.meineKunden = meineKunden;
	}
	public ArrayList<Anzeige> getMeineAnzeigen() 
	{
		return meineAnzeigen;
	}
	public void setMeineAnzeigen(ArrayList<Anzeige> meineAnzeigen) 
	{
		this.meineAnzeigen = meineAnzeigen;
	}   
}