package de.se3.security.administration.ui;

import info.clearthought.layout.TableLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import de.se3.security.administration.Right;
import de.se3.security.administration.RoleRights;
import de.se3.security.administration.Roles;

/**
 *
 * @author sberg
 * Tab zur Verwaltung von Rechten und Rollen
 */
public class UIRoleRights extends Updateable{
 protected static final  String left = "left";
    protected static final  String right = "right";
    protected static final  String edit = "edit";
    protected static final  String delete = "delete";
    protected static final  String create = "neu";
    protected String user = "";

    private JPanel        panelLeft;
    private JPanel        panelRight;

    protected String[]      columnNamesLeft =
        {"Rolle", "Beschreibung"};
    protected Object[][]    dataLeft        =
        {{"", ""}};

    protected String[]      columnNamesRight     = {"Objekt", "Aktion"};
    protected Object[][]    dataRight          = {{"Administrator", "Darf alles"}};

    protected JTable        tableLeft              = null;
    protected JTable        tableRight             = null;

    protected JPopupMenu    popupLeft  = new JPopupMenu();

    protected JPopupMenu    popupRight = new JPopupMenu();

    private RoleRights drr = null;

    JScrollPane scrollPaneRight = null;
    JScrollPane scrollPaneLeft = null;

    protected LeftDialog     addLeftMenu = new LeftDialog();
    protected RightDialog     addRightMenu = new RightDialog();

    protected JButton buttonRight = new JButton("+");

    JLabel labelLeft = new JLabel("Rolle");
    JLabel labelRight = new JLabel("Rechte");

    protected AddButtonListener addBtnListener = new AddButtonListener();

    private class PopMenuListener extends MouseAdapter{

         public void mouseClicked(MouseEvent e){
             if(e.getButton() == e.BUTTON3){
                 if(e.getComponent().getName().equals(UIRoles.left)){
                    popupLeft.show(e.getComponent(), e.getX(), e.getY());
                    popupLeft.setVisible(true);
                 } else if (e.getComponent().getName().equals(UIRoles.right)){
                    popupRight.show(e.getComponent(), e.getX(), e.getY());
                    popupRight.setVisible(true);
                 }
             } else { return; }
         }
    }

