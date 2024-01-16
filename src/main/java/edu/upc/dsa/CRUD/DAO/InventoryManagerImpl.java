package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import io.swagger.models.auth.In;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of the InventoryManager interface to handle Inventory-related operations in the DAO layer.
 */
public class InventoryManagerImpl implements InventoryManager {
    final static Logger logger = Logger.getLogger(ItemManagerImpl.class);
    private static InventoryManagerImpl instance;

    /**
     * Singleton pattern to ensure a single instance of InventoryManagerImpl.
     *
     * @return The singleton instance of InventoryManagerImpl.
     */
    public static InventoryManagerImpl getInstance() {
        if (instance == null) instance = new InventoryManagerImpl();
        return instance;
    }

    /**
     * Retrieve a list of Inventory items associated with a specific Player.
     *
     * @param playerId ID of the Player whose Inventory items are to be retrieved.
     * @return List of Inventory items for the specified Player.
     * @throws NoItemExistsException If no Inventory items exist for the specified Player.
     * @throws SQLException         If a SQL-related exception occurs.
     */
    public List<Inventory> getInventoryItemsByPlayerId(String playerId) throws NoItemExistsException, SQLException {
        Session session = null;
        ItemManager itemManager = new ItemManagerImpl();
        HashMap<String, String> player = new HashMap<>();
        player.put("idPlayer", playerId);
        List<Inventory> playerItems = new ArrayList<>();
        try {
            session = FactorySession.openSession();
            List<Object> inInventory = session.findAll(Inventory.class, player);
            if (inInventory.size() != 0) {
                logger.info("The player with id: " + playerId + " has items in his inventory");
                for (Object o : inInventory) {
                    Inventory i = (Inventory) o;
                    try {
                        playerItems.add(i);
                    } catch (Exception e) {
                        throw new NoItemExistsException();
                    }
                }
                return playerItems;
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
        } finally {
            session.close();
        }
        return playerItems;
    }

    /**
     * Retrieve a list of all Inventory items.
     *
     * @return List of all Inventory items.
     */
    public List<Inventory> getInventoryItems() {
        Session session = null;
        List<Inventory> inventoryItems = null;
        try {
            session = FactorySession.openSession();
            inventoryItems = session.findAll(Inventory.class);
        } catch (Exception e) {
            // Handle exceptions appropriately
        } finally {
            session.close();
        }
        return inventoryItems;
    }
}

