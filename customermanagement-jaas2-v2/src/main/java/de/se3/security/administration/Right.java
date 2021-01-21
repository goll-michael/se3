

package de.se3.security.administration;

/**
 * @author stephanberg@stephanberg.eu
 * Ein einzelnes Recht.
 * Siehe Ausarbeitungen zum Seminar. 
 */
public class Right {

    private String object;
    private String action;

    public Right(){

    }

    public Right(String object, String action){
        this.object = object;
        this.action = action;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

  @Override
  public boolean equals(Object posRight) {

    if ( this == posRight ) return true;
    if ( !(posRight instanceof Right) ) return false;

    Right right = (Right)posRight;
    return (action.equals(right.action) && object.equals(right.object));
  }

  /*
   * hashCode ist zu überlagern, wenn man Hashtables etc. nutzen möchte
   */

    
}
