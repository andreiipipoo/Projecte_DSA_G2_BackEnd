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


    // TODOS LOS INVENTARIOS QUE EXISTEN
    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList = this.session.getAll(Inventory.class);
        return inventoryList;
    }

    // YA TIENE EL ITEM EN SU INVENTARIO??
    @Override
    public boolean repeatItem(String playerName, String itemId) {
        List<Inventory> inventories = this.getInventoryList();
        Player player = playerManager.getPlayerByUsername(playerName);
        Item item = itemManager.getItemById(itemId);

        for (Inventory i : inventories) {
            if (i.getIdPlayer().equals(player.getId()) && i.getIdItem().equals(item.getId())) {
                return true;
            }
        }
        return false;
    }

    // AÑADIR UN NUEVO INVENTARIO
    @Override
    public Inventory addInventory(String playerId, String itemId) {
        Player p = (Player) this.session.getById(Player.class, playerId);
        Item i = (Item) this.session.getById(Item.class, itemId);
        Inventory ii = new Inventory(p.getId(), i.getId());
        this.session.save(ii);
        return ii;
    }

    // Inventario de un jugador
    @Override
    public List<Item> getPlayerInventory(String playerId) {
        List<Inventory> inventories = this.getInventoryList();
        List<Item> playerItems = new LinkedList<>();

        for (Inventory i : inventories) {
            if (i.getIdPlayer().equals(playerId)) {
                Item item = (Item) this.session.getById(Item.class, i.getIdItem());
                playerItems.add(item);
            }
        }
        return playerItems;
    }

    // Comprar un item/skin para su inventario
    @Override
    public Inventory buyItem(String playerId, String itemId) {
        Inventory inventory = null;
        Player u = (Player) this.session.getById(Player.class, playerId);
        Item i = (Item) this.session.getById(Item.class, itemId);
        if(u.getCroCoins() >= i.getPrice() && !this.repeatItem(playerId, itemId)){
            inventory = this.addInventory(u.getId(), itemId);
            int coins = u.getCroCoins() - i.getPrice();
            u = PlayerManagerImpl.getInstance().updateCoins(playerId, coins);
        }
        return inventory;
    }
}
