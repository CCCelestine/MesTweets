import java.io.Serializable;
import java.time.LocalDate;

public class Tweets {

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


}