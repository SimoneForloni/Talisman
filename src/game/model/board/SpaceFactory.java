package game.model.board;

import java.util.Random;

import game.model.Player;
import game.model.board.spaces.CombatSpace;
import game.model.board.spaces.Space;
import game.model.board.spaces.TreasureSpace;
import game.model.board.spaces.VillageSpace;

public class SpaceFactory {
  private static final Random random = new Random();

  public static Space createRandomSpace(int id) {
    int roll = random.nextInt(100);

    if (roll < 40) {
      return new Space("Plain", "A quiet and lonely walk") {
        @Override
        public void onLand(Player player) {
          System.out.println("Nothing happens, you take a breath of fresh air.");
        }
      };

    } else if (roll < 75) {
      return new CombatSpace("Wilderness" + id, "You hear growls from the bushes...");
    } else if (roll < 90) {
      return new TreasureSpace();
    } else {
      return new VillageSpace();
    }
  }
}