    private class AddButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
             if(((JComponent)e.getSource()).getName().equals(UIRoles.left)){
                addLeftMenu.setVisible(true, e.getActionCommand());
             } else if (((JComponent)e.getSource()).getName().equals(UIRoles.right)){
                addRightMenu.setVisible(true, e.getActionCommand());
             }
        }
    }

    private class PopupLeftEdit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addLeftMenu.setVisible(true, e.getActionCommand());
        }
  }


    public UIRoleRights() {

        double size_panelLeft[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        double size_panelRight[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        drr = new RoleRights();

        tableLeft = new JTable(new DefaultTableModel()) {
        @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }};
            tableLeft.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                updateRightTable();
            }
        });



        tableLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLeft.setRowSelectionAllowed(true);
        tableLeft.setColumnSelectionAllowed(false);




        tableRight = new JTable(new DefaultTableModel()) {
        @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }};


        panelLeft    = new JPanel(new TableLayout(size_panelLeft));
        panelRight   = new JPanel(new TableLayout(size_panelRight));

        scrollPaneLeft = new JScrollPane(tableLeft);
        tableLeft.setFillsViewportHeight(true);

        scrollPaneRight = new JScrollPane(tableRight);
        tableRight.setFillsViewportHeight(true);

        tableLeft.addMouseListener(new PopMenuListener());
        tableLeft.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                updateRightTable();
            }

        });



        tableRight.addMouseListener(new PopMenuListener());

        panelLeft.setBackground(new Color(200, 200, 200));
        panelRight.setBackground(new Color(200, 200, 200));

        buttonRight.setName(UIRoles.right);
        buttonRight.setActionCommand(UIRoles.create);
        buttonRight.addActionListener(addBtnListener);

        panelLeft.add(labelLeft, "1,1,1,1");
        panelLeft.add(scrollPaneLeft, "1,3,2,3");

        panelRight.add(labelRight, "1,1,1,1");
        panelRight.add(buttonRight, "2,1,2,1");
        panelRight.add(scrollPaneRight, "1,3,2,3");

        double size[][] =
            {{TableLayout.FILL},
             {TableLayout.FILL}};

        this.setLayout(new TableLayout(size));

        JSplitPane splitter = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);

        splitter.setDividerLocation(700);

        this.add(splitter, "0,0");

        tableLeft.setName(UIRoles.left);
        tableRight.setName(UIRoles.right);

        createRightPopup();

        updateLeftTable();
        updateRightTable();

    }



    protected void updateLeftTable(){

        tableLeft.setModel(new DefaultTableModel(Roles.getInstance().getRoles(), columnNamesLeft));
        updateRightTable();

    }

    protected void updateRightTable(){
            user = "";
            if (!tableLeft.getSelectionModel().isSelectionEmpty())
            user = (String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0);
            String[][] rights = drr.getActiveRights(user);
            if(null == rights){
                tableRight.setModel(new DefaultTableModel(null, columnNamesRight));
            } else {
                tableRight.setModel(new DefaultTableModel(rights, columnNamesRight));
            }
    }

    public void update(){
        updateLeftTable();
        updateRightTable();
    }

    protected void createRightPopup(){

    }

    protected class RightDialog extends JFrame{

        JButton    okBtn = new JButton("Ok");
        JComboBox  objectCmb;
        JComboBox  actionCmb;
        JFrame thisFrame;

      double size[][] =
            {{10, TableLayout.FILL, 75, 10},
             {10, 25, 10, 25, 10, 25}};

        public RightDialog(){

            okBtn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String object = (String)objectCmb.getSelectedItem();
                    String action = (String)actionCmb.getSelectedItem();
                    Right right = new Right(object, action);
                    if(drr.insertRoleRight((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0), right)){
                        updateRightTable();
                        thisFrame.setVisible(false);
                    } else {
                        objectCmb.setBackground(Color.red);
                        actionCmb.setBackground(Color.red);
                    }
                }
            });
        }


        public void setVisible(boolean visible, String mode){

            Right[] rights = drr.getPossibleUserRightsRole((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0));
            LinkedList<String> templist = new LinkedList<String>();


            String[] object = new String[rights.length];
            String[] action = new String[2];
            action[0] = "enable";
            action[1] = "visible";

            int row = 0;
            for (Right right : rights){
                object[row++] = right.getObject();
            }

            thisFrame = this;
           
            objectCmb = new JComboBox(object);
            objectCmb.setEditable(false);

            actionCmb = new JComboBox(action);
            actionCmb.setEditable(false);

            this.setLayout(new TableLayout(size));
            this.setResizable(false);
            this.add(objectCmb,"1,1,2,1");
            this.add(actionCmb, "1,3,2,3");
            this.add(okBtn, "2,5,2,5");
            this.setSize(300, 140);
            this.setTitle("Hinzufügen");
            this.setLocation(100, 100);
            this.setVisible(visible);
        }



    }

    protected class LeftDialog extends JFrame{

        JTextField description = new JTextField();
        JTextField role = new JTextField();
        JButton    okBtn = new JButton("Ok");
        JFrame     thisFrame;
        String     mode;

        public void setVisible(boolean visible, String mode){

            if (mode.equals(UIRoles.edit)){
                description.setText((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0));
                description.setEditable(false);
                this.mode = UIRoles.edit;
                this.setTitle("Ändern");
            } else {
                this.mode = UIRoles.create;
                description.setEditable(true);
                this.setTitle("Hinzufügen");
            }

            this.setVisible(visible);
        }


        public LeftDialog() {

            double size_addmenu[][] =
            {{10, 0.3, 10, 0.5, 0.2, 10},
             {10, 25, 10, 25, 10, 25, 10}};

            this.setLayout(new TableLayout(size_addmenu));
            this.setSize(300, 145);
            this.setResizable(false);
            this.setLocation(100, 100);

            JLabel roleLb = new JLabel("Rolle");
            JLabel descriptionLb = new JLabel("Beschreibung");


            description = new JTextField();
            role = new JTextField();


            this.add(roleLb, "1,1,1,1");
            this.add(description, "3,1,4,1");
            this.add(descriptionLb, "1,3,1,3");
            this.add(role, "3,3,4,3");

            this.add(okBtn, "4,5,4,5");
            thisFrame = this;

        }
    }

}
