package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import io.swagger.models.auth.In;

public class InventoryManagerImpl implements InventoryManager {
    final static Logger logger = Logger.getLogger(InventoryManagerImpl.class);
    private static InventoryManagerImpl instance;
    public static InventoryManagerImpl getInstance() {
        if (instance==null) instance = new InventoryManagerImpl();
        return instance;
    }

    //Get all items from inventory
    @Override
    public List<Inventory> getInventoryItems() {
        Session session = null;
        List<Inventory> items = null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(Inventory.class);
        } catch (Exception e) {
            logger.error("Error getting items", e);
        } finally {
            session.close();
        }
        return items;
    }

    //Get all items from inventory by player id
    /*@Override
    public List<Inventory> getInventoryItemsByPlayerId(String playerId){

    }*/
}
