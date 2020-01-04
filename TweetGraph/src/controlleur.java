import java.awt.Button;
import java.awt.Color;
import java.awt.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//classe jouant le rôle de controlleur pour traiter les actions
public class controlleur {
	@FXML
	private Button bImport; // Object injected by FXMLLoader(fx:id="btnHello")
	@FXML
	private Label label1;// Object injected by FXMLLoader(fx:id="title")

	@FXML
	private void handleButtonAction(ActionEvent event) { 
		label1.setText("H e l l o   !");
		label1.setForeground(Color.blue);
	}

}
