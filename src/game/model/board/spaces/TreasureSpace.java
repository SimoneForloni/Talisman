package game.model.board.spaces;

import game.model.Player;
import game.service.loggers.GameLogger;

public class TreasureSpace extends Space {
  private final GameLogger logger;

  public TreasureSpace(GameLogger logger) {
    super("Treasure Hoard", "Gold glitters in the shadows.");
    this.logger = logger;
  }

  @Override
  public void onLand(Player player) {
    int gold = (int) (Math.random() * 20) + 10;
    player.setCoins(player.getCoins() + gold);
    logger.log("You found " + gold + " coins!");
  }
}