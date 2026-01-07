package game.model;

public class Enemy {
	private String name;
	private int hp;
	private int attack;
	private int defense;
	private int xpReward;
	private int coinsReward;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================
	
	public Enemy(String name, int maxHp, int attack, int defense, int xpReward, int coinsReward) {
		this.name = name;
		this.hp = maxHp; // Il nemico spawna con la vita al massimo
		this.attack = attack;
		this.defense = defense;
		this.xpReward = xpReward;
		this.coinsReward = coinsReward;
	}

	// ===========================================================
	// GETTER
	// ===========================================================
	
	public String getName() { return name; }
	public int getHp() { return hp; }
	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public int getXpReward() { return xpReward; }
	public int getCoinsReward() { return coinsReward; }

	// ===========================================================  
	// SETTER
	// ===========================================================
	
	public void setHp(int hp) { this.hp = hp; }
	public void setName(String name) { this.name = name; }
	public void setAttack(int attack) { this.attack = attack; }
	public void setDefense(int defense) { this.defense = defense; }
	public void setXpReward(int xpReward) { this.xpReward = xpReward; }
	public void setCoinsReward(int coinsReward) { this.coinsReward = coinsReward; }

	// ===========================================================
	// METODI DI LOGICA / AZIONI
	// ===========================================================

	public boolean isAlive() {
		return this.hp > 0;
	}

	public void takeDamage(int damage) {
		int effectiveDamage = Math.max(0, damage - this.defense);
		this.hp -= effectiveDamage;
		if (this.hp < 0) this.hp = 0;
	}
}