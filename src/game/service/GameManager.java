package game.service;

import game.model.*;

@SuppressWarnings("unused")
public class GameManager {
	private static GameManager instance;
	private Player currentPlayer;
	private GameState gameState;
	
	private GameManager() {}
	
	/**
	 * The function `getInstance` returns the singleton instance of the `GameManager` class, creating it
	 * if it doesn't already exist.
	 * 
	 * @return The `GameManager` instance is being returned.
	 */
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
}