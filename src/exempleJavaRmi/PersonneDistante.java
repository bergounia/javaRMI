package exempleJavaRmi;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

/**
 * Classe correspondant a une personne distante.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public class PersonneDistante extends UnicastRemoteObject implements IPersonneDistante {

    private String nom;
    private String prenom;

    /**
     * Constructeur par defaut.
     */
    public PersonneDistante() throws RemoteException {
	nom = "Doe";
	prenom = "John";
    }

    /**
     * Constructeur par initialisation.
     * @param prenom le prenom de la personne
     * @param nom le nom de la personne
     */
    public PersonneDistante(String prenom, String nom) throws RemoteException {
	this.nom = nom;
	this.prenom = prenom;
    }

    /**
     * Retourne le nom de la personne.
     * @return le nom de la personne
     */
    public String getNom() throws RemoteException {
	return nom;
    }

    /**
     * Retourne le prenom de la personne.
     * @return le prenom de la personne
     */
    public String getPrenom() throws RemoteException {
	return prenom;
    }

    /**
     * Modifie le nom de la personne.
     * @param nom le nouveau nom
     */
    public void setNom(String nom) throws RemoteException {
	this.nom = nom;
    }

    /**
     * Modifie le prenom de la personne.
     * @param prenom le nouveau prenom
     */
    public void setPrenom(String prenom) throws RemoteException {
	this.prenom = prenom;
    }

}
