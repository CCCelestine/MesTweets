import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BaseDeTweets {
	private HashSet<Tweets> collTweets;

	/*public TableView<Tweets> tweetTable = new TableView();
	final TableColumn<Tweets, Integer> idColumn = new TableColumn<>("ID"); 
	final TableColumn<Tweets, String> nomColumn = new TableColumn<>("Twitto"); 
	//TableView.addAll(idColumn, nomColumn);*/

	public void setcollTweets(HashSet<Tweets> collTweets) {
		this.collTweets = collTweets;
	}

	public void initialise() {
		collTweets = new HashSet<Tweets>();
	}

	//On veut renvoyer un objet de type HashSet
	//test d'importation du fichier
	//https://openclassrooms.com/fr/courses/4975451-demarrez-votre-projet-avec-java/5005441-travaillez-avec-un-fichier-csv
	public HashSet lecture(String fichier) {
		collTweets = new HashSet<Tweets>();
		Path orderPath = Paths.get(fichier);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(orderPath);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier");
		}
		int ii =0;
		try {
			for (int i = 0; i < lines.size()-1; i++) {//boucle parcourant toutes les lignes du fichier
				String[] split = lines.get(i).split("	"); //le separateur de notre fichier
				//essai de faire un tableau avec des obj de type tweets
				String Id = String.valueOf(split[0]);
				String twitto = String.valueOf(split[1]);
				String date = String.valueOf(split[2]);
				String tweet = String.valueOf(split[3]);
				String retweet="";
				ii=i;
				if (split.length==5) {//si il y a un rt car ca veit dire qu'on a divisé 5 fois
					retweet = String.valueOf(split[4]);
				}
				//instanciation de l'objet Tweets
				Tweets n = new Tweets(Id, twitto, date, tweet, retweet);
				//ajout du tweet à la collection de tweets
				collTweets.add(n);
			}
		}
		catch (ArrayIndexOutOfBoundsException ee) { //erreur quand les lignes sont pas bonnes
			System.out.println("ligne  a supprimer :"+ii+2);
		}
		return collTweets;
	}

	//méthode pour afficher le contenu de notre base de tweets
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
	
	public void toGraph(){
    	Graph graph = new SingleGraph("Tutorial 1");

    	Iterator<Tweets> iter=collTweets.iterator();
    	ArrayList<String> myNumbers = new ArrayList<String>();
    	Boolean flag=true;
        while(iter.hasNext())
        {
            Tweets n = iter.next();
            String nom = n.getIdTwitto();
            for (String i : myNumbers) {
            	if(nom.compareTo(i)==0) {
            		flag=false;
            		break;
            	}
            }
        	if(flag==true) {
        		graph.addNode(nom);
        		myNumbers.add(nom);
        	}
        }

		graph.display();
    } 
}
