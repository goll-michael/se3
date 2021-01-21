package de.se3.security.administration.ui;

import info.clearthought.layout.TableLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.se3.security.administration.Administration;

/**
 *
 * @author sberg
 * Die Klasse bildet den Container fuer die verschiedenen Tabs die
 * fuer die Rechteverwaltung eingehangt sind.
 */
public class UIMain {
    
    JFrame          mainFrame =
            new JFrame("Rechte und Benutzerverwaltung");
    JTabbedPane     tabContainer =
            new JTabbedPane();
    JButton         okBtn =
            new JButton("Ok");
    JCheckBox secEnabled =
            new JCheckBox("Rechteverwaltung aktiviert");
    JMenuBar        menuBar =
            new JMenuBar();
    JMenu           menuFile =
            new JMenu("Datei");
    TableLayout    layout;


    public void show(boolean isStandAlone){

        if(isStandAlone){
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }   else    {
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        double size[][] =
            {{3, TableLayout.FILL, 50, 3},
             {3, TableLayout.FILL, 3, 25, 3}};

        layout = new TableLayout(size);


        tabContainer.addTab("Benutzer",
                            new UIUser());
        tabContainer.addTab("Rollen",
                            new UIRoles());
        tabContainer.addTab("Rollen <-> Rechte",
                            new UIRoleRights());
        tabContainer.addTab("Rechte",
                            new UIRights());

        JMenuItem menuEnd = new JMenuItem("Beenden");

        menuEnd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            mainFrame.dispose();            }
        });

        menuFile.add(menuEnd);
        menuBar.add(menuFile);
  
        mainFrame.setJMenuBar(menuBar);


        mainFrame.setLayout(layout);

        mainFrame.add(tabContainer, "1, 1, 2, 1");
        mainFrame.add(secEnabled, "1, 3, 1, 3");
        mainFrame.add(okBtn, "2, 3, 2, 3");

        mainFrame.setSize(1200, 800);
        mainFrame.setVisible(true);

        secEnabled.setSelected(Administration.getInstance().isPermissionSystemEnabled());

        secEnabled.addItemListener(new ItemListener() {
            public void itemStateChanged( ItemEvent e ) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    Administration.getInstance().setPermissionSystemEnabled(true);
                } else {
                    Administration.getInstance().setPermissionSystemEnabled(false);
                }
            }
        });

       tabContainer.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                JTabbedPane pane = (JTabbedPane)ce.getSource();
                       Updateable up =  (Updateable)pane.getSelectedComponent();
                       up.update();
            }
        });

        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });
    }

}
