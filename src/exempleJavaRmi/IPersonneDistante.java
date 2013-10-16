package exempleJavaRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface correspondant a une personne distante.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public interface IPersonneDistante extends Remote {

    /**
     * Retourne le nom de la personne
     * @return le nom de la personne
     */
    public String getNom() throws RemoteException;

    /**
     * Retourne le prenom de la personne
     * @return le prenom de la personne
     */
    public String getPrenom() throws RemoteException;

    /**
     * Modifie le nom de la personne
     * @param nom le nouveau nom
     */
    public void setNom(String nom) throws RemoteException;

    /**
     * Modifie le prenom de la personne
     * @param prenom le nouveau prenom
     */
    public void setPrenom(String prenom) throws RemoteException;

}
