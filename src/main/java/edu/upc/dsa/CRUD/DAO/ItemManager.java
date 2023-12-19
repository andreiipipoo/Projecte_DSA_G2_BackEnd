package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;

public interface ItemManager {

    public List<Item> getItems();
    public Item getItemById(String id) throws NoItemExistsException;
}
