package de.se3.security.administration.ui;

import info.clearthought.layout.TableLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
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

import de.se3.security.administration.Role;
import de.se3.security.administration.Roles;

/**
 *
 * @author sberg
 * Tab zur Verwaltung von Rollen.
 */
public class UIRoles extends Updateable{

    protected static final  String left = "left";
    protected static final  String right = "right";
    protected static final  String edit = "edit";
    protected static final  String delete = "delete";
    protected static final  String create = "neu";
    protected String user = "";

    private Roles dr = null;

    private JPanel        panelLeft;
    private JPanel        panelRight;

    protected String[]      columnNamesLeft =
        {"Rolle", "Beschreibung"};
    protected Object[][]    dataLeft        =
        {{"sberg", "Stephan"}};

    protected String[]      columnNamesRight     = {"Rolle", "Beschreibung"};
    protected Object[][]    dataRight          = {{"Administrator", "Darf alles"}};

    protected JTable        tableLeft              = null;
    protected JTable        tableRight             = null;

    protected JPopupMenu    popupLeft  = new JPopupMenu();

    protected JPopupMenu    popupRight = new JPopupMenu();

    JScrollPane scrollPaneRight = null;
    JScrollPane scrollPaneLeft = null;

    protected LeftDialog     addLeftMenu = new LeftDialog();
    protected RightDialog     addRightMenu = new RightDialog();

    protected JButton buttonLeft  = new JButton("+");
    protected JButton buttonRight = new JButton("+");

    JLabel labelLeft = new JLabel("Rolle");
    JLabel labelRight = new JLabel("Implizierte Rollen");

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

  private class PopupLeftDelete implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int row = tableLeft.getSelectedRow();

            dr.deleteRole((String)tableLeft.getModel().getValueAt(row, 0));
            tableLeft.setSelectionModel(new DefaultListSelectionModel());
            tableLeft.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                updateRightTable();
            }
        });
            updateLeftTable();


        }
  }



    public UIRoles() {

        double size_panelLeft[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        double size_panelRight[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        dr =  Roles.getInstance();

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

        buttonLeft.setName(UIRoles.left);
        buttonLeft.setActionCommand(UIRoles.create);
        buttonLeft.addActionListener(addBtnListener);
        buttonRight.setName(UIRoles.right);
        buttonRight.setActionCommand(UIRoles.create);
        buttonRight.addActionListener(addBtnListener);

        panelLeft.add(labelLeft, "1,1,1,1");
        panelLeft.add(buttonLeft, "2,1,2,1");
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

        createLeftPopup();
        createRightPopup();

        updateLeftTable();
        updateRightTable();

    }



    protected void updateLeftTable(){

        tableLeft.setModel(new DefaultTableModel(dr.getRoles(), columnNamesLeft));
        updateRightTable();

    }

    protected void updateRightTable(){
            user = "";
            if (!tableLeft.getSelectionModel().isSelectionEmpty())
            user = (String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0);
            String[][] roles = dr.getParentRoles(user);
            if(null == roles){
                tableRight.setModel(new DefaultTableModel(null, columnNamesRight));
            } else {
                tableRight.setModel(new DefaultTableModel(roles, columnNamesRight));
            }
    }

    public void update(){
        updateLeftTable();
        updateRightTable();
    }

    protected void createLeftPopup(){
        JMenuItem edit = new JMenuItem("ändern");
        edit.setActionCommand(UIRoles.edit);
        edit.setName(UIRoles.left);
        edit.addActionListener(new PopupLeftEdit());
        edit.setActionCommand(UIRoles.edit);
        JMenuItem delete = new JMenuItem("löschen");
        delete.setName(UIRoles.right);
        delete.addActionListener(new PopupLeftDelete());
        delete.setActionCommand(UIRoles.delete);
        popupLeft.add(edit);
        popupLeft.add(delete);
    }

    protected void createRightPopup(){

    }

    protected class RightDialog extends JFrame{

        JButton    okBtn = new JButton("Ok");
        JComboBox  cmb;
        JFrame thisFrame;

        double size[][] =
            {{10, TableLayout.FILL, 75, 10},
             {10, 25, 10, 25}};

        public RightDialog(){

            okBtn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Role role = Roles.getInstance().getRole((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0));
                    Role roleToInsert = Roles.getInstance().getRole((String)cmb.getSelectedItem());
                    if(role.addParent(roleToInsert)){
                        updateRightTable();
                        thisFrame.setVisible(false);
                    } else {
                        cmb.setBackground(Color.red);
                    }
                }
            });
        }


        public void setVisible(boolean visible, String mode){

            thisFrame = this;
            cmb = new JComboBox(dr.getPossibleUserRoles((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0)));
            cmb.setEditable(false);
            this.setLayout(new TableLayout(size));
            this.setResizable(false);
            this.add(cmb,"1,1,2,1");
            this.add(okBtn, "2,3,2,3");
            this.setSize(300, 115);
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

            okBtn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String content[] = new String[2];
                    content[1] = role.getText();
                    content[0] = description.getText();

                    System.out.println("content[0]: " + content[0]);
                    System.out.println("content[1]: " + content[1]);

                   if( mode.equals(UIRoles.create)){
                        if(dr.createNewRole(content[0], content[1])){
                            ((DefaultTableModel)tableLeft.getModel()).addRow(content);
                            description.setText("");
                            role.setText("");
                            thisFrame.dispose();
                        } else {
                            description.setBackground(Color.red);
                        }
                   } else {
                        if(dr.editRole(content[0], content[1])){
                            int row = tableLeft.getSelectedRow();
                            tableLeft.getModel().setValueAt(content[0], row, 0);
                            tableLeft.getModel().setValueAt(content[1], row, 1);
                            description.setText("");
                            role.setText("");

                            thisFrame.dispose();
                        } else {
                            description.setBackground(Color.red);
                        }
                   }
                }
            });
        }
    }

}

