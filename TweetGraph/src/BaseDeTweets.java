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
import java.util.TreeSet;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BaseDeTweets {
	public TreeSet<Tweets> collTweets;
	public Tweets[] collection = read();
	/*

	public TableView<Tweets> tweetTable = new TableView();
	final TableColumn<Tweets, Integer> idColumn = new TableColumn<>("ID"); 
	final TableColumn<Tweets, String> nomColumn = new TableColumn<>("Twitto"); 
	//TableView.addAll(idColumn, nomColumn);*/

	public void setcollTweets(TreeSet<Tweets> collTweets) {
		this.collTweets = collTweets;
	}

	public void initialise() {
		collTweets = new TreeSet<Tweets>();
	}

	public void ajoute(Tweets Tweets) {
		collTweets.add(Tweets);
	}

	public void parcourir() {
		Iterator<Tweets> it = this.collTweets.iterator();
		int numero=1;
		while (it.hasNext()) {
			Tweets al = (Tweets) (it.next());
			System.out.println(numero);
			System.out.println(al.getId());
			numero=numero+1;
		}
	}

	public void sauve(String fichier) {
		try {
			FileOutputStream w = new FileOutputStream(fichier);
			ObjectOutputStream o = new ObjectOutputStream(w);
			o.writeObject(collTweets);
			o.close();
			w.close();
		} catch (Exception e){ 
			System.out.println("Erreur d�IO");
		}
	}

	//TreeSet au lieu de void car on veut renvoyer un TreeSet
	public TreeSet<Tweets> lecture(String fichier){
		try{
			FileInputStream r = new FileInputStream(fichier);
			ObjectInputStream o = new ObjectInputStream(r);
			//On doit caster l'objet collTweets en TreeSet
			TreeSet<Tweets> collTweets = (TreeSet<Tweets>) o.readObject();
			//pour afficher le contenu du fichier en output
			for (Tweets n: collTweets){
				System.out.println(n);
			}
			o.close();
			r.close();
			return collTweets;
		} catch (Exception e){
			System.out.println("Erreur de lecture");
			return null; //on est obligé de return qqchose
		}
	}

	//test d'importation du fichier
	//https://openclassrooms.com/fr/courses/4975451-demarrez-votre-projet-avec-java/5005441-travaillez-avec-un-fichier-csv
	public Tweets[] read() {
		Scanner scan = new Scanner(System.in);
		System.out.println("saisir un nom de fichier à lire:");
		String fichier = scan.nextLine();
		Path orderPath = Paths.get(fichier);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(orderPath);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier");
		}


		Tweets[] bddTweets = new Tweets[lines.size()]; //tab de type tweet
		System.out.println(("testo"));
		for (int i = 0; i < lines.size()-1; i++) {//boucle parcourant toutes les lignes du fichier

			String[] split = lines.get(i).split("	"); //le separateur de notre fichier

			//essai de faire un tableau avec des obj de type tweets
			String Id = String.valueOf(split[0]);
			String twitto = String.valueOf(split[1]);
			String date = String.valueOf(split[2]);
			String tweet = String.valueOf(split[3]);
			String retweet="";
			if (split.length==5) {//si il y a un rt car ca veit dire qu'on a divisé 5 fois
				retweet = String.valueOf(split[4]);
			}
			bddTweets[i]=new Tweets(Id,twitto,date,tweet,retweet);//creation d'une case d'obj tweets ds le tab
			//

			if (i==5) {//juste un test pour voir les données et comment les recup
				System.out.println(twitto + " a tweeté le " + bddTweets[i].getDate() + " le tweet suivant : " + tweet);
				System.out.println(retweet + " a rt ce tweet");
				
			}

		}
		return bddTweets;
	}


}
