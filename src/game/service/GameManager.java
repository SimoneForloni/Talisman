package game.service;

import game.model.*;

public class GameManager {
	private static GameManager instance;
	private Player currentPlayer;
	private GameState gameState;
	private CombatService combatService;
	private InventoryService inventoryService;

	private GameManager() {
		this.gameState = new GameState();
		this.combatService = new CombatService(gameState);
		this.inventoryService = new InventoryService();
	}

	/**
	 * Singleton pattern - restituisce l'unica istanza di GameManager
	 */
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	/**
	 * Inizializza una nuova partita con un giocatore
	 */
	public void startNewGame(String playerName) {
		this.currentPlayer = new Player(playerName);
		this.gameState = new GameState();
		System.out.println("Nuova partita iniziata con " + playerName);
	}

	/**
	 * Esegue il lancio del dado e muove il giocatore
	 */
	public int rollDice() {
		int result = (int) (Math.random() * 6) + 1;
		movePlayer(result);
		return result;
	}

	/**
	 * Muove il giocatore di un certo numero di spazi
	 */
	public void movePlayer(int spaces) {
		int newPosition = gameState.getBoardPosition() + spaces;
		gameState.setBoardPosition(newPosition);
		currentPlayer.setPosition(newPosition);

		// Dopo il movimento, potrebbe verificarsi un evento
		checkForEvent(newPosition);
	}

	/**
	 * Controlla se c'è un evento nella posizione corrente
	 */
	private void checkForEvent(int position) {
		// Esempio: ogni 5 caselle c'è un combattimento
		if (position % 5 == 0 && position > 0) {
			spawnRandomEnemy();
		}
	}

	/**
	 * Genera un nemico casuale
	 */
	public Enemy spawnRandomEnemy() {
		// Array di possibili nemici
		String[] enemyNames = { "Goblin", "Orco", "Scheletro", "Bandito", "Lupo" };

		// Genera statistiche casuali basate sul progresso del giocatore
		int level = gameState.getBoardPosition() / 10 + 1;
		String name = enemyNames[(int) (Math.random() * enemyNames.length)];
		int hp = 20 + (level * 10);
		int attack = 3 + level;
		int defense = 2 + level;
		int xpReward = 10 + (level * 5);
		int coinsReward = 5 + (level * 3);

		Enemy enemy = new Enemy(name, hp, attack, defense, xpReward, coinsReward);
		combatService.startCombat(currentPlayer, enemy);

		return enemy;
	}

	/**
	 * Avanza al prossimo turno
	 */
	public void nextTurn() {
		gameState.nextTurn();
	}

	// ==================== GETTER ====================

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public GameState getGameState() {
		return gameState;
	}

	public CombatService getCombatService() {
		return combatService;
	}

	public InventoryService getInventoryService() {
		return inventoryService;
	}

	// ==================== SETTER ====================

	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}

	/**
	 * Resetta il gioco (per nuova partita)
	 */
	public void resetGame() {
		this.currentPlayer = null;
		this.gameState = new GameState();
		this.combatService = new CombatService(gameState);
		System.out.println("Gioco resettato");
	}
}