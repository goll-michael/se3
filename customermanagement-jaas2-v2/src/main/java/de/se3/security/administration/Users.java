

package de.se3.security.administration;

import java.util.LinkedList;

import de.se3.datenhaltung.ObjektDatei;
/**
 * @author stephanberg@stephanberg.eu
 * Verwaltet die Benutzer.
 * Es wird immer der Benutzer demoUser1 mit dem Kennwort password
 * fuer die Rolle Administrator angelegt, um eine erste Anmeldung zu ermoeglichen.
 */
public class Users {
private LinkedList<User> list;
    public static Users instance;
    protected static ObjektDatei db = null;
    
    public static Users getInstance(){

        if(null == instance){
            db = new ObjektDatei("Users.xml");
            try{
                instance = (Users)db.leseObjekt();
            } catch (Exception e) {
                instance = new Users();
            }
        }

        instance.check();
        return instance;
    }

    private void check(){
        if(null == getUser("demoUser1")){
            createNewUser("demoUser1", "password", "user", "demo");
            insertRoleForUser("demoUser1", "Administrator");
        }
    }

    public void speichere()
    {
        db.speichereObjekt(this);
    }
    
    public Users(){
        list = new LinkedList<User>();
    }
    
    public boolean createNewUser(final String user, final String password, final String name, final String firstname){

        return getList().add(new User(user, password, name, firstname));
    }

    public User getUser(final String userlogin){

        for(User user: getList()){
            if(user.getUserlogin().equals(userlogin))
                return user;
        }
        return null;
    }

    public boolean editUser(final String userlogin, final String password, final String name, final String firstname) {


        User user = getUser(userlogin);
        if(null != user){
            user.setPassword(password);
            user.setFirstname(firstname);
            user.setName(name);
            return true;
        }
        return false;
    }

    public boolean deleteUser(final String userlogin){

        User user = getUser(userlogin);
        return getList().remove(user);
    }

    public String[][] getUserRolesInUse(String userlogin){

        if(null == userlogin || userlogin.isEmpty()){
            return null;
        }

        User user = getUser(userlogin);
        String[][] result = new String[user.getRoles().size()][2];
        int row = 0;
        for(Role role: user.getRoles()){
            result[row][0] = role.getName();
            result[row++][1] = role.getDescription();
        }
        return result;
    }

    

    public  String[][] getUser(){

            String[][] result = new String[getList().size()][4];

            int row = 0;
            for(User user: getList()){
                result[row][0] = user.getUserlogin();
                result[row][1] = user.getName();
                result[row][2] = user.getFirstname();
                result[row][3] = user.getPassword();
                row++;
            }

            return result;

   

    }

    public String[] getUserRolesNotInUse(String userlogin){

          LinkedList<Role> templist = new LinkedList<Role>();
          User user = getUser(userlogin);
          for (Role posRole : Roles.getInstance().getList()){
              if(!user.isRole(posRole))
                  templist.add(posRole);
          }

          int row = 0;
          String[] result = new String[templist.size()];
          for(Role role: templist){
              result[row++] = role.getName();
          }

          return result;

    }

    public boolean insertRoleForUser(final String userlogin, final String roleName){

        User user = getUser(userlogin);
        Role role = Roles.getInstance().getRole(roleName);

        if(null == user || null == role)
            return false;

        return user.addRole(role);

    }

    public LinkedList<User> getList() {
        return list;
    }

    public void setList(LinkedList<User> list) {
        this.list = list;
    }

}
