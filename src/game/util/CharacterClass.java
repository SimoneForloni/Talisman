package game.util;

public enum CharacterClass {
  WARRIOR(4, 3, 2, 2, 3, 2),
  WIZARD(2, 3, 4, 2, 2, 3),
  THIEF(3, 2, 2, 3, 4, 2);
  
  public final int strength, defense, intelligence, charisma, agility, luck;

  CharacterClass(int str, int def, int intel, int charis, int agil, int luck) {
    this.strength = str;
    this.defense = def;
    this.intelligence = intel;
    this.charisma = charis;
    this.agility = agil;
    this.luck = luck;
  }
}