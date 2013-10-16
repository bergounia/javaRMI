package exempleJavaRmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

/**
 * Serveur qui cree une personne et la met a disposition des clients.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public class ServeurRMI {

    /**
     * Methode principale.
     * @param args inutilise
     */
    public static void main(String[] args) {
	try {
	    // Creation de la personne
	    PersonneDistante personne = new PersonneDistante("Cyril", "Rabat");

	    // Enregistrement sur le Registry
	    Naming.rebind("CyrilRabat", personne);
	} catch(RemoteException e) {
	    System.err.println("Erreur lors de l'enregistrement : " + e);
	    System.exit(-1);
	} catch(MalformedURLException e) {
	    System.err.println("URL mal formee : " + e);
	    System.exit(-1);
	}
    }

}
