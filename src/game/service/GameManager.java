package game.service;

import game.Model.*;

@SuppressWarnings("unused")
public class GameManager {
	private static GameManager instance;
	private Player currentPlayer;
	private GameState gameState;
	
	private GameManager() {}
	
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
}