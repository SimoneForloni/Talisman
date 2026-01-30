package game.model.board;

import java.util.ArrayList;
import java.util.List;

import game.model.board.spaces.*;
import game.service.factories.SpaceFactory;
import game.service.GameLogger;
import game.service.factories.Deck;
import game.util.Constants;

public class Board {
  private final List<Space> spaces = new ArrayList<>();

  public Board(Deck deck, GameLogger logger) {
    generateBoard(deck, logger);
  }

  private void generateBoard(Deck deck, GameLogger logger) {
    spaces.add(new VillageSpace());

    for (int i = 1; i < Constants.BOARD_SIZE; i++) {
      spaces.add(SpaceFactory.createRandomSpace(i, deck, logger));
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