import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MesTweets 
{
		//Déclaration des variables des objets FXML pour l'interface
	@FXML
	public Button bImport; 
	public Label labelMsg, labelOrdre, labelDegre, labelVolume, labelDiametre;
	public TextField textRacine;
	@FXML
	
	//Importation des données, calculs des statistiques
	public void importer(BaseDeTweets baseDeTweets) {
		//importation
		baseDeTweets.initialise();
		System.out.println(textRacine.getText());
		HashSet<Tweets> bdd = baseDeTweets.lecture(textRacine.getText());
		baseDeTweets.setcollTweets(bdd);

		//calcul des statistique en récuperant la fonction
		//int[] stat = baseDeTweets.statistique(bdd);
		/*//affichage sur l'interface des staistiques
		labelOrdre.setText(Integer.toString(stat[0]));
		labelOrdre.setTextFill(Color.BLACK);
		labelVolume.setText(Integer.toString(stat[1]));
		labelVolume.setTextFill(Color.BLACK);
		afficherGraph(baseDeTweets);*/

	}

	public static void afficherGraph(BaseDeTweets baseDeTweets) {
		baseDeTweets.toGraph();
	}

	public static void SavePNG(BaseDeTweets baseDeTweets) {
		//baseDeTweets.saveImage("test2.png");
	}

	public void importationAction(ActionEvent event) { 
		BaseDeTweets baseDeTweets = new BaseDeTweets();
	//	HashSet<Tweets> collTweets = new HashSet<Tweets>();
		try {//Importation des données
			importer(baseDeTweets);
			labelMsg.setText("Données importées avec succès");			
			labelMsg.setTextFill(Color.GREEN);
		} catch (java.lang.NullPointerException e) //racine du fichier mauvaise
		{	
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Fichier inexistant - Données non importées");
			
		}
		catch(java.nio.file.InvalidPathException ee) {
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Adresse invalide - Vérifier la syntaxe de l'adresse");
		}
	}
}
