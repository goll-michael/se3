package de.se3.gui;


import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import de.se3.security.ac.swing.AcJButton;
import de.se3.security.ac.swing.AcJPanel;
import de.se3.security.ac.swing.AcJScrollPane;
import de.se3.security.ac.swing.AcJTable;


public abstract class AuswahlFenster extends Unterfenster implements ListSelectionListener 
{
	//Attribute
	//Referenz auf das einzige Objekt
	protected AcJTable eineTabellenAnsicht = new AcJTable("AuswahlFenster-eineTabellenAnsicht");
    private AcJScrollPane einRollfeld;
    private ListSelectionModel einSelektionsmodell;
    private AcJButton auswaehlenKnopfJ = new AcJButton("AuswahlFenster-auswaehlenKnopfJ","Auswaehlen"),AbbrechenKnopfJ = new AcJButton("Abbrechen");
	private AcJPanel knopfflaeche = new AcJPanel("AuswahlFenster-knopfflaeche");
    
	//privater Konstruktor
	public AuswahlFenster(JFrame dasAnwendungsfenster,String Fenstertitel)  
	{
		super(dasAnwendungsfenster, Fenstertitel);
		this.setModal(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(java.awt.Color.lightGray);
		setSize(670,200);
		
        eineTabellenAnsicht.setBounds(70,50,250,100);
	    eineTabellenAnsicht.setToolTipText("Es koennen nur Zeilen selektiert werden");
	    //Zeilen selektieren erlaubt
	    eineTabellenAnsicht.setRowSelectionAllowed(true);
	    //Einbettung der Tabellenansicht in ein Rollfeld
	    einRollfeld = new AcJScrollPane("AuswahlFenster-einRollfeld", eineTabellenAnsicht);
	    einRollfeld.setBounds(30,30,600,100);
	    //Hinzufuegen des Rollfelds zum Fenster
	    getContentPane().add(einRollfeld);
		getContentPane().setSize(1000,500);
		einSelektionsmodell = eineTabellenAnsicht.getSelectionModel();
		einSelektionsmodell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		einSelektionsmodell.addListSelectionListener(this);

   		//Die Knoepfe im Fenster unterbringen
        int w = 110;
        int h = 30;

        getContentPane().add(knopfflaeche);
        knopfflaeche.setBackground(Color.lightGray);
        knopfflaeche.setBounds(22,130,240,40);
        knopfflaeche.add(auswaehlenKnopfJ);
        auswaehlenKnopfJ.setPreferredSize(new Dimension(w,h));

        knopfflaeche.add(AbbrechenKnopfJ);
        AbbrechenKnopfJ.setPreferredSize(new Dimension(w,h));
 
		AktionsAbhoerer einAktionsAbhoerer = new AktionsAbhoerer();
		auswaehlenKnopfJ.addActionListener(einAktionsAbhoerer);
		AbbrechenKnopfJ.addActionListener(einAktionsAbhoerer);

	}
        
	//Implementation der Schnittstelle ListSelectionListener
	//Wird aufgerufen immer wenn der Selektionswert sich �ndert
	public void valueChanged(ListSelectionEvent e)
	{
	    int selektierteReihe;
	    selektierteReihe = eineTabellenAnsicht.getSelectedRow();
	    
	    zeileSelektiert(selektierteReihe);
	}
    
    //Methode kann in der jeweiliegen Unterklasse ueberschrieben werden,
    //um ein spezifisches Verhalten zu realisieren.
    protected void zeileSelektiert(int Zeile)
    {
    }

	//Innere Klasse
	class AktionsAbhoerer implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
		    if(e.getSource()==auswaehlenKnopfJ)
		        auswaehlen();
		    else if(e.getSource()==AbbrechenKnopfJ)
		        abbrechen();
		}
	}

    //Um eine Reaktion zu erhalten, muessen die folgenden Methoden
    //neu(),aendern(),schliessen(),loeschen() in der jeweiliegen Unterklasse
    //ueberschrieben werden.
    protected abstract void auswaehlen();
    
    protected void abbrechen()
    {
        setVisible(false);
    }
}
