package client.gui;
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

/**
 * Oberklasse für die beiden Listenfenster
 */
public abstract class Listenfenster extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	//Attribute
	//Referenz auf das einzige Objekt
	protected JTable eineTabellenAnsicht = new JTable();
    private JScrollPane einRollfeld;
    private ListSelectionModel einSelektionsmodell;
    private JButton AendernKnopfJ = new JButton("Aendern");
	private JButton loeschenKnopfJ = new JButton("Loeschen");
	private JButton aktualisierenKnopfJ = new JButton("Liste Aktualisieren");
	private JPanel knopfflaeche = new JPanel();
    
	//privater Konstruktor
	public Listenfenster()  
	{
		//super(dasAnwendungsfenster, Fenstertitel);
		setLayout(null);
		
        eineTabellenAnsicht.setBounds(70,50,170,300);
	    eineTabellenAnsicht.setToolTipText("Es koennen nur Zeilen selektiert werden");
	    //Zeilen selektieren erlaubt
	    eineTabellenAnsicht.setRowSelectionAllowed(true);
	    //Einbettung der Tabellenansicht in ein Rollfeld
	    einRollfeld = new JScrollPane(eineTabellenAnsicht);
	    einRollfeld.setBounds(30,30,400,300);
	    //Hinzufuegen des Rollfelds zum Fenster
	    add(einRollfeld);
		setSize(700,500);
		einSelektionsmodell = eineTabellenAnsicht.getSelectionModel();
		einSelektionsmodell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

   		//Die Knoepfe im Fenster unterbringen
        add(knopfflaeche);
        //knopfflaeche.setBackground(Color.lightGray);
        knopfflaeche.setBounds(17, 330, 423, 40);
       
        int w = 90;
        int h = 30;
        knopfflaeche.add(AendernKnopfJ);
        AendernKnopfJ.setPreferredSize(new Dimension(w,h));
        knopfflaeche.add(loeschenKnopfJ);
        loeschenKnopfJ.setPreferredSize(new Dimension(w,h));
        knopfflaeche.add(aktualisierenKnopfJ);
        aktualisierenKnopfJ.setPreferredSize(new Dimension(2 * w,h));
        
		AendernKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				aendern();
			}
	    });
		loeschenKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				loeschen();
			}
	    });
		aktualisierenKnopfJ.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				updateTabelle();
			}
	    });		
		
		this.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentShown(ComponentEvent arg0) 
			{
				updateTabelle();
			}
		}
		);
	}

    protected abstract void aendern();
    
    protected abstract void loeschen();
    
    protected abstract void updateTabelle();
}
