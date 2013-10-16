package exempleJavaRmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import ServeurRMI.IUtilisateur;

/**
 * Client permettant d'interroger la personne sur le serveur distant.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public class ClientRMI {

    /**
     * Methode principale.
     * @param args inutilise
     */
    public static void main(String[] args) {
	IUtilisateur u = null;

	// Recuperation de la personne distante
	try {
	    u = (IUtilisateur)Naming.lookup("rmi://localhost/CyrilRabat");
	} catch(NotBoundException e) {
	    System.err.println("Pas possible d'acceder à l'objet distant : " + e);
	    System.exit(-1);
	} catch(MalformedURLException e) {
	    System.err.println("URL mal forme : " + e);
	    System.exit(-1);
	} catch(RemoteException e) {
	    System.err.println("Pas possible d'acceder à l'objet distant : " + e);
	    System.exit(-1);
	}

	try {
	    System.out.println("Personne : " + personne.getPrenom() + " " + personne.getNom());
	} catch(RemoteException e) {
	    System.err.println("Erreur lors de l'acces aux methodes : " + e);
	    System.exit(-1);
	}
    }

}