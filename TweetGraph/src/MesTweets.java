import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MesTweets 
{
	public BaseDeTweets baseDeTweets = new BaseDeTweets();
	
	//D�claration des variables des objets FXML pour l'interface
	@FXML
	public Button buttonImport, buttonOuvrir; 
	public Label labelMsg, labelOrdre, labelDegre, labelVolume, labelDiametre;
	public Label labelNun,labelNdeux,labelNtrois;
	public TextField textRacine;
	public Pane paneGraph;
	public ImageView imageGraph;
	@FXML

	//Importation des donn�es
	public void importer(BaseDeTweets baseDeTweets) {
		//initialisation de la base de donn�es
		baseDeTweets.initialise(); 
		//lecture du fichier dans un objet HasSet de type Tweets
		HashSet<Tweets> bdd = baseDeTweets.lecture(textRacine.getText());
		baseDeTweets.setcollTweets(bdd);
	}
	//Actions du bouton "Importation" effectu�es lors du d�clenchement du bouton importation
	public void importationAction(ActionEvent event) { 
		try {
			importer(baseDeTweets);
			//Affichage dans l'interface d'un message sur la r�ussite de l'importation
			labelMsg.setText("Donn�es import�es avec succ�s");			
			labelMsg.setTextFill(Color.GREEN);
		} catch (java.lang.NullPointerException e) 
		{	//Affichage dans l'interface d'un message sur l'�chec de l'importation (racine mauvaise)
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Fichier inexistant - Donn�es non import�es");

		}
		catch(java.nio.file.InvalidPathException ee) {
			//Affichage dans l'interface d'un message sur l'�chec de l'importation
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Adresse invalide - V�rifier la syntaxe de l'adresse");
		}
		//Apr�s l'importation, on affiche les statistiques dans l'interface 
		//R�cuperation des resulats de la fonction toGraph()
		double[] stat =baseDeTweets.statistique();

		//affichage sur l'interface des statistiques
		labelOrdre.setText(Double.toString(stat[0]));
		labelOrdre.setTextFill(Color.BLACK);

		labelVolume.setText(Double.toString(stat[1]));
		labelVolume.setTextFill(Color.BLACK);

		labelDiametre.setText(Double.toString(stat[2]));
		labelDiametre.setTextFill(Color.BLACK);
		//calcul du degre moyen (arrondi) grace � l'ordre et au volume
		labelDegre.setText(Double.toString(Math.round((stat[1]*2)/stat[0])));
		labelDegre.setTextFill(Color.BLACK);
		
		//Affichage du top5 des twittos avec le plus de retweet
		labelNun.setText(Double.toString(stat[2]));
		labelNdeux.setText(Double.toString(stat[2]));
		labelNtrois.setText(Double.toString(stat[2]));
	//	labelNquatre.setText();
		//labelNcinq.setText();
		
		//R�cup�ration de l'image cr��e � partir du graph pour l'afficher sur l'interface
		//Cr�ation d'un objet image null
		Image image = null;
		baseDeTweets.pngGraph();
		try {//r�cup�ration de l'image cr��e
			image = new Image(new FileInputStream("ImageGraphe.png"));
		} catch (FileNotFoundException e) { 
			//Affichage dans l'interface d'un message sur l'�chec de l'affichage de l'image
			labelMsg.setTextFill(Color.RED);
			labelMsg.setText("Image non trouv�e");
		}
		//Affectation de l'image en png dans l'objet ImageGraph
		imageGraph.setImage(image);
	}
	
	//Action du bouton "ouvrir le graphe" pour ouvrir une fenetre permettant de parcourir le graph
	public void ouvrirFenetre(ActionEvent event) {
		baseDeTweets.toGraph();
	}

}
