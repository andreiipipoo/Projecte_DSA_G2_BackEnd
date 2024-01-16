package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;

public interface ItemManager {

    /**
     * Retrieve a list of all Items.
     *
     * @return List of Items.
     */
    public List<Item> getItems();

    /**
     * Retrieve an Item by its unique identifier.
     *
     * @param idItem The unique identifier of the Item to be retrieved.
     * @return The Item with the specified ID.
     * @throws NoItemExistsException If no Item exists with the specified ID.
     * @throws SQLException         If a SQL-related exception occurs.
     */
    Item getItemById(String idItem) throws NoItemExistsException, SQLException;
}

