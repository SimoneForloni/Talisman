package game.model.board;

import java.util.ArrayList;
import java.util.List;

import game.Deck;
import game.model.Player;
import game.model.board.spaces.*;
import game.service.factories.SpaceFactory;
import game.service.loggers.GameLogger;
import game.service.managers.CombatManager;
import game.util.Constants;

public class Board {
  private final List<Space> spaces = new ArrayList<>();

  public Board(Deck deck, GameLogger logger, CombatManager combatManager) {
    generateBoard(deck, logger, combatManager);
  }

  private void generateBoard(Deck deck, GameLogger logger, CombatManager combatManager) {
    spaces.add(new VillageSpace());

    for (int i = 1; i < Constants.BOARD_SIZE; i++) {
      spaces.add(SpaceFactory.createRandomSpace(i, deck, logger, combatManager));
    }
  }

  public Space getSpace(int position) {
    return spaces.get(position);
  }

  public Space getSpace(Player player) {
    return spaces.get(player.getPosition());
  }

  public int calculateNewPosition(int currentPosition, int steps) {
    return (currentPosition + steps) % Constants.BOARD_SIZE;
  }

  public void printMap() {
    System.out.println("\n--- WORLD MAP ---");
    for (int i = 0; i < Constants.BOARD_SIZE; i++) {
      System.out.printf("[%02d] %s\n", i, spaces.get(i).getName());
    }
    System.out.println("-----------------\n");
  }
}