package game;

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

public class Controller {
	
	// ******************************************************
	// CAMPI INIETTATI (fx:id) - Devono corrispondere all'FXML
	// ******************************************************
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private Button rollDiceButton;
	
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

	// ******************************************************
	// 1. METODO DI INIZIALIZZAZIONE (Chiamato dopo il caricamento FXML)
	// ******************************************************
	
	@FXML
public void initialize() {
	if (statusLabel != null) {
		statusLabel.setText("Benvenuto nel Talisman. Tocca al Giocatore 1. Lancia il Dado!");
	} else {
		System.err.println("ERRORE CRITICO: statusLabel non è stata iniettata da FXML. Rivedere fx:id.");
	}
}

	// ******************************************************
	// 2. GESTIONE DELLE AZIONI (onAction)
	// ******************************************************

	/**
	 * Gestisce l'azione del lancio del dado.
	 */
	@FXML
	private void handleRollDice() {
		int diceResult = (int) (Math.random() * 6) + 1;
		
		statusLabel.setText("Hai lanciato un " + diceResult + ".\nOra muovi il tuo personaggio di " + diceResult + " spazi.");
		
	}

	/**
	 * Gestisce l'apertura dell'inventario (collegato al pulsante "🎒 Inventario").
	 */
	@FXML
	private void handleOpenInventory(ActionEvent event) {
		statusLabel.setText("Hai aperto la borsa dell'inventario. Qui vedrai le tue carte.");
		System.out.println("Inventario aperto.");
	}
	
	/**
	 * Gestisce l'uscita dal gioco (collegato al pulsante "🚪 Esci").
	 */
	@FXML
	private void handleExitGame(ActionEvent event) {
		Node source = (Node) event.getSource();
		
		Stage stage = (Stage) source.getScene().getWindow();
		
		stage.close();
	}
	
}