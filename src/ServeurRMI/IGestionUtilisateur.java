package ServeurRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestionUtilisateur extends Remote{
	
	public void ajouterUtilisateur(String nomm, String prenomm, String mdpm) throws RemoteException;

}
