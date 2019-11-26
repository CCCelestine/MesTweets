import java.io.Serializable;
import java.time.LocalDate;

	public abstract class Tweets implements Comparable <Tweets>, Serializable{

	    private String id;
	    private String pseudo;
	    private LocalDate date;
	    private String texte;
	    private String rtid;
	    
		public Tweets(String id, String pseudo, LocalDate date, String texte, String rtid) {
	        //les set et get sont utilisés pour vérifier le format
	        setId(id); 
	        setPseudo(pseudo);
	        setDate(date); 
	        setTexte(texte); 
	        setRtid(rtid);
	    }

	    public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public String getPseudo() {
			return pseudo;
		}



		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}



		public LocalDate getDate() {
			return date;
		}



		public void setDate(LocalDate date) {
			this.date = date;
		}



		public String getTexte() {
			return texte;
		}



		public void setTexte(String texte) {
			this.texte = texte;
		}



		public String getRtid() {
			return rtid;
		}



		public void setRtid(String rtid) {
			this.rtid = rtid;
		}

}