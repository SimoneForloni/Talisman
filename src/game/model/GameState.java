package game.model;

// @SuppressWarnings("unused")
public class GameState {
  private String currentPhase;
  private int turnCount;
  private int boardPosition;
  private boolean inCombat;
  private boolean gameOver;
  private Enemy currentEnemy;

  // ===========================================================
  // COSTRUTTORI
  // ===========================================================

  public GameState() {
    this.currentPhase = "START";
    this.turnCount = 1;
    this.boardPosition = 0;
    this.inCombat = false;
    this.gameOver = false;
    this.currentEnemy = null;
  }

  // ===========================================================
  // GETTER
  // ===========================================================

  public String getCurrentPhase() { return currentPhase; }
  public int getTurnCount() { return turnCount; }
  public int getBoardPosition() { return boardPosition; }
  public boolean getInCombat() { return inCombat; }
  public boolean getGameOver() { return gameOver; }
  public Enemy getCurrentEnemy() { return currentEnemy; }

  // ===========================================================  
  // SETTER
  // ===========================================================

  public void setCurrentPhase(String currentPhase) { this.currentPhase = currentPhase; }
  public void setTurnCount(int turnCount) { this.turnCount = turnCount; }
  public void setBoardPosition(int boardPosition) { this.boardPosition = boardPosition; }
  public void setInCombat(boolean inCombat) { this.inCombat = inCombat; }
  public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
  public void setCurrentEnemy(Enemy currentEnemy) { this.currentEnemy = currentEnemy; }

  // ===========================================================  
  // METODI
  // ===========================================================

  public void nextTurn() {
    this.turnCount++;
  }

  public void startCombat(Enemy enemy) {
    this.currentEnemy = enemy;
    this.inCombat = true;
    this.currentPhase = "COMBACT";
  }

  public void endCombat() {
    this.currentEnemy = null;
    this.inCombat = false;
    this.currentPhase = "IDLE";
  }
}