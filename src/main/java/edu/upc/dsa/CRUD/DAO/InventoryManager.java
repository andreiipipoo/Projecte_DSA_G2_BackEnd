package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.exceptions.*;
import java.sql.SQLException;

import java.util.List;
public interface InventoryManager {
    public List<Inventory> getInventoryItems();

    //public List<Inventory> getInventoryItemsByPlayerId(String playerId);
}
