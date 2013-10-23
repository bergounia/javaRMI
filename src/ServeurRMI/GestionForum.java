package ServeurRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GestionForum extends UnicastRemoteObject implements IGestionForum{
	
	public GestionForum(String nomForum) throws RemoteException{
		
	}

}
