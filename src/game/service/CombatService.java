package game.service;

import game.model.Enemy;
import game.model.GameState;
import game.model.Player;

public class CombatService {
  private GameState gameState;

  public CombatService(GameState gameState) {
    this.gameState = gameState;
  }

  /**
   * Inizia un combattimento tra il giocatore e un nemico
   */
  public void startCombat(Player player, Enemy enemy) {
    gameState.startCombat(enemy);
    System.out.println("Combattimento iniziato contro: " + enemy.getName());
  }

  /**
   * Esegue l'attacco del giocatore sul nemico
   */
  public int playerAttack(Player player, Enemy enemy) {
    int damage = calculateDamage(player.getStrength(), enemy.getDefense());
    enemy.takeDamage(damage);
    System.out.println(player.getName() + " attacca " + enemy.getName() +
        " infliggendo " + damage + " danni!");
    return damage;
  }

  /**
   * Esegue l'attacco del nemico sul giocatore
   */
  public int enemyAttack(Player player, Enemy enemy) {
    int damage = calculateDamage(enemy.getAttack(), player.getDefense());
    player.takeDamage(damage);
    System.out.println(enemy.getName() + " attacca " + player.getName() +
        " infliggendo " + damage + " danni!");
    return damage;
  }

  /**
   * Calcola il danno effettivo considerando attacco e difesa
   */
  public int calculateDamage(int attack, int defense) {
    // Formula base: danno = attacco - (difesa / 2)
    // Il danno minimo è sempre 1
    int baseDamage = attack - (defense / 2);
    return Math.max(1, baseDamage);
  }

  /**
   * Termina il combattimento e assegna ricompense se il giocatore vince
   */
  public void endCombat(Player player, Enemy enemy) {
    if (checkVictory(enemy)) {
      // Il giocatore ha vinto
      player.setXp(player.getXp() + enemy.getXpReward());
      player.setCoins(player.getCoins() + enemy.getCoinsReward());
      System.out.println("Vittoria! Guadagnati " + enemy.getXpReward() +
          " XP e " + enemy.getCoinsReward() + " monete!");
    }
    gameState.endCombat();
  }

  /**
   * Verifica se il nemico è stato sconfitto
   */
  public boolean checkVictory(Enemy enemy) {
    return !enemy.isAlive();
  }

  /**
   * Verifica se il giocatore è stato sconfitto
   */
  public boolean checkDefeat(Player player) {
    return !player.isAlive();
  }

  /**
   * Tenta la fuga dal combattimento (basata sulla fortuna del giocatore)
   */
  public boolean attemptFlee(Player player) {
    // Probabilità di fuga basata sulla fortuna (50% + luck * 5%)
    int fleeChance = 50 + (player.getLuck() * 5);
    int roll = (int) (Math.random() * 100) + 1;
    boolean success = roll <= fleeChance;

    if (success) {
      System.out.println(player.getName() + " è fuggito con successo!");
      gameState.endCombat();
    } else {
      System.out.println(player.getName() + " non è riuscito a fuggire!");
    }

    return success;
  }
}