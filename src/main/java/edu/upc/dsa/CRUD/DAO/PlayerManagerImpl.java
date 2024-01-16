package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import org.apache.log4j.Logger;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of the PlayerManager interface to handle Player-related operations in the DAO layer.
 */
public class PlayerManagerImpl implements PlayerManager {
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);
    private static PlayerManagerImpl instance;

    /**
     * Singleton pattern to ensure a single instance of PlayerManagerImpl.
     *
     * @return The singleton instance of PlayerManagerImpl.
     */
    public static PlayerManagerImpl getInstance() {
        if (instance == null) instance = new PlayerManagerImpl();
        return instance;
    }

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
    public int addPlayer(String idPlayer, String username, String password, String telephone, String email) {
        Session session = null;
        int res = 0;
        try {
            session = FactorySession.openSession();
            Player player = new Player(idPlayer, username, password, telephone, email);
            session.save(player);
        } catch (Exception e) {
            // LOG
        } finally {
            session.close();
        }
        return res;
    }

    /**
     * Retrieve a Player by their unique identifier.
     *
     * @param playerId ID of the Player to be retrieved.
     * @return The Player with the specified ID.
     */
    public Player getPlayer(String playerId) {
        Session session = null;
        Player player = null;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "idPlayer", playerId);
        } catch (Exception e) {
            // LOG
        } finally {
            session.close();
        }
        logger.info("Player with id: " + playerId + " retrieved");
        return player;
    }

    /**
     * Retrieve a Player by their username.
     *
     * @param username Username of the Player to be retrieved.
     * @return The Player with the specified email address.
     */
    public Player getPlayerByUsername(String username) {
        Session session = null;
        Player player = null;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "username", username);
        } catch (Exception e) {
            // LOG
        } finally {
            session.close();
        }
        logger.info("Player with email: " + username + " retrieved");
        return player;
    }

    /**
     * Delete a Player from the system.
     *
     * @param playerId ID of the Player to be deleted.
     */
    public void deletePlayer(String playerId) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "idPlayer", playerId);
            session.delete(player);
        } catch (Exception e) {
            // LOG
        } finally {
            session.close();
        }
        logger.info("Player with id: " + playerId + " deleted");
    }

    /**
     * Retrieve a list of Items.
     *
     * @return List of Items.
     */
    public List<Item> getItems() {
        Session session = null;
        List<Item> items = null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(Player.class);
        } catch (Exception e) {
            // LOG
        } finally {
            session.close();
        }
        return items;
    }

    /**
     * Placeholder method for buying an Item.
     * @param idItem ID of the Item to be purchased.
     * @param username Username of the Player making the purchase.
     * @param idPlayer ID of the Player making the purchase.
     * @throws NoItemExistsException If the specified Item does not exist.
     * @throws SQLException If a SQL-related exception occurs.
     * @throws YouAreBrokeWithCoinsException If the Player does not have enough coins to make the purchase.
     */
    public void buyItem(String idItem, String username, String idPlayer) throws NoItemExistsException, SQLException, YouAreBrokeWithCoinsException {
        // Placeholder method for buying an Item
    }

    /**
     * Update the details of a Player.
     *
     * @param updatePlayer Object containing the updated Player details.
     * @throws SQLException If a SQL-related exception occurs.
     */
    public void updatePlayer(UpdatePlayer updatePlayer) throws SQLException {
        Session session = null;
        Player player;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "idPlayer", updatePlayer.getIdPlayer());
            logger.info("Updating player with id: " + updatePlayer.getIdPlayer());
            session.update(updateInformation(player, updatePlayer));
            logger.info("Player with id: " + updatePlayer.getIdPlayer() + " updated");
        } catch (SQLException e) {
            logger.info("Error updating player with id: " + updatePlayer.getIdPlayer());
            throw new SQLException();
        } finally {
            session.close();
        }
    }

    private Player updateInformation(Player player, UpdatePlayer updatePlayer) {
        player.setUsername(updatePlayer.getUsername());
        player.setPassword(updatePlayer.getPassword());
        player.setTelephone(updatePlayer.getTelephone());
        player.setEmail(updatePlayer.getEmail());
        return player;
    }
}

