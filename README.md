# Talisman RPG (Java)

Un gioco di ruolo ispirato al classico gioco da tavolo **Talisman**, sviluppato in Java con interfaccia grafica **JavaFX**.

## 🎮 Panoramica

Il giocatore esplora un tabellone circolare, affrontando mostri, raccogliendo tesori e potenziando il proprio personaggio. L'obiettivo è sopravvivere e accumulare esperienza e ricchezze.

### Caratteristiche Principali

- **Esplorazione**: Movimento su caselle con eventi diversi (Villaggi, Tesori, Pesca Carte).
- **Combattimento**: Sistema a turni contro nemici generati casualmente o Boss.
- **Progressione**: Guadagna XP, monete e oggetti per migliorare Attacco, Difesa e HP.
- **Interfaccia**: GUI reattiva con log degli eventi, statistiche in tempo reale e inventario.

## 🚀 Per Iniziare

### Prerequisiti

- **Java JDK 25** (o versione compatibile).
- **JavaFX SDK** configurato nel classpath.
- **Visual Studio Code** con "Extension Pack for Java".

### Esecuzione

1. Apri la cartella del progetto in VS Code.
2. Esegui la classe `game.Main`.

## 📂 Struttura del Progetto

Il codice sorgente si trova in `src/game` ed è organizzato secondo il pattern MVC/Service:

- **`controllers/`**: Gestione degli eventi UI (`GameController`).
- **`model/`**: Logica dei dati (`Player`, `Enemy`, `Board`, `Card`).
- **`service/`**: Logica di business e utility (`CombatManager`, `Factories`, `Loggers`).
- **`view/`**: File FXML per l'interfaccia grafica.

## 🛠️ Sviluppo

Vedi CONTEXT.md per le linee guida architetturali, le regole di stile e le istruzioni per l'AI Assistant.
