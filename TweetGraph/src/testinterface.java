// Code : https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/5013971-installez-des-outils
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class testinterface extends Application {

	private Stage stagePrincipal;
	private BorderPane conteneurPrincipal;

	@Override
	public void start(Stage primaryStage) {
		stagePrincipal = primaryStage;
		stagePrincipal.setTitle("Projet JAVA - Graphe");
		initialisationConteneurPrincipal();
	}

	private void initialisationConteneurPrincipal() {
		//création chargeur de FXML et association avec le fichier .fxml généré avec SceneBuilder
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(testinterface.class.getResource("/menu.fxml"));		
		try {
			conteneurPrincipal = (BorderPane) loader.load();
			Scene scene = new Scene(conteneurPrincipal);
			stagePrincipal.setScene(scene); //affectation  de la scene au stage
			stagePrincipal.show();//affichage
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
