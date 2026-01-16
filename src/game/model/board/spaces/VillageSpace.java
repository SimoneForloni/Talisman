package game.model.board.spaces;

import game.model.Player;

public class VillageSpace extends Space {
  public VillageSpace() {
    super("Village", "A village, you can rest and buy from merchants");
  }

  @Override
  public void onLand(Player player) {
    
  }
}