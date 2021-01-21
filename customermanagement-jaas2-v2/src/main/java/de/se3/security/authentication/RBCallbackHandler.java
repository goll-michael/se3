

package de.se3.security.authentication;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import de.se3.security.InsufficientFeed;
import de.se3.security.SecuritySession;

/**
 * @author stephanberg@stephanberg.eu
 * Angepasste Implementierung des CallbackHandlers.
 * Schaltet erzeugt und schaltet die Maske fuer
 * die Anmeldedaten auf. 
 */
public class RBCallbackHandler implements CallbackHandler{

    protected String login;
    protected String role;
    protected String password;

    protected JTextField passwordField;
    protected JTextField     loginField;
    protected JComboBox      roleCmb;
    protected JFrame         loginmask;

    protected JLabel         passwordLbl;
    protected JLabel         roleLbl;
    protected JLabel         loginLbl;

    protected JButton        okBtn;
    protected JButton        cancelBtn;

    protected GridBagLayout  layout;

    public RBCallbackHandler(){
        this.init();
        this.show();
    }

    protected void init(){

    	
        loginmask = new JFrame("Anmeldung");
        
        loginmask.setSize(300, 195);
        loginmask.setAlwaysOnTop(true);
        loginmask.setResizable(false);
        loginmask.setLocationByPlatform(true);
        loginmask.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout = new GridBagLayout();
       
        passwordLbl = new JLabel("Passwort");
        loginLbl    = new JLabel("User");
        roleLbl     = new JLabel("Rolle");

        
        passwordField   = new JPasswordField();

        loginField      = new JTextField();
        roleCmb         = new JComboBox(SecuritySession.getAllPossibleRoles());
        

        okBtn           = new JButton("ok");
        cancelBtn       = new JButton("abbrechen");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;                        
        c.gridy = 0;                        
        c.gridwidth = 1;                    
        c.gridheight = 1;
        c.insets = new Insets(25,25,5,5);
        layout.setConstraints(loginLbl, c);          
        loginmask.add(loginLbl);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(25,5,5,25);
        layout.setConstraints(loginField, c);
        loginmask.add(loginField);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(5,25,5,5);
        layout.setConstraints(passwordLbl, c);
        loginmask.add(passwordLbl);
        
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(5,5,5,25);
        layout.setConstraints(passwordField, c);
        loginmask.add(passwordField);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(5,25,5,5);
        layout.setConstraints(roleLbl, c);
        loginmask.add(roleLbl);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(5,5,5,25);
        layout.setConstraints(roleCmb, c);
        loginmask.add(roleCmb);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(5,5,25,5);
        layout.setConstraints(okBtn, c);
        loginmask.add(okBtn);

        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(5,5,25,25);
        layout.setConstraints(cancelBtn, c);
        loginmask.add(cancelBtn);

        loginmask.setLayout(layout);

        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            try{
            password = passwordField.getText();
            login    = loginField.getText();
            role     = (String)roleCmb.getSelectedItem();

            if( null == password || password.length() <= 0 ||
                null == login    || login.length() <= 0 ||
                null == role     || role.length() <= 0){
                throw new InsufficientFeed();
            }
            } catch (Exception ife){
                System.out.print(ife.getCause());
            }
            SecuritySession.getSecuritySession().login();
            loginmask.dispose();

            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            System.exit(0);
            }
        });

    }

    protected void show(){
        loginmask.setVisible(true);
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException{

        for (int i = 0; i < callbacks.length; i++) {

           if (callbacks[i] instanceof RBCallback) {
                final RBCallback rbc  = (RBCallback) callbacks[i];

                rbc.setPassword(password);
                rbc.setRole(role);
                rbc.setUser(login);

            }  else {
                throw new UnsupportedCallbackException(callbacks[i], "Unbekannter Callback wurde angefragt.");
            }
        }
    }
}
