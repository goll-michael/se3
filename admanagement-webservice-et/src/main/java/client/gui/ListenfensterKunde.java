package client.gui;

import javax.swing.JOptionPane;

import server.schnittstelle.fachkonzept.Kunde;
import client.application.AnzeigenverwaltungDelegate;

/**
 * Unterfenster zur Darstellung der Kundenliste
 */
public class ListenfensterKunde extends Listenfenster
{	
	private static final long serialVersionUID = 1L;

	private AnzeigenverwaltungDelegate anzeigenverwaltung = AnzeigenverwaltungDelegate.getInstance();

	private ModellKunde einTabellenModell = new ModellKunde();
	private UnterfensterKunde kundenFenster;
	// privater Konstruktor
	public ListenfensterKunde(UnterfensterKunde kundenFenster) 
	{
		super();
		kundenFenster.setVisible(false);
		this.kundenFenster = kundenFenster;

		eineTabellenAnsicht.setModel(einTabellenModell);

		ModellKunde.ausrichten(eineTabellenAnsicht);
	}

	protected void aendern() 
	{
		try 
		{
			int zeile = eineTabellenAnsicht.getSelectedRow();
			if (zeile > -1) {
				Kunde einKunde = anzeigenverwaltung.getKunden().get(zeile);
				this.setVisible(false);
				 kundenFenster.zeigen(einKunde);
				 kundenFenster.setVisible(true);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
			        "Fehler", JOptionPane.OK_OPTION );
		}
	}

	protected void loeschen() 
	{
		try 
		{
			int zeile = eineTabellenAnsicht.getSelectedRow();
			if (zeile > -1) {

				Kunde einKunde = anzeigenverwaltung.getKunden().get(zeile);
				anzeigenverwaltung.entferneKunde(einKunde);

				updateTabelle();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ein Fehler ist aufgetreten: " + e.getMessage(),
			        "Fehler", JOptionPane.OK_OPTION );
		}
	}

	@Override
	protected void updateTabelle() 
	{
		try
		{
			anzeigenverwaltung.aktualisiereListen();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		einTabellenModell.update();	
	}
}
