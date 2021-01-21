package gui;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Start 
{
	public Start()
	{
		SwingUtilities.invokeLater(new Runnable()
	     {
	            public void run()
	            {
	            	String[] sprachen = {"English","German"};
	            	
	            	//Setze Druckknoepfe des Startfensters auf English
	            	UIManager.put("OptionPane.okButtonText", "Okay"); 
	            	UIManager.put("OptionPane.cancelButtonText", "Cancel"); 
	            	
	            	String s = (String)JOptionPane.showInputDialog(
	                    null,
                    	"Choose your language, please!",
	            	    "Language selection",
	            	    JOptionPane.PLAIN_MESSAGE,
	            	    null,
	            	    sprachen,
	            	    "German"
            	    );
	            	if(s == null) return;	            	
	            		            	
	            	ResourceBundle ressourcenBuendel = null;
	            	         	
	            	if(s.equals(sprachen[0]))
	            		ressourcenBuendel = 
	            			ResourceBundle.getBundle("globalisierung.anzeigetexte", new Locale("en"));
	            	else
	            		ressourcenBuendel = 
	            			ResourceBundle.getBundle("globalisierung.anzeigetexte", new Locale("de"));
	            	
					/*
					 * Benutzungsoberflaeche aufbauen.
					 * Erzeugen des Anwendungsfensters
					 */
	            	new Kundenfenster(ressourcenBuendel).setVisible(true);
	            }
	      });
	}
	
	public static void main(String[] args)
	{
		new Start();
	}
}