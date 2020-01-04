//import java.util.Iterator;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashSet;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MesTweets {
	
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
                    quitter();
                    break;
                default:
                    break;
            }
        }
    }
    
    public static int afficher_menu() {
        Scanner scan = new Scanner(System.in);
        int nb;
        System.out.print("Saisissez un nombre (1 import/ 2 affichage / 3 quitter) : ");
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
    
    public static void quitter() {
    	System.out.println("au revoir");
    }
}
