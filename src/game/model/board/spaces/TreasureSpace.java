package game.model.board.spaces;

import game.model.Player;

public class TreasureSpace extends Space {
  public TreasureSpace() {
    super("Treasure Hoard", "Gold glitters in the shadows.");
  }

  @Override
  public void onLand(Player player) {
    int gold = (int) (Math.random() * 20) + 10;
    player.setCoins(player.getCoins() + gold);
    System.out.println("You found " + gold + " coins!");
  }
}