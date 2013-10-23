package ServeurRMI;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestionUtilisateur extends UnicastRemoteObject implements IGestionUtilisateur{

	private static Element racine= new Element("utilisateurs");
	public static org.jdom2.Document document = new Document(racine);
	private static int incr;
	
	public GestionUtilisateur() throws RemoteException {
		ParserXML();
	}
	
	public void ajouterUtilisateur(String nomm, String prenomm, String mdpm) throws RemoteException
	{
		Element utilisateur= new Element("utilisateur");
		racine.addContent(utilisateur);
		
		Element id= new Element("id");
		utilisateur.addContent(id);
		id.setText(nomm.substring(0, 3) + prenomm.substring(0, 3) + GestionUtilisateur.incr++);
		
		Element nom= new Element("nom");
		utilisateur.addContent(nom);
		nom.setText(nomm);
		
		Element prenom= new Element("prenom");
		utilisateur.addContent(prenom);
		prenom.setText(prenomm);
		
		Element mdp= new Element("mdp");
		utilisateur.addContent(mdp);
		mdp.setText(GestionUtilisateur.encode(mdpm));
		
		enregistre("utilisateurs.xml");
	}
	
	public void supprimerUtilisateur(String id) throws RemoteException
	{
		int i=0;
		boolean b= false;
		
		List<Element> listeUtilisateurs= racine.getChildren("utilisateur");

		Iterator<Element> it = listeUtilisateurs.iterator();
		
		while(!b)
		{
			Element courant = it.next();
			if(!id.equals(courant.getChild("id").getText()))
				i++;
			else
				b= true;
		}
		
		racine.removeContent(i);
	}
	
	public boolean chercherUtilisateur(String identifiant, String motDePasse) throws RemoteException
	{
		boolean b= false;
		
		List<Element> listeUtilisateurs= racine.getChildren("utilisateur");
		
		Iterator<Element> it = listeUtilisateurs.iterator();
		
		while(!b && it.hasNext())
		{
			Element courant = it.next();
			
			if(courant.getChild("id").getText().equals(identifiant) & courant.getChild("mdp").getText().equals(encode(motDePasse)) )
				b= true;
		}
		
		return b;
	}
	
	public static void affiche()
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){}
	}
	
	public static void enregistre(String fichier)
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document, new FileOutputStream(fichier));
		}
		catch (java.io.IOException e){}
	}
	
	public static void ParserXML()
	   {
	      //On crée une instance de SAXBuilder
	      SAXBuilder sxb = new SAXBuilder();
	      try
	      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)
	         document = sxb.build(new File("utilisateurs.xml"));
	      }
	      catch(Exception e){}

	      //On initialise un nouvel élément racine avec l'élément racine du document.
	      racine = document.getRootElement();
	      
	   }
	
	public static String encode(String password)
	{
		byte[] uniqueKey = password.getBytes();
		byte[] hash      = null;

		try
		{
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new Error("No MD5 support in this VM.");
		}

		StringBuilder hashString = new StringBuilder();
		for (int i = 0; i < hash.length; i++)
		{
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1)
			{
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}
			else
				hashString.append(hex.substring(hex.length() - 2));
		}
		return hashString.toString();
	}
}
