package game.service.managers;

import java.util.Random;
import game.model.Combatant;
import game.model.Enemy;
import game.model.Player;
import game.model.StatusEffect;
import game.service.loggers.GameLogger;
import game.util.Methods;

public class CombatManager {

	private static final Random random = new Random();
	private final GameLogger logger;

	public CombatManager(GameLogger logger) {
		this.logger = logger;
	}

	public void startBattle(Player player, Enemy enemy) {
		logger.log("!!! COMBAT START !!!");
		logger.log(String.format("%s challenges %s!\n", player.getName(), enemy.getName()));
		Methods.pressEnterToContinue();

		Combatant attacker = player;
		Combatant defender = enemy;

		while (player.isAlive() && enemy.isAlive()) {
			executeTurn(attacker, defender);

			if (!defender.isAlive()) {
				break;
			}

			Combatant temp = attacker;
			attacker = defender;
			defender = temp;
		}

		handleBattleEnd(player, enemy);
	}

	private void executeTurn(Combatant attacker, Combatant defender) {
		if (attacker instanceof Player) {
			applyStatusEffects((Player) attacker);
			if (!attacker.isAlive())
				return;
		}

		logger.log(String.format("--- %s's Turn ---", attacker.getName()));

		int diceRoll = random.nextInt(6) + 1;
		int totalAttack = attacker.getAttack() + diceRoll;
		int damage = Math.max(0, totalAttack - defender.getDefense());

		logger.log(String.format("%s attacks with %d (Attack) + %d (Dice) = %d",
				attacker.getName(), attacker.getAttack(), diceRoll, totalAttack));

		if (damage > 0) {
			defender.takeDamage(damage);
			logger
					.log(String.format("%s defends %d and takes %d damage!", defender.getName(), defender.getDefense(), damage));
		} else {
			logger.log(String.format("%s's defense holds! No damage taken.", defender.getName()));
		}

		logger.log(String.format("Remaining HP -> %s: %d | %s: %d\n",
				attacker.getName(), attacker.getHp(),
				defender.getName(), defender.getHp()));

		Methods.pressEnterToContinue();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		Methods.clearScreen();
	}

	private void handleBattleEnd(Player player, Enemy enemy) {
		if (player.isAlive()) {
			logger.log(String.format("VICTORY! %s has been defeated.", enemy.getName()));
			logger.log(String.format("You gained %d XP and %d Coins.", enemy.getXpReward(), enemy.getCoinsReward()));
			player.setXp(player.getXp() + enemy.getXpReward());
			player.setCoins(player.getCoins() + enemy.getCoinsReward());
		} else {
			logger.log(String.format("DEFEAT... %s has defeated you.", enemy.getName()));
		}
	}

	private void applyStatusEffects(Player player) {
		if (player.getStatusEffects().contains(StatusEffect.POISONED)) {
			logger.log("Poison deals 1 damage!");
			player.takeDamage(1);
		}
		// Altri effetti possono essere gestiti qui
	}
}