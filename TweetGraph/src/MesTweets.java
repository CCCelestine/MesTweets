//import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class MesTweets {
    public static void main(String[] args) {
        System.out.println("importer les données");
        BaseDeTweets baseDeTweets = new BaseDeTweets();
        int nb = 0;
        while (nb != 8) {
            nb = afficher_menu();
            
            switch (nb) {
                case 1:
                    creer(baseDeTweets);
                    //System.out.println("creer");
                    break;
                case 2:
                    ouvrir(baseDeTweets);
                    break;
                case 8:
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
        System.out.print("Saisissez un nombre (1 créer / 2 importer) : ");

        nb = scan.nextInt();
        System.out.println("Vous avez saisi le nombre : " + nb);

        return nb;
    }
    
    public static void creer(BaseDeTweets baseDeTweets) {
        baseDeTweets.initialise();
    }

    public static void ouvrir(BaseDeTweets baseDeTweets) {
        Scanner scan = new Scanner(System.in);
    	System.out.println("saisir un nom de fichier à lire:");
    	String fichier = scan.nextLine();
    	TreeSet test = baseDeTweets.lecture(fichier);
        baseDeTweets.setcollTweets(test);
    }
    
    public static void quitter() {
    }
}
