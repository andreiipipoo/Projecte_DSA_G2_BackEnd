package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.exceptions.*;
import java.sql.SQLException;

import java.util.List;


public interface InventoryManager {

    /**
     * Retrieve a list of all Inventory items.
     *
     * @return List of Inventory items.
     */
    public List<Inventory> getInventoryItems();

    /**
     * Retrieve a list of Inventory items associated with a specific Player.
     *
     * @param playerId ID of the Player whose Inventory items are to be retrieved.
     * @return List of Inventory items for the specified Player.
     * @throws SQLException           If a SQL-related exception occurs.
     * @throws NoItemExistsException  If no Inventory items exist for the specified Player.
     */
    public List<Inventory> getInventoryItemsByPlayerId(String playerId) throws SQLException, NoItemExistsException;
}

