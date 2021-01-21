package de.se3.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import de.se3.security.ac.swing.AcJButton;
import de.se3.security.ac.swing.AcJPanel;
import de.se3.security.ac.swing.AcJScrollPane;
import de.se3.security.ac.swing.AcJTable;

public abstract class ListenFenster extends Unterfenster implements ListSelectionListener 
{
	//Attribute
	//Referenz auf das einzige Objekt
	protected AcJTable eineTabellenAnsicht = new AcJTable("ListenFenster-eineTabellenAnsicht");
    private AcJScrollPane einRollfeld;
    private AbstractTableModel einTabellenModell;
    private ListSelectionModel einSelektionsmodell;
    private AcJButton neuKnopfJ = new AcJButton("ListenFenster-neuKnopfJ","Neu"), AendernKnopfJ = new AcJButton("ListenFenster-AendernKnopfJ","Aendern");
	private AcJButton loeschenKnopfJ = new AcJButton("ListenFenster-neuKnopfJ","Loeschen"), SchliessenKnopfJ = new AcJButton("ListenFenster-SchliessenKnopfJ","Schliessen");
	private AcJPanel knopfflaeche = new AcJPanel("ListenFenster-knopfflaeche");
    
	//privater Konstruktor
	public ListenFenster(JFrame dasAnwendungsfenster,String Fenstertitel)  
	{
		super(dasAnwendungsfenster, Fenstertitel);
		getContentPane().setLayout(null);
		getContentPane().setBackground(java.awt.Color.lightGray);
		setSize(670,200);
		
        eineTabellenAnsicht.setBounds(70,50,250,100);
	    eineTabellenAnsicht.setToolTipText("Es koennen nur Zeilen selektiert werden");
	    //Zeilen selektieren erlaubt
	    eineTabellenAnsicht.setRowSelectionAllowed(true);
	    //Einbettung der Tabellenansicht in ein Rollfeld
	    einRollfeld = new AcJScrollPane("ListenFenster-einRollfeld", eineTabellenAnsicht);
	    einRollfeld.setBounds(30,30,600,100);
	    //Hinzuf�gen des Rollfelds zum Fenster
	    getContentPane().add(einRollfeld);
		getContentPane().setSize(1000,500);
		einSelektionsmodell = eineTabellenAnsicht.getSelectionModel();
		einSelektionsmodell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		einSelektionsmodell.addListSelectionListener(this);

   		//Die Knoepfe im Fenster unterbringen
        getContentPane().add(knopfflaeche);
        knopfflaeche.setBackground(Color.lightGray);
        knopfflaeche.setBounds(17, 130, 423, 40);
        knopfflaeche.add(neuKnopfJ);
        int w = 90;
        int h = 30;
        neuKnopfJ.setPreferredSize(new Dimension(w,h));
        knopfflaeche.add(AendernKnopfJ);
        AendernKnopfJ.setPreferredSize(new Dimension(w,h));
        knopfflaeche.add(loeschenKnopfJ);
        loeschenKnopfJ.setPreferredSize(new Dimension(w,h));
        knopfflaeche.add(SchliessenKnopfJ);
        SchliessenKnopfJ.setPreferredSize(new java.awt.Dimension(103, 30));

		AktionsAbhoerer einAktionsAbhoerer = new AktionsAbhoerer();
		neuKnopfJ.addActionListener(einAktionsAbhoerer);
		AendernKnopfJ.addActionListener(einAktionsAbhoerer);
		loeschenKnopfJ.addActionListener(einAktionsAbhoerer);
		SchliessenKnopfJ.addActionListener(einAktionsAbhoerer);
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
    
	//Beobachter-Operation
	public void aktualisiere()
	{
	}
	 
	//Innere Klasse
	class AktionsAbhoerer implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
		    if(e.getSource()==neuKnopfJ)
		        neu();
		    else if(e.getSource()==AendernKnopfJ)
		        aendern();
		    else if(e.getSource()==SchliessenKnopfJ)
		        schliessen();
		    else if(e.getSource()==loeschenKnopfJ)
		        loeschen();
		}
	}

    //Um eine Reaktion zu erhalten, muessen die folgenden Methoden
    //neu(),aendern(),schliessen(),loeschen() in der jeweiliegen Unterklasse
    //ueberschrieben werden.
    protected abstract void neu();
    
    protected abstract void aendern();
    
    protected void schliessen()
    {
        setVisible(false);
    }
    
    protected abstract void loeschen();
}
