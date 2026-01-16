package game;

import game.model.Player;
import game.util.Constants;
import game.util.Methods;

public class Game {
  private final Player player;
  private boolean isRunning;

  Game(Player player) {
    this.player = player;
    this.isRunning = false;
  }

  public void start() {
    this.isRunning = true;
    Methods.clearScreen();
    System.out.printf("\nWelcome to talisman %s", player.getName());

    while (isRunning) {
      playTurn();
    }
  }

  private void playTurn() {
    showGameMenu();
    switch (Methods.readNumber(1, 4)) {
      case 1 -> movePlayer();
      case 2 -> showInventory();
      case 3 -> showCharacterSheet();
      default -> quitGame();
    }
  }

  private void showGameMenu() {
    Methods.clearScreen();
    System.out.println("\n--- CURRENT STATUS ---");
    System.out.println("Position: " + (player.getPosition() + 1) + " / " + (Constants.BOARD_SIZE));
    System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
    System.out.println("----------------------");
    System.out.println("\n1) Roll dice\n2) Open inventory\n3) Open stats screen\n4) Save and quit");
    System.out.print("\nYour choice: ");
  }

  private void movePlayer() {
    try {
      System.out.println("Rolling dice...");
      Thread.sleep(600);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    int dice = (int) (Math.random() * 6) + 1;
    System.out.println("\nResult: " + dice);

    int newPos = (player.getPosition() + dice) % Constants.BOARD_SIZE;
    player.setPosition(newPos);

    System.out.println("You landed on space: " + (newPos + 1));
    Methods.pressEnterToContinue();
  }

  private void showInventory() {
    Methods.clearScreen();

    System.out.println("== INVENTORY ==");

    if (player.getInventory().isEmpty()) {
      System.out.println("\n-- Empty --\n");
    } else {
      player.getInventory().forEach(System.out::println);
    }
    Methods.pressEnterToContinue();
  }

  private void showCharacterSheet() {
    Methods.clearScreen();
    System.out.println("=========== STATS ==========");

    System.out.println(player.toString());
    Methods.pressEnterToContinue();
  }

  private void quitGame() {
    Methods.clearScreen();
    System.out.println("Saving...");
    Methods.pressEnterToContinue();
    isRunning = false;
  }
}