package game;

import game.model.Player;
import game.util.CharacterClass;
import game.util.Constants;
import game.util.Methods;

public class GameManager {

  public static void main(String[] args) {
    boolean running = true;

    while (running) {
      int choice = askMenuChoice();

      switch (choice) {
        case 1 -> {
          createAndStartGame();
        }
        case 2 -> System.out.println("\n-> Funzionalità Carica non ancora implementata.\n");
        case 3 -> System.out.println("\n-> Impostazioni non ancora implementate.\n");
        case 4 -> {
          System.out.println("\nArrivederci!");
          running = false;
        }
      }

      if (running && choice != 1) {
        Methods.pressEnterToContinue();
      }
    }

    Constants.scanner.close();
  }

  private static int askMenuChoice() {
    Methods.clearScreen();
    showMenu();

    return Methods.readNumber(1, 4);
  }

  private static void showMenu() {
    System.out.println("""
        ╔════════════════════════════╗
        ║          TALISMAN          ║
        ╚════════════════════════════╝

              1) New game
              2) Load game
              3) Setting
              4) Quit

        Select a option (1-4):
        """);
  }

  private static Player carachterCreator() {
    while (true) {
      Methods.clearScreen();
      System.out.println("\n=== CHARACTER CREATION ===\n");

      String name = askPlayerName();
      if (name == null)
        return null;

      System.out.println("\nChoose your class:");
      System.out.println("1) Warrior\n2) Wizard\n3) Thief");

      int choice = Methods.readNumber(1, 3);

      CharacterClass selectedClass = switch (choice) {
        case 1 -> CharacterClass.WARRIOR;
        case 2 -> CharacterClass.WIZARD;
        default -> CharacterClass.THIEF;
      };

      Player p = new Player(name, selectedClass);
      System.out.println("\nCharacter summary:\n" + p);

      System.out.print("Confirm creation? (y/n): ");
      String confirm = Constants.scanner.nextLine().trim().toLowerCase();

      if (confirm.startsWith("y") || confirm.startsWith("s")) {
        return p;
      }

      System.out.println("\nCreation cancelled. Let's start over...");
    }
  }

  private static void createAndStartGame() {
    Player player = carachterCreator();
    if (player != null) {
      new Game(player).start();
    }
  }

  private static String askPlayerName() {
    while (true) {
      System.out.print("Choose your name (or 'q' to cancel): ");
      String name = Constants.scanner.nextLine().trim();

      if (name.equalsIgnoreCase("q"))
        return null;
      if (name.length() >= 2 && name.length() <= 20)
        return name;

      System.out.println("Invalid name (must be between 2 and 20 characters).\n");
    }
  }
}