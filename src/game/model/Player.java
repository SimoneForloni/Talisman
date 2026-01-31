package game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import game.model.inventory.*;
import game.util.CharacterClass;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player implements Combatant {
	// Statistiche di stato
	private String name;
	private CharacterClass characterClass;
	private IntegerProperty hp = new SimpleIntegerProperty();
	private IntegerProperty maxHp = new SimpleIntegerProperty();
	private IntegerProperty xp = new SimpleIntegerProperty();
	private IntegerProperty upgradePoints = new SimpleIntegerProperty();
	private IntegerProperty coins = new SimpleIntegerProperty();
	private IntegerProperty position = new SimpleIntegerProperty();

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
		this.maxHp.set(initialMaxHp);
		this.hp.set(initialMaxHp);
		this.xp.set(0);
		this.upgradePoints.set(0);
		this.coins.set(0);
		this.position.set(0);
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
		return hp.get();
	}

	public int getMaxHp() {
		return maxHp.get();
	}

	public int getXp() {
		return xp.get();
	}

	public int getXpPoint() {
		return upgradePoints.get();
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
		return coins.get();
	}

	public int getPosition() {
		return position.get();
	}

	public List<InventoryObject> getInventory() {
		return Collections.unmodifiableList(inventory);
	}

	public List<InventoryObject> getSpells() {
		return Collections.unmodifiableList(spells);
	}

	public List<StatusEffect> getStatusEffects() {
		return Collections.unmodifiableList(statusEffects);
	}

	// ===========================================================
	// PROPERTY ACCESSORS (JavaFX)
	// ===========================================================

	public IntegerProperty hpProperty() {
		return hp;
	}

	public IntegerProperty maxHpProperty() {
		return maxHp;
	}

	public IntegerProperty coinsProperty() {
		return coins;
	}

	public IntegerProperty xpProperty() {
		return xp;
	}

	public IntegerProperty positionProperty() {
		return position;
	}

	// ===========================================================
	// SETTER
	// ===========================================================

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp.set(hp);
	}

	public void setMaxHp(int maxHp) {
		this.maxHp.set(maxHp);
	}

	public void setXp(int xp) {
		this.xp.set(xp);
	}

	public void setUpgradePoints(int upgradePoints) {
		this.upgradePoints.set(upgradePoints);
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
		this.coins.set(coins);
	}

	public void setPosition(int position) {
		this.position.set(position);
	}

	// ===========================================================
	// METODI DI LOGICA / AZIONI
	// ===========================================================

	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	public void heal(int healthGain) {
		setHp(Math.min(getMaxHp(), getHp() + healthGain));
	}

	public void addItem(InventoryObject item) {
		inventory.add(item);
	}

	public void removeItem(InventoryObject item) {
		inventory.remove(item);
	}

	public void addSpell(InventoryObject spell) {
		spells.add(spell);
	}

	public void removeSpell(InventoryObject spell) {
		spells.remove(spell);
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
		setHp(Math.max(0, getHp() - damage));
	}

	public boolean isAlive() {
		if (getHp() > 0) {
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
				getHp(), getMaxHp(), getCoins(),
				strength, defense, intelligence, charisma, agility, luck);
	}

}