

package de.se3.security.administration;

import java.util.LinkedList;

import de.se3.datenhaltung.ObjektDatei;

/**
 * @author stephanberg@stephanberg.eu
 * Klasse zur Verwaltung der Rechte.
 */
public class Rights {

        private static ObjektDatei db = null;
        private static Rights instance = null;
        private LinkedList<Right> list;

        public Rights(){
            list = new LinkedList<Right>();
        }

        public static Rights getInstance(){

            if(null == instance){
                db = new ObjektDatei("Rights.xml");
                try{
                    instance = (Rights)db.leseObjekt();
                } catch (Exception e) {
                    instance = new Rights();
                }
            }
            return instance;
        }

        public void speichere()
        {
            System.out.println("Anzahl Element:" +  list.size());
            db.speichereObjekt(this);
        }


        public Right getRight(String objectName, String actionName){

            Right right = new Right(objectName, actionName);
            for(Right exRight: list){
                if(exRight.equals(right))
                    return exRight;
            }
            return null;
        }


    public String[][] getRights(){

        int row = 0;
        String result[][] = new String[list.size()][2];
        for (Right right: list){
                result[row][0] = right.getObject();
                result[row++][1] = right.getAction();
        }
        return result;
    }

    public boolean isRightExisting(Right candidate){
                for (Right right: list){
                 if(right.equals(candidate))
                     return true;
                }
                return false;
    }


    public LinkedList<Right> getList() {
        return list;
    }

    public void setList(LinkedList<Right> list) {
        this.list = list;
    }
    
}