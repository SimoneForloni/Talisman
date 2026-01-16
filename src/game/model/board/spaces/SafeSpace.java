package game.model.board.spaces;

import game.model.Player;

public class SafeSpace extends Space {

  public SafeSpace(String name, String description) {
    super("A safe space", "A safe space where you can heal and recover");
  }

  @Override
  public void onLand(Player player) {
    System.out.println("You gain 10 hp.");
    player.heal(10);
  }
  
}