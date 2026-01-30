package game.service.factories;

import java.util.ArrayList;
import java.util.Random;

import game.service.GameLogger;
import game.model.Enemy;
import game.model.StatusEffect;
import game.model.board.cards.AdventureCard;
import game.model.board.cards.BossCard;
import game.model.board.cards.EnemyCard;
import game.model.board.cards.EventCard;
import game.model.board.cards.ItemCard;
import game.util.Constants;

public class Deck {
	private static final Random random = new Random();
	private ArrayList<AdventureCard> deck;
	private final GameLogger logger;
	private int index;

	public Deck(GameLogger logger) {
		this.logger = logger;
		this.deck = new ArrayList<>();
		for (int i = 0; i < Constants.DECK_LENGTH; i++) {
			deck.add(createRandomCard());
		}
		index = 0;
	}

	public AdventureCard draw() {
		if (index >= deck.size()) {
			index = 0; // Reshuffle semplice: ricomincia da capo
			// Qui potresti aggiungere Collections.shuffle(deck);
		}
		return deck.get(index++);
	}

	private AdventureCard createRandomCard() {
		int roll = random.nextInt(100);

		if (roll < 25) {
			// 25% Enemy
			return new EnemyCard(EnemyFactory.createRandomEnemy());
		} else if (roll < 65) {
			// 40% Item
			// Nota: InventoryObject richiederebbe un costruttore reale. Qui simulo.
			return new ItemCard("Potion", null);
		} else if (roll < 95) {
			// 30% Event
			return createRandomEvent();
		} else {
			// 5% Boss
			return new BossCard(new Enemy("Dragon", 100, 15, 10, 100, 100));
		}
	}

	private EventCard createRandomEvent() {
		int roll = random.nextInt(3);
		switch (roll) {
			case 0:
				return new EventCard("Poison Trap", p -> {
					logger.log("You stepped on a trap!");
					p.addStatusEffect(StatusEffect.POISONED);
				});
			case 1:
				return new EventCard("Found Gold", p -> {
					logger.log("You found a hidden stash!");
					p.setCoins(p.getCoins() + 5);
				});
			default:
				return new EventCard("Teleport", p -> {
					logger.log("A magical wind blows you away!");
					p.setPosition(random.nextInt(Constants.BOARD_SIZE));
				});
		}
	}
}