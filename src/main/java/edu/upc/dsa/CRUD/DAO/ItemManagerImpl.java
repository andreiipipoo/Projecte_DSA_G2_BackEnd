package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.SessionImpl;
import edu.upc.dsa.models.Item;

import java.util.List;
import java.util.logging.Logger;

public class ItemManagerImpl implements ItemManager {

    static final Logger logger = Logger.getLogger(ItemManagerImpl.class.getName());
    private static ItemManagerImpl instance;
    private SessionImpl session;

    private  ItemManagerImpl() {
        this.session = SessionImpl.getInstance();
    }

    public static ItemManager getInstance() {

        if(instance == null) {

            instance = new ItemManagerImpl();
        }
        return instance;
    }

    @Override
    public List<Item> getStoreList() {
        List<Item> storeList = this.session.getAll(Item.class);
        return storeList;
    }

    @Override
    public Item addItem(Item i) {
        this.session.save(i);
        return i;
    }

    @Override
    public void deleteItem(String id) {
       Item i = (Item) this.session.getById(Item.class, id);
       logger.info("Item to delete: " + i);
       session.delete(i);
    }

    @Override
    public Item getItemById(String id) {
        Item i = (Item) this.session.getById(Item.class, id);
        if(i.getName() == null) {
            return null;
        }
        logger.info("Item to get: " + i);
        return i;
    }
}
