package game.controllers;

import game.Game;
import game.model.Player;
import game.service.loggers.GameLogger;
import game.service.loggers.GuiLogger;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GameController {

	@FXML
	private TextArea logArea;
	@FXML
	private ProgressBar hpBar;
	@FXML
	private Label hpTextLabel;
	@FXML
	private Label coinsLabel;
	@FXML
	private Label nameLabel;

	@FXML
	private Button btnMove;
	@FXML
	private Button btnInventory;
	@FXML
	private Button btnStats;
	@FXML
	private Button btnQuit;

	private Game game;
	private Player player;

	/**
	 * Metodo chiamato dal Main dopo aver caricato l'FXML per inizializzare la
	 * partita.
	 */
	public void initializeGame(Player player) {
		this.player = player;
		// 1. Crea il Logger che scrive sulla TextArea
		GameLogger guiLogger = new GuiLogger(logArea);

		// 2. Crea il gioco iniettando il logger grafico
		this.game = new Game(player, guiLogger);

		// 3. Data Binding: Collega le proprietà del Player alla GUI
		nameLabel.setText(player.getName() + " (" + player.getCharacterClass() + ")");

		// Barra della vita: hp / maxHp
		hpBar.progressProperty().bind(
				player.hpProperty().divide(player.maxHpProperty().multiply(1.0)));

		// Testo HP: "80/100"
		hpTextLabel.textProperty().bind(
				Bindings.concat(player.hpProperty(), "/", player.maxHpProperty()));

		// Monete
		coinsLabel.textProperty().bind(player.coinsProperty().asString());

		// Disabilita i bottoni se il giocatore è morto (HP <= 0)
		btnMove.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));
		btnInventory.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));
		btnStats.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));

		// 4. Avvia il gioco (messaggio di benvenuto)
		game.start();
	}

	@FXML
	private void onMove() {
		game.movePlayer();
	}

	@FXML
	private void onInventory() {
		game.showInventory();
	}

	@FXML
	private void onStats() {
		game.showCharacterSheet();
	}

	@FXML
	private void onQuit() {
		game.quitGame();
		Stage stage = (Stage) btnQuit.getScene().getWindow();
		stage.close();
	}
}