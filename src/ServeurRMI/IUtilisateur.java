package ServeurRMI;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface IUtilisateur {
	public String getId() throws RemoteException;

	public String getMdp() throws RemoteException;

	public void setMdp(String mdp) throws RemoteException;

	public String getNom() throws RemoteException;

	public void setNom(String nom) throws RemoteException;

	public String getPrenom() throws RemoteException;

	public void setPrenom(String prenom) throws RemoteException;
}
