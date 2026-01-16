package game.model.board.spaces;

import game.model.Player;

public class CombatSpace extends Space {
  public CombatSpace(String enemyName, String enemyDescription) {
    super(enemyName, enemyDescription);
  }

  @Override
  public void onLand(Player player) {
    System.out.println("Danger! An enemy emergeres from the mist...");
    // TODO: implement enemy spawn logic
  }
}