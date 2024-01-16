package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;


public interface PlayerManager {

    /**
     * Add a new Player to the system.
     *
     * @param idPlayer   ID of the new Player.
     * @param username   Username of the new Player.
     * @param password   Password of the new Player.
     * @param telephone  Telephone number of the new Player.
     * @param email      Email address of the new Player.
     * @return The number of Players added (usually 1).
     */
    public int addPlayer(String idPlayer, String username, String password, String telephone, String email);

    /**
     * Retrieve a Player by their unique identifier.
     *
     * @param playerId ID of the Player to be retrieved.
     * @return The Player with the specified ID.
     * @throws PlayerNotRegisteredException If no Player exists with the specified ID.
     */
    public Player getPlayer(String playerId) throws PlayerNotRegisteredException;

    /**
     * Delete a Player from the system.
     *
     * @param playerId ID of the Player to be deleted.
     */
    public void deletePlayer(String playerId);

    /**
     * Retrieve a list of Items.
     *
     * @return List of Items.
     */
    public List<Item> getItems();

    /**
     * Buy an Item for a specific Player.
     *
     * @param idItem    ID of the Item to be purchased.
     * @param username  Username of the Player making the purchase.
     * @param idPlayer  ID of the Player making the purchase.
     * @throws NoItemExistsException        If the specified Item does not exist.
     * @throws SQLException                If a SQL-related exception occurs.
     * @throws YouAreBrokeWithCoinsException If the Player does not have enough coins to make the purchase.
     */
    public void buyItem(String idItem, String username, String idPlayer) throws NoItemExistsException, SQLException, YouAreBrokeWithCoinsException;



    Player getPlayerByUsername(String username);

    /**
     * Update the details of a Player.
     *
     * @param updatePlayer Object containing the updated Player details.
     * @throws SQLException If a SQL-related exception occurs.
     */
    void updatePlayer(UpdatePlayer updatePlayer) throws SQLException;

    // MINIMO 2 ALGUNA FUNCION FALTA SEGURO
}

