import java.io.*;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.*;

public class BaseDeTweets extends
JApplet{
	private HashSet<Tweets> collTweets;

	private static final long serialVersionUID = 2202072534703043194L;

	//private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	//private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

	public void setcollTweets(HashSet<Tweets> collTweets) {
		this.collTweets = collTweets;
	}

	public void initialise() {
		collTweets = new HashSet<Tweets>();
	}

	//https://openclassrooms.com/fr/courses/4975451-demarrez-votre-projet-avec-java/5005441-travaillez-avec-un-fichier-csv
	public HashSet<Tweets> lecture(String fichier) {
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
				String Id = String.valueOf(split[0]);
				String twitto = String.valueOf(split[1]);
				String date = String.valueOf(split[2]);
				String tweet = String.valueOf(split[3]);
				String retweet="";
				ii=i;
				if (split.length==5) {//dans le cas où le tweet est retweeté
					retweet = String.valueOf(split[4]);
				}
				//instanciation de l'objet Tweets
				Tweets n = new Tweets(Id, twitto, date, tweet, retweet);
				//ajout du tweet à la collection de tweets
				collTweets.add(n);
			}
		}
		catch (ArrayIndexOutOfBoundsException ee) { 
			//Détection des lignes à supprimer dans le fichier de données
			System.out.println("ligne  à supprimer :"+ii+2);
		}
		return collTweets;
	}
	public int[] statistique(HashSet<Tweets> collTweets) {
		Iterator<Tweets> iter=collTweets.iterator();
		ArrayList<String> utilisateurs = new ArrayList<String>();
		int volume=0;
		while(iter.hasNext())
		{
			Boolean flag=true;
			Boolean flag2=true;
			Tweets n = iter.next();
			String nom = n.getIdTwitto();
			String nomRt = n.getRtid();
			for (String i : utilisateurs) {
				if(nom.compareTo(i)==0) {
					flag=false;
					break;
				}
			}

			for (String i : utilisateurs) {
				if(nomRt.compareTo(i)!=0) {
					flag2=false;
					break;
				}
			}
			if (flag==true) {
				utilisateurs.add(nom);
			}

			if (flag2==true) {
				utilisateurs.add(nomRt);
			}
			if (n.getRtid()!="" & (flag2==true | flag==true)) {
				volume++;				
			}
		}
		//ordre, volume
		int[] res = {utilisateurs.size(),volume};
		return res;
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

		testjGraphT applet = new testjGraphT(collTweets);
		applet.init();
		//met le graphe crée dans une fenetre
		JFrame frame = new JFrame();
		frame.getContentPane().add(applet);
		frame.setTitle("Ma premiere visu GraphX");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);  
	} 
}
