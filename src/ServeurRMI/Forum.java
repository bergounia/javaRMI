package ServeurRMI;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Forum {

	private int id;
	private String title;
	private ArrayList<Message> listeMessages= new ArrayList<Message>();
	private static int incrIdMessage;
	private static Element racine= new Element("forum");
	public static org.jdom2.Document document = new Document(racine);

	/*public Forum(String title){
		this.title=title;
		this.id=SystemClockFactory.getDatetime().toGMTString();
	}*/

	public Forum(int id, String title){
		//verifier sur il y a un XML (messages) à parser
		this.title=title;
		this.id=id;
		incrIdMessage=0;
	}

	public void creerMessage(String auteur,String corps){
		listeMessages.add(new Message(incrIdMessage, auteur, corps));
		majIncre();
	}

	public synchronized static void majIncre(){
		incrIdMessage++;
	}

	public void suppMessage(int id){

	}

	public void genererXML(){

		//ID
		Element id= new Element("id");
		id.setText(String.valueOf(this.id));
		racine.addContent(id);

		//titre
		Element title= new Element("title");
		title.setText(String.valueOf(this.title));
		racine.addContent(title);

		//incrIdMessage
		Element incrIdMessage= new Element("incrIdMessage");
		incrIdMessage.setText(String.valueOf(this.incrIdMessage));
		racine.addContent(incrIdMessage);

		//Messages
		Element messages= new Element("Messages");
		racine.addContent(messages);

		//Boucle sur les messages
		Element mess = null;
		for (Message m : listeMessages){

			//Message individuel
			mess= new Element("Message");

			Element idM= new Element("idM");
			Element auteur= new Element("auteur");
			Element corps= new Element("corps");

			idM.setText(String.valueOf(this.id));
			auteur.setText(m.getAuteur());
			corps.setText(m.getCorps());

			mess.addContent(idM);
			mess.addContent(auteur);
			mess.addContent(corps);

			messages.addContent(mess);

		}
	}


	public void ParserXML(String fichier){


		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			//Le parsing est terminé ;)
			document = sxb.build(new File(String.valueOf(this.id)));
		}
		catch(Exception e){

			//On initialise un nouvel élément racine avec l'élément racine du document.
			racine = document.getRootElement();

		}

	}

	public void chagerXML(){

	}

	public void enregistre(){
		try{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document, new FileOutputStream(String.valueOf(this.id)));
		}
		catch (java.io.IOException e){}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Message> getListeMessages() {
		return listeMessages;
	}

	public void setListeMessages(ArrayList<Message> listeMessages) {
		this.listeMessages = listeMessages;
	}
	
	

	@Override
	public String toString() {
		return "Forum [id=" + id + ", title=" + title + ", listeMessages="
				+ listeMessages + "]";
	}

	public static void main (String [] args){

		Forum F = new Forum(1,"toto");
		System.out.println(F);
		F.creerMessage("benjmin","c'est ça mon message");
		F.creerMessage("aymeric","c'est ça mon message");
		F.creerMessage("clement","c'est ça mon message");
		F.genererXML();
		F.enregistre();
	}
}
