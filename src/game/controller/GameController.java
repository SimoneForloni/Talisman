package game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ToolBar;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import game.model.*;
import game.service.*;

public class GameController {

	private GameManager gameManager;

	// ******************************************************
	// CAMPI INIETTATI (fx:id) - Devono corrispondere all'FXML
	// ******************************************************

	@FXML
	private Label statusLabel;
	@FXML
	private Button rollDiceButton;
	@FXML
	private Button attackButton;
	@FXML
	private Button defendButton;
	@FXML
	private Button fleeButton;

	@FXML
	private VBox mainMenuVBox;
	@FXML
	private HBox topPanel;
	@FXML
	private HBox playerStatsBarHBox;
	@FXML
	private AnchorPane combatAreaAnchorPane;
	@FXML
	private VBox leftMenuVBox;
	@FXML
	private VBox enemyStatsVBox;
	@FXML
	private ToolBar mainToolbar;

	// Label delle statistiche giocatore
	@FXML
	private Label playerNameLabel;
	@FXML
	private Label playerHpLabel;
	@FXML
	private Label playerStrengthLabel;
	@FXML
	private Label playerDefenseLabel;
	@FXML
	private Label playerXpLabel;
	@FXML
	private Label playerCoinsLabel;
	@FXML
	private Label positionLabel;

	// Label delle statistiche nemico
	@FXML
	private Label enemyNameLabel;
	@FXML
	private Label enemyHpLabel;
	@FXML
	private Label enemyAttackLabel;
	@FXML
	private Label enemyDefenseLabel;

	// ******************************************************
	// INIZIALIZZAZIONE
	// ******************************************************

	@FXML
	public void initialize() {
		gameManager = GameManager.getInstance();
		gameManager.startNewGame("Eroe");

		updatePlayerStats();

		if (statusLabel != null) {
			statusLabel.setText("Benvenuto nel Talisman, " +
					gameManager.getCurrentPlayer().getName() +
					"!\nLancia il dado per iniziare l'avventura.");
		}
	}

	// ******************************************************
	// GESTIONE DELLE AZIONI
	// ******************************************************

	@FXML
	private void handleRollDice() {
		// Player player = gameManager.getCurrentPlayer();
		GameState gameState = gameManager.getGameState();

		// Non permettere di lanciare il dado durante il combattimento
		if (gameState.getInCombat()) {
			statusLabel.setText("Sei in combattimento! Devi sconfiggere il nemico prima di muoverti.");
			return;
		}

		int diceResult = gameManager.rollDice();

		statusLabel.setText("🎲 Hai lanciato un " + diceResult +
				"!\nTi sei mosso alla posizione " +
				gameState.getBoardPosition());

		updatePlayerStats();

		// Controlla se è iniziato un combattimento
		if (gameState.getInCombat()) {
			startCombatUI();
		}
	}

	@FXML
	private void handleAttack() {
		Player player = gameManager.getCurrentPlayer();
		Enemy enemy = gameManager.getGameState().getCurrentEnemy();
		CombatService combat = gameManager.getCombatService();

		if (enemy == null)
			return;

		// Attacco del giocatore
		int playerDamage = combat.playerAttack(player, enemy);
		String message = "⚔ Hai attaccato " + enemy.getName() +
				" infliggendo " + playerDamage + " danni!\n";

		// Controlla se il nemico è morto
		if (combat.checkVictory(enemy)) {
			message += "\n🎉 Vittoria! Hai sconfitto " + enemy.getName() + "!\n";
			message += "Ricompense: +" + enemy.getXpReward() + " XP, +" +
					enemy.getCoinsReward() + " monete";

			combat.endCombat(player, enemy);
			endCombatUI();
			statusLabel.setText(message);
			updatePlayerStats();
			return;
		}

		// Contrattacco del nemico
		int enemyDamage = combat.enemyAttack(player, enemy);
		message += enemy.getName() + " contrattacca infliggendo " +
				enemyDamage + " danni!\n";

		// Controlla se il giocatore è morto
		if (combat.checkDefeat(player)) {
			message += "\n💀 Sei stato sconfitto! Game Over.";
			gameOver();
		}

		statusLabel.setText(message);
		updatePlayerStats();
		updateEnemyStats();
	}

