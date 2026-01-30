# Project Context: Talisman RPG (Java)

## Overview
Gioco di ruolo testuale ispirato a Talisman. Architettura basata su console (CLI).
In futuro sara' si implementera' JavaFX

## 🏗️ Architettura & Responsabilità
- **Entry Point**: `Main.java` avvia `GameManager`, che gestisce i menu principali.
- **Engine**: `Game.java` gestisce il loop di gioco, il movimento e possiede le istanze di `Player` e `Board`.
- **Logic (Services)**: Le classi in `service.managers` sono puramente logiche e non mantengono stati permanenti. Ricevono `Player` ed `Enemy` come argomenti.
- **Data (Models)**: Classi POJO con getter/setter. `Space.java` usa il pattern Command (metodo `onLand(Player)`).

## 🛠️ Regole Tecniche & Stile
- **Ambiente**: Java 25, solo console (System.out) per poi implementare in futuro JavaFX.
- **Naming**: Classi PascalCase, variabili/metodi camelCase, costanti UPPER_SNAKE_CASE.
- **Dependency Rule**: Le classi in `model.board.spaces` non devono creare istanze di `Enemy`, devono usare `EnemyFactory`.
- **Input/Output**: Usare esclusivamente `game.util.Methods` per:
  - Pulire lo schermo (`clearScreen`)
  - Leggere numeri (`readNumber`)
  - Mettere in pausa (`pressEnterToContinue`)

## ⚔️ Regole di Game Design (AI Guidance)
- **Combattimento**: 
  - Turni alternati: Giocatore -> Nemico.
  - Formula Danno: `max(0, (Attacco + Random(1-6)) - Difesa)`.
  - Morte: Se `hp <= 0`, l'entità è sconfitta. Il giocatore deve morire permanentemente (Game Over).
- **Mappa**: 
  - Array circolare di dimensione `Constants.BOARD_SIZE`.
  - Movimento tramite operatore Modulo `%`.

## 📜 Prompt Instructions for AI
1. **Context First**: Prima di scrivere codice, verifica se esistono già metodi utility in `Methods.java` o costanti in `Constants.java`.
2. **Polymorphism**: Se aggiungi un nuovo tipo di casella, estendi `Space.java` e implementa `onLand`.
3. **Clean Code**: Commenti in italiano, metodi brevi, una sola responsabilità per classe.
4. **Safety**: Verifica sempre che il Player sia vivo (`isAlive()`) prima di iniziare un evento.

## 📂 Project Structure (Source: /src)
```text
src/
└── game/
    ├── Game.java, GameManager.java, Main.java
    ├── model/
    │   ├── Combatant.java (Interface), StatusEffect.java
    │   ├── Player.java, Enemy.java
    │   ├── board/
    │   │   ├── Board.java
    │   │   ├── cards/ (AdventureCard, BossCard, EnemyCard, EventCard, ItemCard)
    │   │   └── spaces/ (Space, DrawCardSpace, SafeSpace, VillageSpace, etc.)
    │   └── inventory/
    ├── service/
    │   ├── factories/ (AdventureDeck, EnemyFactory, SpaceFactory)
    │   └── managers/ (CombatManager)
    └── util/ (CharacterClass, Constants, Methods)
```

