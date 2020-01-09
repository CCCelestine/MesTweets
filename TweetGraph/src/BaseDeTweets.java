import java.io.*;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.*;

public class BaseDeTweets extends
JApplet{
	private HashSet<Tweets> collTweets;
	private static final long serialVersionUID = 2202072534703043194L;
	private Graphs applet = new Graphs();	

	public void setcollTweets(HashSet<Tweets> collTweets) {
		this.collTweets = collTweets;
	}

	public void initialise() {
		collTweets = new HashSet<Tweets>();
	}

	public HashSet<Tweets> lecture(String fichier) {
		collTweets = new HashSet<Tweets>();
		//récupération du chemin du fichier
		Path orderPath = Paths.get(fichier);
		//création de lines permettant de stocker les lignes du fichier
		List<String> lines = null;
		try {
			//lecture de toutes les lignes du fichier
			lines = Files.readAllLines(orderPath);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier");
		}
		//le compteur ii est utile identifier les lignes a supprimer
		int ii =0;
		try {
			//boucle parcourant toutes les lignes du fichier
			for (int i = 0; i < lines.size()-1; i++) {
				//Separation de nos ligne en fonction du séparateur de notre fichier ("	")
				String[] split = lines.get(i).split("	"); 
				ii=i;
				//On souhaite importer seulement les tweets retweetés
				if (split.length==5) {
					//Affectation des valeurs de la ligne à chaque variables
					String Id = String.valueOf(split[0]);
					String twitto = String.valueOf(split[1]);
					String date = String.valueOf(split[2]);
					String tweet = String.valueOf(split[3]);
					String retweet = String.valueOf(split[4]);
					//instanciation de l'objet Tweets
					Tweets n = new Tweets(Id, twitto, date, tweet, retweet);
					//ajout du tweet à la collection de tweets
					collTweets.add(n);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException ee) { 
			//Affichage des lignes à supprimer dans le fichier de données
			System.out.println("ligne  à supprimer :"+ii+1);
		}
		return collTweets;
	}

	//méthode pour afficher le contenu de notre base de tweets (utilisée lors des phases de vérification)
	public String toString()
	{
		String s = "";
		int num = 1;
		//si la base de tweets est vide on renvoie une erreur
		if(collTweets == null) {
			System.out.println("la base de tweets est vide");        
		} else {
			Iterator<Tweets> iter=collTweets.iterator();
			while(iter.hasNext())
			{
				Tweets n = iter.next();
				s += "-- Actualité " + num + " --\n";
				s += n.toString();
				num++;
			}
		}
		return s;
	}

	//Recupération des statistiques du graph
	public double[] statistique(){		
		applet.build(collTweets);
		double[] stat = applet.statistique();
		return stat;
	} 
	public List<Entry<String, Double>>  pageRank() {
		List<Entry<String, Double>> greatest = applet.pageRank();
		return greatest;
	}
	//Affichage du graphe dans une fenetre
	public void toGraph(){
		JFrame frame = new JFrame();
		//met le graphe crée dans une fenetre
		frame.getContentPane().add(applet);
		frame.setTitle(" Visualisation Graphe");
		frame.pack();
		frame.setVisible(true);
	}

	//appel à la fonction showGraph()
	public void pngGraph() {
		applet.showGraph();
	}

	//appel de la fonction PageRank (top twitto)
	public List<Entry<String, Double>> top() {
		return applet.pageRank();
	}
}
