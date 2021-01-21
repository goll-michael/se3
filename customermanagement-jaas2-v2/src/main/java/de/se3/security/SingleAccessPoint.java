

package de.se3.security;


/**
 * @author stephanberg@stephanberg.eu
 *
 * Implementierung des Musters SingleAccessPoint. Die
 * ersten Anfragen werden von dieser Klasse entegegengenommen.
 * Siehe Ausarbeitungen in der Seminararbeit. 
 */
public class SingleAccessPoint {

    private static SingleAccessPoint instance;

    private SingleAccessPointCaller sapc;
    private SecuritySession ss = null;

    protected SingleAccessPoint(){

    }

    public static SingleAccessPoint getInstance(){

        if(null == instance)
            instance = new SingleAccessPoint();
        return instance;
    }



    public void loginAndStart(SingleAccessPointCaller sapc){

        ss = SecuritySession.getSecuritySession();
        this.sapc = sapc;

    }

    public void checked(){

        if(ss.isLoggedIn())
        sapc.start();

    }

    public void failed(){
        System.out.println("Anmeldung gescheitert.");
    }

}
