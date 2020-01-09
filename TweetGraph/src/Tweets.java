import java.io.Serializable;

public class Tweets implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String idTwitto;
	protected String date;
	protected String texte;
	protected String rtid;

	public Tweets(String id, String twitto, String date, String texte, String rtid) {
		//les set et get sont utilisés pour vérifier le format
		setId(id); 
		setIdTwitto(twitto);
		setDate(date); 
		setTexte(texte); 
		setRtid(rtid);
	}

	public String getIdTwitto() {
		return idTwitto;
	}

	public void setIdTwitto(String idTwitto) {
		this.idTwitto = idTwitto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	//Fonction créée pour la vérification de l'importation des données
	public String toString() {     
		String result =  "Id : " + getId() + "\n";
		result +=  "Id Twitto : " + getIdTwitto() + "\n";
		result +=  "date : " + getDate() + "\n";
		result +=  "texte : " + getTexte() + "\n";
		result +=  "rtid : " + getRtid() + "\n";

		return result;	
	}
}