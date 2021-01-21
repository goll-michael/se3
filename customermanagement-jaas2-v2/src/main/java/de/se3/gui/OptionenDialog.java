package de.se3.gui; 

import javax.swing.*;
import java.awt.*;

public class OptionenDialog extends JOptionPane
{
    public static void zeigen(Component c)
    {
    	zeigen(c, "Es sind Mussfelder nicht ausgefuellt!");
	}
    
    public static void zeigen(Component c, String msg)
    {
        showMessageDialog(c, msg,
	        "Mitteilung", JOptionPane.OK_OPTION );
	}
}