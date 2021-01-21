package de.se3.security.administration.ui;

import info.clearthought.layout.TableLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import de.se3.security.administration.Rights;

/**
 *
 * @author sberg
 * Tab zur Ansicht aller verfuegbaren Rechte
 */
public class UIRights extends Updateable{
    protected static final  String left = "left";
    protected static final  String right = "right";
    protected static final  String edit = "edit";
    protected static final  String delete = "delete";
    protected static final  String create = "neu";
    protected String user = "";

    private JPanel        panelLeft;

    protected String[]      columnNamesLeft =
        {"Objekt", "Aktion"};
    protected Object[][]    dataLeft        =
        {{"", ""}};
    protected JTable        tableLeft              = null;

    JScrollPane scrollPaneLeft = null;
    JLabel labelLeft = new JLabel("Rolle");

    public UIRights() {

        double size_panelLeft[][] =
            {{1, TableLayout.FILL, 50, 2},
             {1, 20, 1, TableLayout.FILL}};

        tableLeft = new JTable(new DefaultTableModel()) {
        @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }};

        tableLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLeft.setRowSelectionAllowed(true);
        tableLeft.setColumnSelectionAllowed(false);

        panelLeft    = new JPanel(new TableLayout(size_panelLeft));

        scrollPaneLeft = new JScrollPane(tableLeft);
        tableLeft.setFillsViewportHeight(true);

        panelLeft.setBackground(new Color(200, 200, 200));

        panelLeft.add(labelLeft, "1,1,1,1");
        panelLeft.add(scrollPaneLeft, "1,3,2,3");


        double size[][] =
            {{TableLayout.FILL},
             {TableLayout.FILL}};

        this.setLayout(new TableLayout(size));

        this.add(panelLeft, "0,0");

        tableLeft.setName(UIRoles.left);

        updateLeftTable();
    }


    protected void updateLeftTable(){
        Rights dr =  Rights.getInstance();
        tableLeft.setModel(new DefaultTableModel(dr.getRights(), columnNamesLeft));
    }

    public void update(){
        updateLeftTable();
    }
}
