package ServeurRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServeurRmi{
	public static void main(String[] args) {
		try {
			// Creation de la personne
			Utilisateur u = new Utilisateur("Cyril", "Rabat", "rabat001");
	
			// Enregistrement sur le Registry
			Naming.rebind("CyrilRabat", u);
		} catch(RemoteException e) {
			System.err.println("Erreur lors de l'enregistrement : " + e);
			System.exit(-1);
		} catch(MalformedURLException e) {
			System.err.println("URL mal formee : " + e);
			System.exit(-1);
		}
	}
}