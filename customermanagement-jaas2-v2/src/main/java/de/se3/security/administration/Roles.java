

package de.se3.security.administration;
import java.util.LinkedList;

import de.se3.datenhaltung.ObjektDatei;
/**
 * @author stephanberg@stephanberg.eu
 * Die Klasse verwaltet die unterschiedlichen Rollen. Sie legt
 * ausserdem immer die Rolle Administrator an, damit die
 * Anwedung bei der ersten Anmeldung gestartet werden kann. 
 */
public class Roles {

    private LinkedList<Role> list;
        protected static ObjektDatei db = null;
        protected static Roles instance = null;

    public Roles(){
        list = new LinkedList<Role>();


    }

    public static Roles getInstance(){

        if(null == instance){
            db = new ObjektDatei("Roles.xml");
            try{
                instance = (Roles)db.leseObjekt();
            } catch (Exception e) {
                instance = new Roles();
            }
        }
        instance.check();
        return instance;
    }

    private void check(){
       if(!isRoleExisting(new Role("Administrator", "")))
            createNewRole("Administrator", "");
    }

    public void speichere()
    {
        System.out.println("Anzahl Element:" +  getList().size());
        db.speichereObjekt(this);
    }

    public boolean createNewRole(final String role, final String description){


        if(isRoleExisting(new Role(role, description)))
            return false;

        getList().add(new Role(role, description));
        return true;
    }

    public boolean isRoleExisting(Role candidate){
                for (Role role: getList()){
                 if(role.equals(candidate))
                     return true;
                }
                return false;
    }


    public boolean deleteRole(final String roleName){


            Role role = getRole(roleName);

            if(null != role && list.contains(role)){
                if(list.remove(role))
                    return true;
                
            }
            return false;
    
    }

        public boolean editRole(final String roleName, final String description) {


                    Role candidate = new Role(roleName, "");

            for (Role role: getList()){
                 if(role.equals(candidate))
                     role.setDescription(description);
                     return true;
                }
            return false;


    }
       public String[] getRolesForLogin(){

        int row = 0;
        String[] result = new String[list.size()];
        for(Role role: list){
            result[row++] = role.getName();
        }
        return result;
    }

    public String[][] getRoles(){


        int row = 0;
        String result[][] = new String[getList().size()][2];
        for (Role role: getList()){
                result[row][0] = role.getName();
                result[row++][1] = role.getDescription();
        }

        return result;
    }

        public String[][] getParentRoles(String roleName){

        Role roleT = getRole(roleName);
        if(null == roleT) return null;
        
        Role[] roles = roleT.getParents();
        int row = 0;
        String result[][] = new String[roles.length][2];
        for (Role role: roles){
                result[row][0] = role.getName();
                result[row][1] = role.getDescription();
        }
        return result;

    }

    public String[] getPossibleUserRoles(String roleName){
        int row = 0;
        LinkedList<Role> tempList = (LinkedList<Role>)list.clone();
        tempList.remove(getRole(roleName));
        String result[] = new String[tempList.size()];
        for (Role role: tempList){
                result[row++] = role.getName();
        }
        return result;
    }

    public Role getRole(String name){
        Role candidate = new Role (name, "");
           for (Role role: getList()){
                 if(role.equals(candidate)){
                    return role;
                }
            }
         return null;
    }

    public LinkedList<Role> getList() {
        return list;
    }

    public void setList(LinkedList<Role> list) {
        this.list = list;
    }
}
