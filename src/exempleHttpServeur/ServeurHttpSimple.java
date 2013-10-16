package exempleHttpServeur;

import java.io.IOException;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 * Classe correspondant à un serveur Http simple.
 * Le serveur ecoute sur le port 8080 sur le contexte 'index.html'.
 * Le resultat est une simple page qui affiche les donnees envoyees en POST et en GET
 * @author Cyril Rabat
 * @version 2013/10/02
 */
public class ServeurHttpSimple {

    public static void main(String[] args) {	
	HttpServer serveur = null;
	try {
	   serveur = HttpServer.create(new InetSocketAddress(8081), 0);
	} catch(IOException e) {
	    System.err.println("Erreur lors de la creation du serveur " + e);
	    System.exit(-1);
	}

	serveur.createContext("/index.html", new AccueilSimpleHandler());
	serveur.setExecutor(null);
	serveur.start();
    }

}