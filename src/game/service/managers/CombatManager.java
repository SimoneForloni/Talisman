package game.service.managers;

import game.model.Enemy;
import game.model.Player;
import game.util.Methods;

public class CombatManager {

  public static void startCombat(Player player, Enemy enemy) {
    System.out.println("!!! Encounter: " + enemy.getName() + " attacks !!!");

    Methods.pressEnterToContinue();

    while (player.isAlive() && enemy.isAlive()) {
      Methods.clearScreen();

      System.out
          .println("--- Player HP: " + player.getHp() + " | " + enemy.getName() + " HP: " + enemy.getHp() + " ---");

      // 1. Player attack
      int pDice = (int) (Math.random() * 6) + 1;
      int damageToEnemy = Math.max(0, (player.getStrength() + pDice) - enemy.getDefense());
      enemy.setHp(enemy.getHp() - damageToEnemy);
      System.out.println("> You roll " + pDice + " and deal " + damageToEnemy + " damage!");

      Methods.pressEnterToContinue();

      if (enemy.getHp() <= 0)
        break;

      Methods.clearScreen();

      System.out
          .println("--- Player HP: " + player.getHp() + " | " + enemy.getName() + " HP: " + enemy.getHp() + " ---");

      // 2. Enemy attack
      int eDice = (int) (Math.random() * 6) + 1;
      int damageToPlayer = Math.max(0, (enemy.getAttack() + eDice) - player.getDefense());
      player.setHp(player.getHp() - damageToPlayer);
      System.out.println("> " + enemy.getName() + " rolls " + eDice + " and deals " + damageToPlayer + " damage!");

      Methods.pressEnterToContinue();
    }

    if (player.isAlive()) {
      System.out.println("\nVICTORY! You defeated " + enemy.getName());
      System.out.println("Rewards: " + enemy.getXpReward() + " XP and " + enemy.getCoinsReward() + " Coins.");
      player.setXp(player.getXp() + enemy.getXpReward());
      player.setCoins(player.getCoins() + enemy.getCoinsReward());
    }
  }
}
