

package de.se3.security.authentication;

import javax.security.auth.callback.Callback;

/**
 * @author stephanberg@stephanberg.eu
 * Das eigentliche Callback. Siehe Ausarbeitungen zum Seminar. 
 */
public class RBCallback implements Callback{

    protected String password;
    protected String user;
    protected String role;

    public RBCallback(){

    }

    public RBCallback(String role, String user, String password){

        this.password = password;
        this.user = user;
        this.role = role;

    }

    public String getPassword(){
        return password;
    }

    public String getUser(){
        return user;
    }

    public String getRole(){
        return role;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUser(String user){
        this.user = user;
    }

    public void setRole(String role){
        this.role = role;
    }
    
}
