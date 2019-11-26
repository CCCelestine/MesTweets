import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeSet;

public class BaseDeTweets {
public TreeSet collTweets;
    
    public void setcollTweets(TreeSet collTweets) {
        this.collTweets = collTweets;
    }

    public void initialise() {
        collTweets = new TreeSet();
    }

    public void ajoute(Tweets Tweets) {
        collTweets.add(Tweets);
    }

    public void parcourir() {
        Iterator it = this.collTweets.iterator();
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
    public TreeSet lecture(String fichier){
        try{
            FileInputStream r = new FileInputStream(fichier);
            ObjectInputStream o = new ObjectInputStream(r);
            //On doit caster l'objet collTweets en TreeSet
            TreeSet<Tweets> collTweets = (TreeSet) o.readObject();
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
}
