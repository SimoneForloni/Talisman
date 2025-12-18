package game.model;

import java.util.ArrayList;

import game.model.Inventory.*;

@SuppressWarnings("unused")
public class Player {

	private int maxHp;

	private String name;
	private int hp;
	private int xp;
	private int xpPoint;
	private int strength;
	private int defense;
	private int intelligence;
	private int carisma;
	private int agility;
	private int luck;
	private int coins;

	private ArrayList<InventoryObject> inventory;
	private ArrayList<InventoryObject> spells;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================
	
	public Player(
		String name
	) {
		this.name = name;
		this.maxHp = 100;
		this.hp = 100;
		this.strength = 1;
		this.defense = 1;
		this.intelligence = 1;
		this.carisma = 1;
		this.agility = 1;
		this.luck = 1;
		this.xp = 0;
		this.xpPoint = 0;
		this.coins = 0;
		inventory = new ArrayList<>();
		spells = new ArrayList<>();
	}

	public Player(
		String name,
		int maxHp,
		int hp,
		int strength,
		int defense,
		int intelligence,
		int carisma,
		int agility,
		int luck,
		int xp,
		int xpPoint,
		int coins,

		ArrayList<InventoryObject> inventory,
		ArrayList<InventoryObject> spells
	) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = 100;
		this.strength = 1;
		this.defense = 1;
		this.intelligence = 1;
		this.carisma = 1;
		this.agility = 1;
		this.luck = 1;
		this.xp = 0;
		this.xpPoint = 0;
		this.coins = 0;
		this.inventory = inventory;
		this.spells = spells;
	}

	// ===========================================================
	// GETTER
	// ===========================================================

	public String getName() { return name; }
	public int getHp() { return hp; }
	public int getXp() { return xp; }
	public int getXpPoint() { return xpPoint; }
	public int getStrength() { return strength; }
	public int getDefense() { return defense; }
	public int getIntelligence() { return intelligence; }
	public int getCarisma() { return carisma; }
	public int getAgility() { return agility; }
	public int getLuck() { return luck; }
	public int getCoins() { return coins; }

	// ===========================================================  
	// SETTER
	// ===========================================================

	public void setName(String name) { this.name = name; }
	public void setHp(int hp) { this.hp = hp; }
	public void setXp(int xp) { this.xp = xp; }
	public void setXpPoint(int xpPoint) { this.xpPoint = xpPoint; }
	public void setStrength(int strength) { this.strength = strength; }
	public void setDefense(int defense) { this.defense = defense; }
	public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
	public void setCarisma(int carisma) { this.carisma = carisma; }
	public void setAgility(int agility) { this.agility = agility; }
	public void setLuck(int luck) { this.luck = luck; }
	public void setCoins(int coins) { this.coins = coins; }

	// ===========================================================
	// METODI DI LOGICA / AZIONI
	// ===========================================================

	/**
	 * The `heal` function increases the current health points of an object by a specified amount, up to a
	 * maximum limit, and throws an exception if the amount is negative.
	 * 
	 * @param amount The `amount` parameter in the `heal` method represents the amount of health points
	 * that will be added to the current health points (`hp`) of an entity. If the `amount` is negative,
	 * an `IllegalArgumentException` is thrown with the message "Heal amount cannot be negative". The
	 * @return The method `heal` is returning the updated value of the `hp` attribute after healing by the
	 * specified `amount`.
	 */
	public int heal(int amount) {
    if (amount < 0) throw new IllegalArgumentException("Heal amount cannot be negative");
    this.hp = Math.min(this.hp + amount, maxHp);
    return this.hp;
	}

	/**
	 * The `takeDamage` function reduces the object's health points by a specified amount and ensures it
	 * does not go below zero.
	 * 
	 * @param amount The `amount` parameter in the `takeDamage` method represents the amount of damage
	 * that will be subtracted from the current health points (`hp`) of an object.
	 * @return The method `takeDamage` returns the updated value of the `hp` variable after deducting the
	 * specified `amount` of damage.
	 */
	public int takeDamage(int amount) {
		this.hp -= amount;
		if (this.hp < 0) this.hp = 0;
		return this.hp;
	}

	
	/**
	 * The function "isAlive" returns true if the object's health points (hp) are greater than 0.
	 * 
	 * @return The method isAlive() returns a boolean value indicating whether the object's hp (health
	 * points) is greater than 0. If the hp is greater than 0, it returns true, indicating that the object
	 * is alive.
	 */
	public boolean isAlive() { 
		return this.hp > 0;
	}

}