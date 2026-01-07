package game.model;

import java.util.ArrayList;
import game.model.inventory.*;

public class Player {

	private int maxHp;
	private int coins;
	private int position;

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

	private ArrayList<InventoryObject> inventory;
	private ArrayList<InventoryObject> spells;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================

	public Player(String name) {
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
		this.position = 0;
		this.inventory = new ArrayList<>();
		this.spells = new ArrayList<>();
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
			ArrayList<InventoryObject> spells) {
		this.name = name;
		this.maxHp = maxHp;
		this.hp = hp;
		this.strength = strength;
		this.defense = defense;
		this.intelligence = intelligence;
		this.carisma = carisma;
		this.agility = agility;
		this.luck = luck;
		this.xp = xp;
		this.xpPoint = xpPoint;
		this.coins = coins;
		this.position = 0;
		this.inventory = inventory;
		this.spells = spells;
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
		return xpPoint;
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

	public int getCarisma() {
		return carisma;
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

	public ArrayList<InventoryObject> getInventory() {
		return inventory;
	}

	public ArrayList<InventoryObject> getSpells() {
		return spells;
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

	public void setXpPoint(int xpPoint) {
		this.xpPoint = xpPoint;
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

	public void setCarisma(int carisma) {
		this.carisma = carisma;
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

	/**
	 * Cura il giocatore di una certa quantità, fino al massimo HP
	 */
	public int heal(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Heal amount cannot be negative");
		this.hp = Math.min(this.hp + amount, maxHp);
		return this.hp;
	}

	/**
	 * Il giocatore subisce danno
	 */
	public int takeDamage(int amount) {
		this.hp -= amount;
		if (this.hp < 0)
			this.hp = 0;
		return this.hp;
	}

	/**
	 * Controlla se il giocatore è vivo
	 */
	public boolean isAlive() {
		return this.hp > 0;
	}

	/**
	 * Aggiunge esperienza e controlla se si sale di livello
	 */
	public void addExperience(int amount) {
		this.xp += amount;

		// Ogni 100 XP = 1 punto abilità
		int newPoints = this.xp / 100;
		if (newPoints > this.xpPoint) {
			this.xpPoint = newPoints;
			System.out.println("Livello aumentato! Hai " + this.xpPoint + " punti abilità da spendere!");
		}
	}

	/**
	 * Spende un punto abilità per aumentare una statistica
	 */
	public boolean spendSkillPoint(String stat) {
		if (this.xpPoint <= 0) {
			return false;
		}

		switch (stat.toLowerCase()) {
			case "strength":
				this.strength++;
				break;
			case "defense":
				this.defense++;
				break;
			case "intelligence":
				this.intelligence++;
				break;
			case "carisma":
				this.carisma++;
				break;
			case "agility":
				this.agility++;
				break;
			case "luck":
				this.luck++;
				break;
			default:
				return false;
		}

		this.xpPoint--;
		return true;
	}
}