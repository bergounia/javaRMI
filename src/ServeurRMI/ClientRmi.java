package ServeurRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import exempleJavaRmi.IPersonneDistante;

public class ClientRmi {

	public static void main(String[] args) {
		IPersonneDistante personne = null;

		// Recuperation de la personne distante
		try {
			personne = (IPersonneDistante)Naming.lookup("rmi://localhost/CyrilRabat");
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
}
