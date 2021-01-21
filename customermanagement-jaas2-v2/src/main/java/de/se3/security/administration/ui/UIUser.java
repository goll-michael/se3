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
import de.se3.security.administration.User;
import de.se3.security.administration.Users;

/*
 * Tab zur Verwaltung der Benutzer
 */

public class UIUser extends Updateable{

    protected static final  String left = "left";
    protected static final  String right = "right";
    protected static final  String edit = "edit";
    protected static final  String delete = "delete";
    protected static final  String create = "neu";

    protected Users users = null;


    protected String user = "";

    private JPanel        panelLeft;
    private JPanel        panelRight;

    protected String[]      columnNamesLeft =
        {"User", "Name", "Vorname", "Kennwort"};
    protected Object[][]    dataLeft        =
        {{"", "","", ""}};

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

    JLabel labelLeft = new JLabel("Benutzer");
    JLabel labelRight = new JLabel("Rollen");

    protected AddButtonListener addBtnListener = new AddButtonListener();

    private class PopMenuListener extends MouseAdapter{

         public void mouseClicked(MouseEvent e){
             if(e.getButton() == e.BUTTON3){
                 if(e.getComponent().getName().equals(UIUser.left)){
                    popupLeft.show(e.getComponent(), e.getX(), e.getY());
                    popupLeft.setVisible(true);
                 } else if (e.getComponent().getName().equals(UIUser.right)){
                    popupRight.show(e.getComponent(), e.getX(), e.getY());
                    popupRight.setVisible(true);
                 }
             } else { return; }
         }
    }

    private class AddButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
             if(((JComponent)e.getSource()).getName().equals(UIUser.left)){
                addLeftMenu.setVisible(true, e.getActionCommand());
             } else if (((JComponent)e.getSource()).getName().equals(UIUser.right)){
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

            users.deleteUser((String)tableLeft.getModel().getValueAt(row, 0));
            tableLeft.setSelectionModel(new DefaultListSelectionModel());
            tableLeft.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                updateRightTable();
            }
        });
            updateLeftTable();

            
        }
  }

 private class PopupRightDelete implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            User user = Users.getInstance().getUser((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0));
            int row = tableRight.getSelectedRow();
            Role role = Roles.getInstance().getRole((String)tableRight.getModel().getValueAt(tableRight.getSelectedRow(), 0));
            user.removeRole(role);
            updateRightTable();
            tableRight.setSelectionModel(new DefaultListSelectionModel());
            tableRight.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                updateRightTable();
            }
        });



        }
  }

    public UIUser() {

        double size_panelLeft[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        double size_panelRight[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        tableLeft = new JTable(new DefaultTableModel()) {
        @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }};

          users = Users.getInstance();

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

        buttonLeft.setName(UIUser.left);
        buttonLeft.setActionCommand(UIUser.create);
        buttonLeft.addActionListener(addBtnListener);
        buttonRight.setName(UIUser.right);
        buttonRight.setActionCommand(UIUser.create);
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

        tableLeft.setName(UIUser.left);
        tableRight.setName(UIUser.right);

        createLeftPopup();
        createRightPopup();

        updateLeftTable();
        updateRightTable();

    }



    protected void updateLeftTable(){

        tableLeft.setModel(new DefaultTableModel(users.getUser(), columnNamesLeft));
        updateRightTable();

    }

    protected void updateRightTable(){
            user = "";
            if (!tableLeft.getSelectionModel().isSelectionEmpty())
            user = (String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0);
            String[][] roles = users.getUserRolesInUse(user);
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
        edit.setActionCommand(UIUser.edit);
        edit.setName(UIUser.left);
        edit.addActionListener(new PopupLeftEdit());
        edit.setActionCommand(UIUser.edit);
        JMenuItem delete = new JMenuItem("löschen");
        delete.setName(UIUser.right);
        delete.addActionListener(new PopupLeftDelete());
        delete.setActionCommand(UIUser.delete);
        popupLeft.add(edit);
        popupLeft.add(delete);
    }

    protected void createRightPopup(){
        JMenuItem delete = new JMenuItem("löschen");
        delete.addActionListener(new PopupRightDelete());
        popupRight.add(delete);
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
                    if(users.insertRoleForUser((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0), (String)cmb.getSelectedItem())){
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
            cmb = new JComboBox(users.getUserRolesNotInUse((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0)));
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

        JTextField userTf = new JTextField();
        JTextField password = new JTextField();
        JTextField name = new JTextField();
        JTextField firstname = new JTextField();
        JButton    okBtn = new JButton("Ok");
        JFrame     thisFrame;
        String     mode; 

        public void setVisible(boolean visible, String mode){

            if (mode.equals(UIUser.edit)){
                userTf.setText((String)tableLeft.getModel().getValueAt(tableLeft.getSelectedRow(), 0));
                userTf.setEditable(false);
                this.mode = UIUser.edit;
                this.setTitle("Ändern");
            } else {
                this.mode = UIUser.create;
                userTf.setEditable(true);
                this.setTitle("Hinzufügen");
            }

            this.setVisible(visible);
        }


        public LeftDialog() {

            double size_addmenu[][] =
            {{10, 0.3, 10, 0.5, 0.2, 10},
             {10, 25, 10, 25, 10, 25, 10, 25, 10, 25, 10}};

            this.setLayout(new TableLayout(size_addmenu));
            this.setSize(300, 225);
            this.setResizable(false);
            this.setLocation(100, 100);

            JLabel userLb = new JLabel("Username");
            JLabel passwordLb = new JLabel("Kennwort");
            JLabel nameLb = new JLabel("Name");
            JLabel firstnameLb = new JLabel("Vorname");

            userTf = new JTextField();
            password = new JTextField();
            name = new JTextField();
            firstname = new JTextField();


            this.add(userLb, "1,1,1,1");
            this.add(userTf, "3,1,4,1");
            this.add(passwordLb, "1,3,1,3");
            this.add(password, "3,3,4,3");

            this.add(nameLb, "1,5,1,5");
            this.add(name, "3,5,4,5");

            this.add(firstnameLb, "1,7,1,7");
            this.add(firstname, "3,7,4,7");

            this.add(okBtn, "4,9,4,9");
            thisFrame = this;
 
            okBtn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String content[] = new String[4];
                    content[0] = userTf.getText();
                    content[1] = name.getText();
                    content[2] = firstname.getText();
                    content[3] = password.getText();


                   if( mode.equals(UIUser.create)){
                       if(users.createNewUser(content[0], content[3], content[1], content[2])){
                            userTf.setText("");
                            password.setText("");
                            name.setText("");
                            firstname.setText("");                            
                            ((DefaultTableModel)tableLeft.getModel()).addRow(content);
                            thisFrame.dispose();
                        } else {
                            userTf.setBackground(Color.red);
                        }
                   } else {
                        if(users.editUser(content[0], password.getText(),  content[1], content[2])){
                            int row = tableLeft.getSelectedRow();
                            tableLeft.getModel().setValueAt(content[0], row, 0);
                            tableLeft.getModel().setValueAt(content[1], row, 1);
                            tableLeft.getModel().setValueAt(content[2], row, 2);
                            tableLeft.getModel().setValueAt( content[3], row, 3);
                            userTf.setText("");
                            password.setText("");
                            name.setText("");
                            firstname.setText("");
                            thisFrame.dispose();
                        } else {
                            userTf.setBackground(Color.red);
                        }
                   }
                }
            });
        }
    }

}
