package game.model;

import java.util.ArrayList;
import java.util.List;
import game.model.inventory.*;
import game.util.CharacterClass;;

public class Player implements Combatant {
	// Statistiche di stato
	private String name;
	private CharacterClass characterClass;
	private int hp;
	private int maxHp;
	private int xp;
	private int upgradePoints;
	private int coins;
	private int position;

	// Statistiche di combattimento/interazione
	private int strength;
	private int defense;
	private int intelligence;
	private int charisma;
	private int agility;
	private int luck;

	private List<InventoryObject> inventory;
	private List<InventoryObject> spells;
	private List<StatusEffect> statusEffects;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================

	public Player() {
		this.inventory = new ArrayList<>();
		this.spells = new ArrayList<>();
	}

	public Player(String name, CharacterClass charClass) {
		this(name, charClass, 100);
	}

	public Player(String name, CharacterClass charClass, int initialMaxHp) {
		this.name = name;
		this.characterClass = charClass;
		this.maxHp = initialMaxHp;
		this.hp = initialMaxHp;
		this.xp = 0;
		this.upgradePoints = 0;
		this.coins = 0;
		this.position = 0;
		this.inventory = new ArrayList<>();
		this.spells = new ArrayList<>();
		this.statusEffects = new ArrayList<>();

		// Copia le statistiche dalla Enum
		this.strength = charClass.strength;
		this.defense = charClass.defense;
		this.intelligence = charClass.intelligence;
		this.charisma = charClass.charisma;
		this.agility = charClass.agility;
		this.luck = charClass.luck;
	}

	// ===========================================================
	// GETTER
	// ===========================================================

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getXp() {
		return xp;
	}

	public int getXpPoint() {
		return upgradePoints;
	}

	public int getStrength() {
		return strength;
	}

	public int getDefense() {
		return defense;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getCharisma() {
		return charisma;
	}

	// Metodo per l'interfaccia Combatant, usa la forza come attacco base
	@Override
	public int getAttack() {
		return strength;
	}

	public int getAgility() {
		return agility;
	}

	public int getLuck() {
		return luck;
	}

	public int getCoins() {
		return coins;
	}

	public int getPosition() {
		return position;
	}

	public List<InventoryObject> getInventory() {
		return inventory;
	}

	public List<InventoryObject> getSpells() {
		return spells;
	}

	public List<StatusEffect> getStatusEffects() {
		return statusEffects;
	}

	// ===========================================================
	// SETTER
	// ===========================================================

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setUpgradePoints(int upgradePoints) {
		this.upgradePoints = upgradePoints;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// ===========================================================
	// METODI DI LOGICA / AZIONI
	// ===========================================================

	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	public void heal(int healthGain) {
		this.hp = Math.min(maxHp, this.hp + healthGain);
	}

	public void addStatusEffect(StatusEffect effect) {
		if (!statusEffects.contains(effect)) {
			statusEffects.add(effect);
		}
	}

	public void removeStatusEffect(StatusEffect effect) {
		statusEffects.remove(effect);
	}

	@Override
	public void takeDamage(int damage) {
		this.hp = Math.max(0, this.hp - damage);
	}

	public boolean isAlive() {
		if (this.hp > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("""
				+--------------------------+
				  NAME: %s (%s)
				+--------------------------+
				  HP: %d/%d | COINS: %d
				  STR: %d  DEF: %d  INT: %d
				  CAR: %d  AGI: %d  LUK: %d
				+--------------------------+
				""", name, characterClass, hp, maxHp, coins,
				strength, defense, intelligence, charisma, agility, luck);
	}

}