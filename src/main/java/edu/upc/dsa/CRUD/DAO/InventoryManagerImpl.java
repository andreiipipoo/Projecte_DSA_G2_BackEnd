package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.SessionImpl;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.beans.IntrospectionException;

public class InventoryManagerImpl implements InventoryManager {
    static final Logger logger = Logger.getLogger(PlayerManagerImpl.class.getName());
    private static InventoryManagerImpl manager;
    private static PlayerManager playerManager = PlayerManagerImpl.getInstance();
    private static ItemManager itemManager = ItemManagerImpl.getInstance();
    private SessionImpl session;

    InventoryManagerImpl() {
        this.session = SessionImpl.getInstance();
    }

    public static InventoryManagerImpl getInstance() {

        if(manager == null) {

            manager = new InventoryManagerImpl();
        }
        return manager;
    }


    @Override
    public List<Inventory> getAll() {
        List<Inventory> inventories = this.session.getAll(Inventory.class);
        return inventories;
    }

    // FET
    @Override
    public boolean alreadyExists(String username, String itemname) {
        List<Inventory> all = this.getAll();
        Player user = playerManager.getPlayerByUsername(username);
        Item item = itemManager.getItemByName(itemname);

        for (Inventory i : all) {
            if ((i.getIdPlayer().equals(user.getId())) && (i.getIdItem().equals(item.getId()))) {
                return true;
            }
        }
        return false;
    }

    // NOT OK
    @Override
    public Inventory addInventory(String username, String itemname) {
        Player user = (Player) this.session.getByName(Player.class, username);
        Item item = (Item) this.session.getByName(Item.class, itemname);

        Inventory i = new Inventory(user.getId(), item.getId());
        this.session.save(i);
        return i;
    }

    // OK
    @Override
    public List<Item> getUserInventory(String userid) {
        List<Inventory> inventories = this.getAll();
        List<Item> userItems = new LinkedList<>();

        for (Inventory i : inventories) {
            if (i.getIdPlayer().equals(userid)) {
                Item item = (Item) this.session.getById(Item.class, i.getIdItem());
                userItems.add(item);
            }
        }
        return userItems;
    }

    // FET HE FET DELETE CATCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    @Override
    public void makeActive(String userid, String itemid) {
        List <Inventory> iList = this.getAll();
        iList.forEach(i -> {
            if(i.getIdItem().equals(itemid) && i.getIdPlayer().equals(userid)) {
                //i.setActive(true);
                this.session.update(i);
            }
        });
    }

    // NOT OK
    @Override
    public void buyItem(String username, String itemname) throws IntrospectionException {
        Player user = (Player) this.session.getByName(Player.class, username);
        Item item = (Item) this.session.getByName(Item.class, itemname);

        int coinBalance = user.getCroCoins() - item.getPrice();
        user.setCroCoins(coinBalance);
        this.session.update(user);

        this.addInventory(username, itemname);
    }
}
