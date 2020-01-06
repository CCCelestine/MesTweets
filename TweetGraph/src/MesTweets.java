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

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MesTweets 
{
	
    public static void main(String[] args) {
        BaseDeTweets baseDeTweets = new BaseDeTweets();
        

        int nb = 0;
        
        while (nb != 3) {
            nb = afficher_menu();           
            switch (nb) {
                case 1:
                	//System.out.println("ouvrir");
                    importer(baseDeTweets);
                    break;
                case 2:
                	//System.out.println("afficher");
                	//C:/Users/Laura/Documents/M1 INFO/Projet JAVA/MesTweets/TweetGraph/climat.txt
                    afficher(baseDeTweets);
                    break;
                case 3:
                    afficherGraph(baseDeTweets);
                    break;
                case 4:
                    quitter();
                    break;
                case 5:
                	//sauvegarder comme une image png
                	
                default:
                    break;
            }
        }
    }
     
    
    public static int afficher_menu() {
        Scanner scan = new Scanner(System.in);
        int nb;
        System.out.print("Saisissez un nombre (1 import/ 2 affichage / 3 graph / 4 quitter) : ");
        nb = scan.nextInt();
        System.out.println("Vous avez saisi le nombre : " + nb);
        return nb;
    }
    
    public static void importer(BaseDeTweets baseDeTweets) {
    	baseDeTweets.initialise();
        Scanner scan = new Scanner(System.in);
    	System.out.println("saisir un nom de fichier à lire:");
    	String fichier = scan.nextLine();
    	HashSet<Tweets> test = baseDeTweets.lecture(fichier);
        baseDeTweets.setcollTweets(test);
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
}
