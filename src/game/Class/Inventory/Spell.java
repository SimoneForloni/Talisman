package game.Class.Inventory;

// @SuppressWarnings("unused")
public class Spell {
  private String name;
  private String description;
  private String type; // Es: "Attacco", "Difesa", "Utilità"
  private int bonusValue;

  // ===========================================================
  // COSTRUTTORI
  // ===========================================================

  public Spell(String name, String description, String type, int bonusValue) {
    this.name = name;
    this.description = description;
    this.type = type;
    this.bonusValue = bonusValue;
  }

  // ===========================================================
  // GETTER
  // ===========================================================

  public String getName() { return name; }
  public String getDescription() { return description; }
  public String getType() { return type; }
  public int getPower() { return bonusValue; }

  // ===========================================================  
  // SETTER
  // ===========================================================

  public void setName(String name) { this.name = name; }
  public void setDescription(String description) { this.description = description; }
  public void setType(String type) { this.type = type; }
  public void setPower(int bonusValue) { this.bonusValue = bonusValue; }
}