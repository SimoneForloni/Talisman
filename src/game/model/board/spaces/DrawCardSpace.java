package game.model.board.spaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Deck;
import game.model.Player;
import game.model.board.cards.AdventureCard;
import game.model.board.cards.CardType;
import game.model.board.cards.EnemyCard;
import game.service.loggers.GameLogger;
import game.service.managers.CombatManager;

public class DrawCardSpace extends Space {

	private static final Random random = new Random();
	private final Deck deck;
	private final GameLogger logger;
	private final CombatManager combatManager;

	public DrawCardSpace(String name, String description, Deck deck, GameLogger logger, CombatManager combatManager) {
		super(name, description);
		this.deck = deck;
		this.logger = logger;
		this.combatManager = combatManager;
	}

	/**
	 * The `onLand` function draws a specified number of Adventure Cards for a
	 * player, separates
	 * enemies from other cards, resolves combat encounters first, and then resolves
	 * the effects of the
	 * remaining cards if the player is still alive.
	 * 
	 * @param player The `player` parameter in the `onLand` method represents the
	 *               player who has landed
	 *               on a specific location in the game. This method is responsible
	 *               for drawing adventure cards for
	 *               the player and resolving any encounters or events based on the
	 *               drawn cards.
	 */
	@Override
	public void onLand(Player player) {
		// Decide internamente quante carte pescare (1 o 2)
		int cardsToDraw = 1 + random.nextInt(3);
		logger.log(String.format("Drawing %d Adventure Card(s)...", cardsToDraw));

		List<AdventureCard> drawnCards = new ArrayList<>();
		for (int i = 0; i < cardsToDraw; i++) {
			drawnCards.add(deck.draw());
		}

		// Separa nemici e altre carte
		List<AdventureCard> enemies = drawnCards.stream()
				.filter(c -> c.getType() == CardType.ENEMY)
				.toList();

		List<AdventureCard> others = drawnCards.stream()
				.filter(c -> c.getType() != CardType.ENEMY)
				.toList();

		// Risolvi prima i combattimenti
		for (AdventureCard card : enemies) {
			if (player.isAlive() && card instanceof EnemyCard) {
				EnemyCard enemyCard = (EnemyCard) card;
				combatManager.startBattle(player, enemyCard.getEnemy());
			}
		}

		// Se vivo, risolvi il resto
		if (player.isAlive()) {
			for (AdventureCard card : others) {
				card.execute(player);
			}
		}
	}
}