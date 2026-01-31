# Project Context: Talisman RPG (Java)

## Overview

Gioco di ruolo ispirato a Talisman sviluppato in Java.
Il progetto utilizza **JavaFX** per l'interfaccia grafica (GUI), mantenendo la logica di gioco separata tramite pattern MVC e Service.

## 🏗️ Architettura & Responsabilità

- **Entry Point**: `Main.java` avvia l'applicazione JavaFX e carica `game.fxml`.
- **Controller**: `GameController.java` gestisce l'interazione UI, il binding dei dati e collega la View al `Game`.
- **Engine**: `Game.java` gestisce il loop di gioco, il movimento e possiede le istanze di `Player` e `Board`.
- **Logic (Services)**: Le classi in `service.managers` sono puramente logiche e non mantengono stati permanenti. Ricevono `Player` ed `Enemy` come argomenti.
- **Logging**: `GameLogger` (interfaccia) astrae l'output. `GuiLogger` scrive sulla TextArea della GUI.
- **Data (Models)**: Classi POJO con getter/setter. `Space.java` usa il pattern Command (metodo `onLand(Player)`).

## 🛠️ Regole Tecniche & Stile

- **Ambiente**: Java 25, JavaFX.
- **Naming**: Classi PascalCase, variabili/metodi camelCase, costanti UPPER_SNAKE_CASE.
- **Dependency Rule**: Le classi in `model.board.spaces` non devono creare istanze di `Enemy`, devono usare `EnemyFactory`.
- **Input/Output**:
  - L'output testuale deve passare tramite `logger.log()` (iniettato nel `Game`).
  - L'input utente è gestito ad eventi (bottoni) nel `GameController`, non tramite scanner console.
  - Usa JavaFX Properties (Binding) per aggiornare HP, Monete e UI in tempo reale.

## ⚔️ Regole di Game Design (AI Guidance)

- **Combattimento**:
  - Turni alternati: Giocatore -> Nemico.
  - Formula Danno: `max(0, (Attacco + Random(1-6)) - Difesa)`.
  - Morte: Se `hp <= 0`, l'entità è sconfitta. Il giocatore deve morire permanentemente (Game Over).
- **Mappa**:
  - Array circolare di dimensione `Constants.BOARD_SIZE`.
  - Movimento tramite operatore Modulo `%`.
- **Carte (Deck)**:
  - `DrawCardSpace` pesca 1-3 carte.
  - Priorità: I nemici vengono combattuti subito; se il player sopravvive, si risolvono gli altri eventi/oggetti.

## 📜 Prompt Instructions for AI

1. **Context First**: Prima di scrivere codice, verifica se esistono già metodi utility in `Methods.java` o costanti in `Constants.java`.
2. **Polymorphism**: Se aggiungi un nuovo tipo di casella, estendi `Space.java` e implementa `onLand`.
3. **Clean Code**: Commenti in italiano, metodi brevi, una sola responsabilità per classe.
4. **Safety**: Verifica sempre che il Player sia vivo (`isAlive()`) prima di iniziare un evento o aggiornare la UI.
5. **UI Awareness**: Se modifichi il modello (`Player`), assicurati che le Property JavaFX siano aggiornate per riflettere il cambiamento nella GUI.

## 📂 Project Structure (Source: /src)

```text
src/
└── game/
    ├── Deck.java, Game.java, GameManager.java, Main.java
    ├── controllers/
    │   └── GameController.java
    ├── model/
    │   ├── Combatant.java (Interface), StatusEffect.java
    │   ├── Player.java, Enemy.java
    │   ├── board/
    │   │   ├── Board.java
    │   │   ├── cards/ (AdventureCard, BossCard, CardType, EnemyCard, EventCard, ItemCard)
    │   │   └── spaces/ (Space, DrawCardSpace, SafeSpace, TreasureSpace, VillageSpace)
    │   └── inventory/
    │       └── InventoryObject.java
    ├── service/
    │   ├── factories/ (EnemyFactory, SpaceFactory)
    │   ├── loggers/ (ConsoleLogger, GameLogger, GuiLogger)
    │   ├── managers/ (CombatManager)
    │   └── util/ (CharacterClass, Constants, Methods)
    ├── util/ (CharacterClass, Constants, Methods)
    └── view/
        └── game.fxml
```
