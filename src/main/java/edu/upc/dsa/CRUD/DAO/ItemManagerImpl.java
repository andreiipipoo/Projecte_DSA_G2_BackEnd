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
    // FET
    public Item addItem(Item item) {
        this.session.save(item);
        logger.info("Item saved: " + item.toString());
        return item;
    }

    // FET
    @Override
    public Item getItemByName(String itemname) {
        Item item = (Item) this.session.getByName(Item.class, itemname);
        if (item.getName() == null) return null;
        logger.info("item by name: " + item.toString());
        return item;
    }
}
