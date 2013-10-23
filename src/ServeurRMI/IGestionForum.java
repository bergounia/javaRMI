package ServeurRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestionForum extends Remote{

	public void creerForum(String nomForum) throws RemoteException;
	public void creerForums() throws RemoteException;
	public void ajouterForum(Forum f) throws RemoteException;
	public void supprimerForum(Forum f) throws RemoteException;
}
