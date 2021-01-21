

package de.se3.security.administration;

import java.util.LinkedList;

/**
 * @author stephanberg@stephanberg.eu
 * Die Klasse setzt eine Rolle um.
 * Sie bietet die Moeglichkeit die Rechte die zu einer Rolle gehoeren
 * und die Rollen von denen diese Rolle erbt, zu verwalten.
 */
public class Role {

    private LinkedList<Right> rights = new LinkedList<Right>();
    private LinkedList<Role>  roles  = new LinkedList<Role>();
    private String name;
    private String description;

public Role(){
   
}

public Role(String name, String description){
    this.name = name;
    this.description = description;
}


public Role(String name, String description, Role parent){
    this.name = name;
    this.description = description;
    addParent(parent);
}


    public String getName() {
        return name;
    }

    public Role[] getParents(){
        return getRoles().toArray(new Role[0]);
    }

    public boolean addParent(Role role){
        if(!getRoles().contains(role))
            return getRoles().add(role);
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  @Override
  public boolean equals(Object posRole) {

    if ( this == posRole ) return true;
    if ( !(posRole instanceof Role) ) return false;

    Role role = (Role)posRole;
    return name.equals(role.name);
  }

    public LinkedList<Right> getRights() {

        if(null == rights)
            rights = new LinkedList<Right>();

        if(0 == getRoles().size())
            return rights;

        LinkedList<Right> tempList = (LinkedList<Right>)getRoles().clone();
        for (Role parent : getRoles()){
            for(Right parentRight : parent.getRights()){
                if(!tempList.contains(parentRight))
                    tempList.add(parentRight);
            }
        }
        return tempList;
    }

    public void setRights(LinkedList<Right> rights) {
        this.rights = rights;
    }

    public boolean addRight(Right right){
        if(!rights.contains(right))
            return rights.add(right);
        return false;
    }

    public boolean removeRight(Right right){
        if(rights.contains(right))
            return rights.remove(right);
        return false;
    }

    public boolean hasRight(Right posRight){
            if(rights.contains(posRight))
                return true;
            return false;
    }

    public LinkedList<Role> getRoles() {
        return roles;
    }

    public void setRoles(LinkedList<Role> roles) {
        this.roles = roles;
    }
  
}
