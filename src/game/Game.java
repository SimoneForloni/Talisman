package game;

import game.model.Player;
import game.model.board.Board;
import game.model.board.spaces.Space;
import game.service.loggers.ConsoleLogger;
import game.service.loggers.GameLogger;
import game.service.managers.CombatManager;

public class Game {
  private final Player player;
  private final Board board;
  private final Deck deck;
  private final GameLogger logger;
  private final CombatManager combatManager;

  public Game(Player player) {
    this.player = player;
    this.logger = new ConsoleLogger();
    this.combatManager = new CombatManager(logger);
    this.deck = new Deck(logger);
    this.board = new Board(deck, logger, combatManager);
  }

  // Costruttore pubblico per la GUI
  public Game(Player player, GameLogger logger) {
    this.player = player;
    this.logger = logger;
    this.combatManager = new CombatManager(logger);
    this.deck = new Deck(logger);
    this.board = new Board(deck, logger, combatManager);
  }

  public void start() {
    logger.log("Game Started! Ready to play.");
    logPlayerStatus(); // Mostra lo stato iniziale nel log
  }

  public void logPlayerStatus() {
    Space currentSpace = board.getSpace(player);

    logger.log("\n" + "=".repeat(50));
    logger.log(String.format(" POSITION: %d | SPACE: %s", player.getPosition() + 1, currentSpace.getName()));
    logger.log(String.format(" HP: %d/%d | COINS: %d | XP: %d",
        player.getHp(), player.getMaxHp(), player.getCoins(), player.getXp()));
    logger.log("=".repeat(50));
  }

  public void movePlayer() { // Deve essere public per essere visto dal Controller
    if (!player.isAlive())
      return;

    logger.log("Rolling dice...");

    int dice = (int) (Math.random() * 6) + 1;
    logger.log("Result: [" + dice + "]");

    int newPos = board.calculateNewPosition(player.getPosition(), dice);
    player.setPosition(newPos);

    Space currentSpace = board.getSpace(player);

    logger.log("\n>>> Landed on: " + currentSpace.getName() + " <<<");
    logger.log(currentSpace.getDescription());
    logger.log("-".repeat(50));

    currentSpace.onLand(player);

    // Controllo morte dopo l'evento (es. combattimento o trappola)
    if (!player.isAlive()) {
      handleGameOver();
    }
  }

  public void showInventory() { // Deve essere public
    logger.log("========= INVENTORY =========");

    if (player.getInventory().isEmpty()) {
      logger.log("\n-- Empty --\n");
    } else {
      player.getInventory().forEach(item -> logger.log(item.toString()));
    }
  }

  public void showCharacterSheet() { // Deve essere public
    logger.log("=========== STATS ==========");

    logger.log(player.toString());
  }

  public void quitGame() { // Deve essere public
    logger.log("Saving...");
    // In GUI qui potresti chiudere lo Stage o tornare al menu principale
  }

  private void handleGameOver() {
    logger.log("""
        ####################################
                  G A M E   O V E R
        ####################################
        """);
    logger.log("L'avventura di " + player.getName() + " termina qui.");
    logger.log("Monete raccolte: " + player.getCoins());
    logger.log("Esperienza totale: " + player.getXp());
    logger.log("\nGrazie per aver giocato!");
  }
}