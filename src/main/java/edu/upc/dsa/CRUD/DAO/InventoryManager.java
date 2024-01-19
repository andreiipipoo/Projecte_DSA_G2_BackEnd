package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;

import java.beans.IntrospectionException;
import java.util.List;

public interface InventoryManager {
    List<Inventory> getAll();

    // FET
    boolean alreadyExists(String username, String itemname);

    // NOT OK
    Inventory addInventory(String username, String itemname);

    // OK
    List<Item> getUserInventory(String userid);

    // FET
    void makeActive(String userid, String itemid);

    // NOT OK
    void buyItem(String username, String itemname) throws IntrospectionException;
}
