package game.model.inventory;

// @SuppressWarnings("unused")
public class InventoryObject {
  private String name;
  private String description;
  private String type; // Es: "Arma", "Armatura", "Oggetto"
  private int bonusValue;

  // ===========================================================
  // COSTRUTTORI
  // ===========================================================

  public InventoryObject(String name, String description, String type, int bonusValue) {
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
  public int getBonusValue() { return bonusValue; }

  // ===========================================================  
  // SETTER
  // ===========================================================

  public void setName(String name) { this.name = name; }
  public void setDescription(String description) { this.description = description; }
  public void setType(String type) { this.type = type; }
  public void setBonusValue(int bonusValue) { this.bonusValue = bonusValue; }
}