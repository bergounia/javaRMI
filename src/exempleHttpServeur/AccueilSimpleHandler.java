package exempleHttpServeur;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Classe correspondant au handler sur le contexte 'index.html'.
 * @author Cyril Rabat
 * @version 2013/10/02
 */
class AccueilSimpleHandler implements HttpHandler {

    public void handle(HttpExchange t) {
	String reponse = "<h1>Bienvenue sur la page d'accueil</h1>";

	// Recuperation des donnees en GET
	URI requestedUri = t.getRequestURI();
        String query = requestedUri.getRawQuery();

	reponse += "<p>Données en GET : ";
	if(query == null)
	    reponse += "<b>Aucune</b></p>";
	else
	    reponse += "<b>" + query + "</b></p>";

	// Utilisation d'un flux pour lire les donnees du message Http
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new InputStreamReader(t.getRequestBody(),"utf-8"));
	} catch(UnsupportedEncodingException e) {
	    System.err.println("Erreur lors de la recuperation du flux " + e);
	    System.exit(-1);
	}
	
	// Recuperation des donnees en POST
	try {
	    query = br.readLine();
	} catch(IOException e) {
	    System.err.println("Erreur lors de la lecture d'une ligne " + e);
	    System.exit(-1);
	}

	reponse += "<p>Données en POST : ";
	if(query == null)
	    reponse += "<b>Aucune</b></p>";
	else
	    reponse += "<b>" + query + "</b></p>";

	// Envoi de l'entete Http
	try {
	    t.sendResponseHeaders(200, reponse.length());
	} catch(IOException e) {
	    System.err.println("Erreur lors de l'envoi de l'entete : " + e);
	    System.exit(-1);
	}

	// Envoi du corps (donnees HTML)
	try {
	    OutputStream os = t.getResponseBody();
	    os.write(reponse.getBytes());
	    os.close();
	} catch(IOException e) {
	    System.err.println("Erreur lors de l'envoi du corps : " + e);
	}
    }

}
