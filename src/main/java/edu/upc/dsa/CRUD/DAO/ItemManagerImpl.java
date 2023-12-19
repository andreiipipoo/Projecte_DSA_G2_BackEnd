package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;

public class ItemManagerImpl implements ItemManager{
    final static Logger logger = Logger.getLogger(ItemManagerImpl.class);
    private static ItemManagerImpl instance;
    public static ItemManagerImpl getInstance() {
        if (instance==null) instance = new ItemManagerImpl();
        return instance;
    }

    //Get all items
    @Override
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

    //Get an item by id
    @Override
    public Item getItemById(String id) throws NoItemExistsException {
        Session session = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            item = (Item) session.get(Item.class,"id", id);
            if (item == null) throw new NoItemExistsException();
        } catch (Exception e) {
            logger.error("Error getting item", e);
        } finally {
            session.close();
        }
        return item;
    }

}
