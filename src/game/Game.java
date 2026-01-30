package game;

import game.model.Player;
import game.model.board.Board;
import game.model.board.spaces.Space;
import game.service.factories.Deck;
import game.service.ConsoleLogger;
import game.service.GameLogger;
import game.util.Constants;
import game.util.Methods;

public class Game {
  private final Player player;
  private final Board board;
  private final Deck deck;
  private final GameLogger logger;
  private boolean isRunning;

  Game(Player player) {
    this.player = player;
    this.logger = new ConsoleLogger();
    this.deck = new Deck(logger);
    this.board = new Board(deck, logger);
    this.isRunning = false;
  }

  public void start() {
    this.isRunning = true;

    while (isRunning && player.isAlive()) {
      playTurn();
    }

    if (player.isAlive() == false) {
      hadleGameOver();
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
    Space currentSpace = board.getSpace(player.getPosition());

    System.out.println("\n" + "=".repeat(50));
    System.out.printf(" POSITION: %d | SPACE: %s\n", player.getPosition() + 1, currentSpace.getName());
    System.out.printf(" HP: %d/%d | COINS: %d | XP: %d\n",
        player.getHp(), player.getMaxHp(), player.getCoins(), player.getXp());
    System.out.println("=".repeat(50));
    System.out.println("1) Roll Dice & Move");
    System.out.println("2) Inventory");
    System.out.println("3) Character Stats");
    System.out.println("4) Quit Game");
    System.out.print("\nYour choice: ");
  }

  private void movePlayer() {
    try {
      System.out.print("Rolling dice");
      for (int i = 0; i < 3; i++) {
        Thread.sleep(200);
        System.out.print(".");
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    int dice = (int) (Math.random() * 6) + 1;
    System.out.println("\nResult: [" + dice + "]");

    int newPos = (player.getPosition() + dice) % Constants.BOARD_SIZE;
    player.setPosition(newPos);

    Space currentSpace = board.getSpace(newPos);

    System.out.println("\n>>> Landed on: " + currentSpace.getName() + " <<<");
    System.out.println(currentSpace.getDescription());
    System.out.println("-".repeat(50));

    currentSpace.onLand(player);
    System.out.println();

    Methods.pressEnterToContinue();
  }

  private void showInventory() {
    Methods.clearScreen();

    System.out.println("========= INVENTORY =========");

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

  private void hadleGameOver() {
    Methods.clearScreen();
    System.out.println("""
        ####################################
                  G A M E   O V E R
        ####################################
        """);
    System.out.println("L'avventura di " + player.getName() + " termina qui.");
    System.out.println("Monete raccolte: " + player.getCoins());
    System.out.println("Esperienza totale: " + player.getXp());
    System.out.println("\nGrazie per aver giocato!");
    Methods.pressEnterToContinue();
  }
}