package ServeurRMI;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestionForum extends UnicastRemoteObject implements IGestionForum{

	private static Element racine= new Element("forums");
	public static org.jdom2.Document document = new Document(racine);
	private ArrayList<Forum> listeForums= new ArrayList<Forum>();
	private static int identifiant=0; 

	public GestionForum() throws RemoteException
	{
		ParserXML();
	}

	public synchronized int genererIdentifiant()
	{
		return identifiant++;
	}
	
	public ArrayList<Forum> getListeForums()
	{
		return this.listeForums;
	}
	
	public void creerForum(String nomForum) throws RemoteException
	{
		Forum f= new Forum(Forum.genererIdentifiant(), nomForum)
		try {
			// Enregistrement sur le Registry
			Naming.bind(nomForum, f);
		} catch(RemoteException e) {
			System.err.println("Erreur lors de l'enregistrement : " + e);
			System.exit(-1);
		} catch(MalformedURLException e) {
			System.err.println("URL mal formee : " + e);
			System.exit(-1);
		}
		
		GestionForum.ajouterForum(f);
	}

	public void creerForums() throws RemoteException
	{
		for(Forum f: this.getListeForums())
		{
			GestionForum.creerForum(f.getTitle());
		}
	}

	public void ajouterForum(Forum f) throws RemoteException
	{
		Element forum= new Element("forum");
		racine.addContent(forum);

		Element identifiant= new Element("identifiant");
		forum.addContent(identifiant);
		identifiant.setText(f.getId());

		Element titre= new Element("titre");
		forum.addContent(titre);
		titre.setText(f.getTitre());
		
		enregistre("forums.xml");
	}

	public void supprimerForum(Forum f) throws RemoteException
	{
		int i=0;
		boolean b= false;

		List<Element> listeForums= racine.getChildren("forum");

		Iterator<Element> it = listeForums.iterator();

		while(!b)
		{
			Element courant = it.next();
			if(!f.getId().equals(courant.getChild("id").getText()))
				i++;
			else
				b= true;
		}

		racine.removeContent(i);
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
			document = sxb.build(new File("forums.xml"));
		}
		catch(Exception e){}

		//On initialise un nouvel élément racine avec l'élément racine du document.
		racine = document.getRootElement();

	}

}
