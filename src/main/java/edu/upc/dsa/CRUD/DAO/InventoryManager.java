package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;

import java.beans.IntrospectionException;
import java.util.List;

public interface InventoryManager {
    public List<Inventory> getInventoryList();
    public List<Item> getPlayerInventory(String idPlayer);
    public Inventory addInventory(String idPlayer, String idItem);
    public Inventory buyItem(String userName, String idItem);
    public boolean repeatItem(String playerName, String itemId);
}
