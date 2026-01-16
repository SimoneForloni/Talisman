package game.model.board;

import java.util.ArrayList;
import java.util.List;

import game.model.board.spaces.*;
import game.util.Constants;

public class Board {
  private final List<Space> spaces = new ArrayList<>();

  public Board() {
    generateBoard();
  }

  private void generateBoard() {
    spaces.add(new VillageSpace());

    for (int i = 1; i < Constants.BOARD_SIZE; i++) {
      if (i > 40) {
        spaces.add(new CombatSpace("Abissal Wasteladn" + i, "An ancient evil dwells here."));
      } else {
        spaces.add(SpaceFactory.createRandomSpace(i));
      }
    }
  }

  public Space getSpace(int position) {
    return spaces.get(position);
  }

  public void printMap() {
    System.out.println("\n--- WORLD MAP ---");
    for (int i = 0; i < Constants.BOARD_SIZE; i++) {
      System.out.printf("[%02d] %s\n", i, spaces.get(i).getName());
    }
    System.out.println("-----------------\n");
  }
}