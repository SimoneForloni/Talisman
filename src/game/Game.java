package game;

import game.model.Player;
import game.util.Methods;

public class Game {
  private Player player;
  private boolean isRunning;
  
  Game(Player player) {
    this.player = player;
    this.isRunning = false;
  }

  public void start() {
    this.isRunning = true;
    System.out.printf("\nWelcome to talisman %s", player.getName());

    while (isRunning) {
      playTurn();
    }
  }
  
  private void playTurn() {
    showGameMenu();
    switch (Methods.readNumber(1, 4)) {
      case 1 -> {
        
      }
      case 2 -> {
        
      }
      case 3 -> {
        
      }
      default -> {
        isRunning = false;
      }
    }
  }
  
  private void showGameMenu() {
    Methods.clearScreen();
    System.out.println("\n=== Your turn ===");
    System.out.println("\nPosition: " + player.getPosition() + "\n");
    System.out.println("\n1) Roll dice");
    System.out.println("\n2) Open inventory");
    System.out.println("\n3) Open stats screen");
    System.out.println("\n4) Save and quit\n");
    System.out.println("\nYour choice: ");
  }

  private void movePlayer() {
    int dice = (int) (Math.random() * 6) + 1;
    System.out.println("\nResult: " + dice);
    player.setPosition(player.getPosition() + dice);
    System.out.println("");
  }
}