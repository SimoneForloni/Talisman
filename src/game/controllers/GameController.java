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

	// Nuovi campi per le statistiche
	@FXML
	private ProgressBar atkBar;
	@FXML
	private ProgressBar defBar;
	@FXML
	private Label spdLabel;

	@FXML
	private Button btnMove;
	@FXML
	private Button btnInventory;
	@FXML
	private Button btnStats;
	@FXML
	private Button btnMap;
	@FXML
	private Button btnQuit;

	private Game game;
	private Player player;
	private GameLogger logger;

	/**
	 * Metodo chiamato dal Main dopo aver caricato l'FXML per inizializzare la
	 * partita.
	 */
	public void initializeGame(Player player) {
		this.player = player;
		// 1. Crea il Logger che scrive sulla TextArea
		this.logger = new GuiLogger(logArea);

		// 2. Crea il gioco iniettando il logger grafico
		this.game = new Game(player, logger);

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

		// Inizializza le statistiche visive (Assumiamo valori base se non bindabili)
		updateStatsUI();

		// Disabilita i bottoni se il giocatore è morto (HP <= 0)
		btnMove.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));
		btnInventory.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));
		btnStats.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));
		btnMap.disableProperty().bind(player.hpProperty().lessThanOrEqualTo(0));

		// 4. Avvia il gioco (messaggio di benvenuto)
		game.start();
	}

	private void updateStatsUI() {
		// Esempio di aggiornamento visuale delle stats.
		// Se Player ha attackProperty() potremmo usare il binding.
		// Qui usiamo i getter standard assumendo un max di 20 per la barra.
		if (atkBar != null)
			atkBar.setProgress(player.getAttack() / 20.0);
		if (defBar != null)
			defBar.setProgress(player.getDefense() / 20.0);
		if (spdLabel != null)
			spdLabel.setText("1.0"); // Placeholder o player.getSpeed()
	}

	@FXML
	private void onMove() {
		game.movePlayer();
		updateStatsUI(); // Aggiorna le stats dopo il movimento (es. level up o oggetti)
	}

	@FXML
	private void onInventory() {
		game.showInventory();
		updateStatsUI(); // L'equipaggiamento potrebbe cambiare le stats
	}

	@FXML
	private void onStats() {
		game.showCharacterSheet();
	}

	@FXML
	private void onMap() {
		// Se Game ha un metodo showMap(), lo chiamiamo.
		// Altrimenti usiamo il logger per feedback.
		logger.log("--- MAPPA ---");
		// game.showMap(); // Decommentare se implementato in Game
		logger.log("Posizione attuale: " + player.getPosition());
	}

	@FXML
	private void onQuit() {
		game.quitGame();
		Stage stage = (Stage) btnQuit.getScene().getWindow();
		stage.close();
	}
}