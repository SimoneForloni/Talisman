package game.model.inventory;

import game.model.Player;

// @SuppressWarnings("unused")
public class InventoryObject {
  private String name;
  private String description;
  private boolean equip;
  private String type; // Es: "Arma", "Armatura", "Oggetto"
  private int bonusValue;

  // ===========================================================
  // COSTRUTTORI
  // ===========================================================

  public InventoryObject(String name, String description, String type, int bonusValue, boolean equip) {
    this.name = name;
    this.description = description;
    this.equip = equip;
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
  public boolean getEquip() { return equip; }

  // ===========================================================  
  // SETTER
  // ===========================================================

  public void setName(String name) { this.name = name; }
  public void setDescription(String description) { this.description = description; }
  public void setType(String type) { this.type = type; }
  public void setBonusValue(int bonusValue) { this.bonusValue = bonusValue; }
  public void setEquip(boolean equip) { this.equip = equip; }

  // ===========================================================  
  // METODI
  // ===========================================================

  public void use(Player player) {
    
  }
}