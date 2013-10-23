package ServeurRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientRmi {

	public static void main(String[] args) {
		IGestionUtilisateur gestion = null;

		// Recuperation de la personne distante
		try {
			gestion = (IGestionUtilisateur)Naming.lookup("rmi://localhost/GestionUtilisateur");
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
			//System.out.println("Personne : " + personne.getPrenom() + " " + personne.getNom());
			gestion.ajouterUtilisateur("Kaminski", "Benjamin", "coucou");
			gestion.ajouterUtilisateur("Bride", "Aymeric", "Salut");
			gestion.ajouterUtilisateur("Rabat", "Cyril", "bonjour");
			System.out.println(gestion.chercherUtilisateur("BriAym1"));
			gestion.supprimerUtilisateur("KamBen0");
		} catch(RemoteException e) {
			System.err.println("Erreur lors de l'acces aux methodes : " + e);
			System.exit(-1);
		}
	}

}
