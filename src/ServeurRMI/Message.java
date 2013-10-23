package ServeurRMI;

public class Message {
	
	private int id;
	private String auteur;
	private String corps;
	

	public Message(){
		
	}
	
	public Message(int id, String auteur, String corps){
		this.auteur=auteur;
		this.id=id;
		this.corps=corps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", auteur=" + auteur + ", corps=" + corps
				+ "]";
	}
	
	
	
	
}
