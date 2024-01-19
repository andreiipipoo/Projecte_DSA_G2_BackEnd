package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Item;

import java.util.List;

public interface ItemManager {
    public List<Item> getStoreList();
    public Item addItem(Item item);
    public Item getItemByName(String itemname);
}
