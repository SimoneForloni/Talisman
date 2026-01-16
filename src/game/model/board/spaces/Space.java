package game.model.board.spaces;

import game.model.Player;

public abstract class Space {
  private final String name;
  private final String description;

  public Space(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public abstract void onLand(Player player);

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}