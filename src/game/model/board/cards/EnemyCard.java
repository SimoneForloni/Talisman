package game.model.board.cards;

import game.model.Enemy;
import game.model.Player;

public class EnemyCard implements AdventureCard {
    private final Enemy enemy;

    public EnemyCard(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void execute(Player player) {
        // La logica è gestita dal CombatManager, questo metodo rimane vuoto o di log
    }

    @Override
    public String getName() {
        return enemy.getName();
    }

    @Override
    public CardType getType() {
        return CardType.ENEMY;
    }
}
