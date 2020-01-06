

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//classe jouant le rôle de controlleur pour traiter les actions
public class controlleur extends MesTweets {
	@FXML
	private Button bImport; // Object injected by FXMLLoader(fx:id="btnHello")
	@FXML
	private Label label1;// Object injected by FXMLLoader(fx:id="title")
	//private Label labelVerif;
	@FXML
	
	private void handleButtonAction(ActionEvent event) { 
		BaseDeTweets baseDeTweets = new BaseDeTweets();
		importer(baseDeTweets);
		label1.setText("Données importées avec succès");
		label1.setTextFill(Color.GREEN);
	}

}
