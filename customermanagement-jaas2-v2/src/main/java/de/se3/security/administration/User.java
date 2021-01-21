

package de.se3.security.administration;

import java.util.LinkedList;

/**
 * @author stephanberg@stephanberg.eu
 * Implementierung eines Users.
 */
public class User{

    private String userlogin;
    private String password;
    private String name;
    private String firstname;
    private LinkedList<Role> roles;

    public User(){
        roles = new LinkedList<Role>();
    }

    public User(String userlogin, String password, String name, String firstname){
        this.userlogin = userlogin;
        this.firstname = firstname;
        this.name = name;
        this.password = password;
        roles = new LinkedList<Role>();
    }

    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LinkedList<Role> getRoles() {
        return roles;
    }

    public String[] getRolesAsString(){

        String[] result = new String[roles.size()];
        int row = 0;
        for(Role role: roles){
            result[row++] = role.getName();
        }
        return result;

    }

    public void setRoles(LinkedList<Role> roles) {
        this.roles = roles;
    }

    public boolean removeRole(Role role){
        return roles.remove(role);
    }

    public boolean addRole(Role role){
        if(!roles.contains(this))
            return roles.add(role);
        return false;
    }

    public boolean isRole(Role role){
        return roles.contains(role);
    }

      @Override
  public boolean equals(Object posUser) {

    if ( this == posUser ) return true;
    if ( !(posUser instanceof User) ) return false;

    User user = (User)posUser;
    return userlogin.equals(user.userlogin);
  }


  /*
   * hashCode ist zu überlagern, wenn man Hashtables etc. nutzen möchte
   */

}
