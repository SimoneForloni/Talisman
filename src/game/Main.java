package game;

import game.controllers.GameController;
import game.model.Player;
import game.util.CharacterClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/view/game.fxml"));
			Parent root = loader.load();

			GameController controller = loader.getController();

			// Creiamo un giocatore di default per iniziare
			Player player = new Player("Geralt", CharacterClass.WARRIOR);
			controller.initializeGame(player);

			primaryStage.setTitle("Talisman RPG");

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/game/view/style.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}