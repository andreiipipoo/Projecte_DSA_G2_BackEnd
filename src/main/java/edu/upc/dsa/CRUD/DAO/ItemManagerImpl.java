package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;


public class ItemManagerImpl implements ItemManager {
    final static Logger logger = Logger.getLogger(ItemManagerImpl.class);

    /**
     * Retrieve an Item by its unique identifier.
     *
     * @param idItem The unique identifier of the Item to be retrieved.
     * @return The Item with the specified ID.
     * @throws NoItemExistsException If no Item exists with the specified ID.
     * @throws SQLException         If a SQL-related exception occurs.
     */
    public Item getItemById(String idItem) throws NoItemExistsException, SQLException {
        Session session = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            item = (Item) session.get(Item.class, "idItem", idItem);
        } catch (Exception e) {
            logger.error("Error getting item", e);
        } finally {
            session.close();
        }
        return item;
    }

    /**
     * Retrieve a list of all Items.
     *
     * @return List of Items.
     */
    public List<Item> getItems() {
        Session session = null;
        List<Item> items = null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(Item.class);
        } catch (Exception e) {
            logger.error("Error getting items", e);
        } finally {
            session.close();
        }
        return items;
    }
}

