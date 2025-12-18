package game.Class;

import game.Class.Inventory.*;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Player {
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

	private ArrayList<Item> inventory;
	private ArrayList<Spell> spells;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================

	public Player(
		String name
	) {
		this.name = name;
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

	public int heal(int amount) {
		if(hp >= 100 || hp < 1) {
			return hp;
		} else {
			return this.hp += amount;
		}
	} 

	// public int takeDamage(int amount)
	// public int calculateTotal() 
	// public boolean isAlive(int hp) 
}