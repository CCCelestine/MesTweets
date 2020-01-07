import java.awt.Dimension;
import java.io.*;
import java.net.*;
import java.util.*;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class MesTweets 
{
	@FXML
	public Button bImport; 
	public Label labelMsg, labelOrdre, labelDegre, labelVolume, labelDiametre;
	public TextField textRacine;
	@FXML
	/*
                case 3:
                    afficherGraph(baseDeTweets);
                    break;
                case 4:

    }*/

	//C:/Users/Laura/Documents/M1 INFO/Projet JAVA/MesTweets/TweetGraph/climat2.txt
	public static int afficher_menu() {
		Scanner scan = new Scanner(System.in);
		int nb;
		System.out.print("Saisissez un nombre (1 import/ 2 affichage / 3 graph / 4 quitter) : ");
		nb = scan.nextInt();
		System.out.println("Vous avez saisi le nombre : " + nb);
		return nb;
	}

	public void importer(BaseDeTweets baseDeTweets) {
		baseDeTweets.initialise();
		System.out.println(textRacine.getText());
		HashSet<Tweets> bdd = baseDeTweets.lecture(textRacine.getText());
		baseDeTweets.setcollTweets(bdd);
		//calcul des statistique en récuperant la fonction
		int[] stat = baseDeTweets.statistique(bdd);
		//affichage sur l'interface des staistiques
		labelOrdre.setText(Integer.toString(stat[0]));
		labelOrdre.setTextFill(Color.BLACK);
		labelVolume.setText(Integer.toString(stat[1]));
		labelVolume.setTextFill(Color.BLACK);

	}

	public static void afficher(BaseDeTweets baseDeTweets) {
		String res = baseDeTweets.toString();
		System.out.println(res);
	}

	public static void afficherGraph(BaseDeTweets baseDeTweets) {
		baseDeTweets.toGraph();
	}

	public static void SavePNG(BaseDeTweets baseDeTweets) {
		//baseDeTweets.saveImage("test2.png");
	}

	public static void quitter() {
		System.out.println("au revoir");
	}
	public void importationAction(ActionEvent event) { 
		BaseDeTweets baseDeTweets = new BaseDeTweets();
		HashSet<Tweets> collTweets = new HashSet<Tweets>();
		try {//Importation des données
			importer(baseDeTweets);
			labelMsg.setText("Données importées avec succès");			
			labelMsg.setTextFill(Color.GREEN);
		} catch (java.lang.NullPointerException e) //racine du fichier mauvaise
		{	
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Adresse invalide - Données non importées");
			
		}
		catch(java.nio.file.InvalidPathException ee) {
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Adresse invalide - Données non importées");
		}
		
		//affichage des statistiques
		//int[] stat=statistique(collTweets);
	}
}