	@FXML
	private void handleDefend() {
		// Implementa la difesa (riduce il danno del prossimo attacco)
		statusLabel.setText("🛡 Ti sei messo in posizione difensiva!\n" +
				"Il prossimo attacco nemico infliggerà meno danni.");

		// Qui dovresti implementare la logica di difesa
		// Per ora, facciamo solo l'attacco del nemico con danno ridotto
		Player player = gameManager.getCurrentPlayer();
		Enemy enemy = gameManager.getGameState().getCurrentEnemy();
		CombatService combat = gameManager.getCombatService();

		if (enemy == null)
			return;

		int damage = combat.calculateDamage(enemy.getAttack(), player.getDefense() * 2);
		player.takeDamage(damage);

		statusLabel.setText(statusLabel.getText() + "\n" +
				enemy.getName() + " attacca ma infligge solo " +
				damage + " danni grazie alla tua difesa!");

		updatePlayerStats();
	}

	@FXML
	private void handleFlee() {
		Player player = gameManager.getCurrentPlayer();
		CombatService combat = gameManager.getCombatService();

		boolean success = combat.attemptFlee(player);

		if (success) {
			statusLabel.setText("🏃 Sei fuggito con successo dal combattimento!");
			endCombatUI();
		} else {
			// Il nemico attacca dopo il tentativo fallito di fuga
			Enemy enemy = gameManager.getGameState().getCurrentEnemy();
			int damage = combat.enemyAttack(player, enemy);

			statusLabel.setText("🏃 Tentativo di fuga fallito!\n" +
					enemy.getName() + " ti attacca durante la fuga, " +
					"infliggendo " + damage + " danni!");

			if (combat.checkDefeat(player)) {
				statusLabel.setText(statusLabel.getText() + "\n💀 Sei stato sconfitto!");
				gameOver();
			}
		}

		updatePlayerStats();
	}

	@FXML
	private void handleOpenInventory(ActionEvent event) {
		statusLabel.setText("🎒 Inventario aperto.\nQui vedrai le tue carte e oggetti.");
		System.out.println("Inventario aperto.");
	}

	@FXML
	private void handleSaveGame() {
		statusLabel.setText("💾 Gioco salvato! (Funzionalità da implementare)");
		System.out.println("Salvataggio del gioco...");
	}

	@FXML
	private void handleExitGame(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	// ******************************************************
	// METODI HELPER PER L'UI
	// ******************************************************

	private void startCombatUI() {
		Enemy enemy = gameManager.getGameState().getCurrentEnemy();

		// Mostra le statistiche del nemico
		enemyStatsVBox.setVisible(true);
		enemyStatsVBox.setManaged(true);
		updateEnemyStats();

		// Mostra i pulsanti di combattimento
		attackButton.setVisible(true);
		attackButton.setManaged(true);
		defendButton.setVisible(true);
		defendButton.setManaged(true);
		fleeButton.setVisible(true);
		fleeButton.setManaged(true);

		// Nascondi il pulsante del dado
		rollDiceButton.setVisible(false);
		rollDiceButton.setManaged(false);

		statusLabel.setText("⚔ Combattimento iniziato contro " + enemy.getName() + "!");
	}

	private void endCombatUI() {
		// Nascondi le statistiche del nemico
		enemyStatsVBox.setVisible(false);
		enemyStatsVBox.setManaged(false);

		// Nascondi i pulsanti di combattimento
		attackButton.setVisible(false);
		attackButton.setManaged(false);
		defendButton.setVisible(false);
		defendButton.setManaged(false);
		fleeButton.setVisible(false);
		fleeButton.setManaged(false);

		// Mostra il pulsante del dado
		rollDiceButton.setVisible(true);
		rollDiceButton.setManaged(true);
	}

	private void updatePlayerStats() {
		Player player = gameManager.getCurrentPlayer();

		playerNameLabel.setText("👤 " + player.getName());
		playerHpLabel.setText("❤ HP: " + player.getHp() + "/100");
		playerStrengthLabel.setText("💪 STR: " + player.getStrength());
		playerDefenseLabel.setText("🛡 DEF: " + player.getDefense());
		playerXpLabel.setText("⭐ XP: " + player.getXp());
		playerCoinsLabel.setText("💰 Coins: " + player.getCoins());
		positionLabel.setText("📍 Posizione: " + gameManager.getGameState().getBoardPosition());
	}

	private void updateEnemyStats() {
		Enemy enemy = gameManager.getGameState().getCurrentEnemy();

		if (enemy != null) {
			enemyNameLabel.setText(enemy.getName());
			enemyHpLabel.setText("❤ HP: " + enemy.getHp());
			enemyAttackLabel.setText("⚔ ATK: " + enemy.getAttack());
			enemyDefenseLabel.setText("🛡 DEF: " + enemy.getDefense());
		}
	}

	private void gameOver() {
		rollDiceButton.setDisable(true);
		attackButton.setDisable(true);
		defendButton.setDisable(true);
		fleeButton.setDisable(true);
	}
}