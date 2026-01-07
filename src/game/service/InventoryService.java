package game.service;

import java.util.ArrayList;
import game.model.Player;
import game.model.inventory.InventoryObject;

public class InventoryService {

  /**
   * Aggiunge un oggetto all'inventario del giocatore
   */
  public boolean addItem(Player player, InventoryObject item) {
    if (item == null) {
      System.err.println("Impossibile aggiungere un oggetto null");
      return false;
    }

    // Qui potresti aggiungere un limite di inventario
    // Per ora l'inventario è illimitato
    player.getInventory().add(item);
    System.out.println("Aggiunto: " + item.getName() + " all'inventario");
    return true;
  }

  /**
   * Rimuove un oggetto dall'inventario per nome
   */
  public boolean removeItem(Player player, String itemName) {
    InventoryObject item = getItemByName(player, itemName);

    if (item == null) {
      System.err.println("Oggetto non trovato: " + itemName);
      return false;
    }

    player.getInventory().remove(item);
    System.out.println("Rimosso: " + itemName + " dall'inventario");
    return true;
  }

  /**
   * Usa un oggetto dall'inventario
   */
  public void useItem(Player player, String itemName) {
    InventoryObject item = getItemByName(player, itemName);

    if (item == null) {
      System.err.println("Oggetto non trovato: " + itemName);
      return;
    }

    // Usa l'oggetto tramite il suo metodo use()
    item.use(player);

    // Se l'oggetto non è equipaggiabile (consumabile), rimuovilo
    if (!item.getEquip()) {
      removeItem(player, itemName);
      System.out.println(itemName + " è stato consumato");
    }
  }

  /**
   * Ottiene l'inventario completo del giocatore
   */
  public ArrayList<InventoryObject> getInventory(Player player) {
    return player.getInventory();
  }

  /**
   * Verifica se il giocatore ha un determinato oggetto
   */
  public boolean hasItem(Player player, String itemName) {
    return getItemByName(player, itemName) != null;
  }

  /**
   * Cerca un oggetto nell'inventario per nome
   */
  public InventoryObject getItemByName(Player player, String name) {
    if (name == null || name.isEmpty()) {
      return null;
    }

    for (InventoryObject item : player.getInventory()) {
      if (item.getName().equalsIgnoreCase(name)) {
        return item;
      }
    }

    return null;
  }

  /**
   * Ottiene tutti gli oggetti equipaggiati
   */
  public ArrayList<InventoryObject> getEquippedItems(Player player) {
    ArrayList<InventoryObject> equipped = new ArrayList<>();

    for (InventoryObject item : player.getInventory()) {
      if (item.getEquip()) {
        equipped.add(item);
      }
    }

    return equipped;
  }

  /**
   * Conta il numero di oggetti nell'inventario
   */
  public int getInventorySize(Player player) {
    return player.getInventory().size();
  }
}