

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

//classe jouant le rôle de controlleur pour traiter les actions
public class controlleur {
	@FXML
	private Button bImport; // Object injected by FXMLLoader(fx:id="btnHello")
	@FXML
	private Label label1;// Object injected by FXMLLoader(fx:id="title")

	@FXML
	private void handleButtonAction(ActionEvent event) { 
		label1.setText("H e l l o   !");
		label1.setTextFill(Color.BLUE);
	}

}
