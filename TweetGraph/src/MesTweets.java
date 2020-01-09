import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

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

		//R�cup�ration de valeurs pour le top 3 twittos avec le plus de retweet
		List<Entry<String, Double>> greatest = baseDeTweets.pageRank();
		Entry<String, Double> entry1 = greatest.get(0);
		String label11 = entry1.getKey();
		Double label12 = entry1.getValue();
		Entry<String, Double> entry2 = greatest.get(1);
		String label21 = entry2.getKey();
		Double label22 = entry2.getValue();
		Entry<String, Double> entry3 = greatest.get(2);
		String label31 = entry3.getKey();
		Double label32 = entry3.getValue();
		
		//Affichage du top3 des twittos 
		labelNun.setText(label11 +" score(x100) : " +Double.toString(Math.round(label12*100)));
		labelNdeux.setText(label21 +" score(x100) : " +Double.toString(Math.round(label22*100)));;
		labelNtrois.setText(label31 +" score(x100) : " +Double.toString(Math.round(label32*100)));;

		//R�cup�ration de l'image cr��e � partir du graph pour l'afficher sur l'interface
		baseDeTweets.pngGraph();
		//Cr�ation d'un objet image null
		Image image = null;
		try {//r�cup�ration de l'image cr��e
			image = new Image(new FileInputStream("ImgGraphe.png"));
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
		//ouverture de la fenetre 
		baseDeTweets.toGraph();

	}

}
