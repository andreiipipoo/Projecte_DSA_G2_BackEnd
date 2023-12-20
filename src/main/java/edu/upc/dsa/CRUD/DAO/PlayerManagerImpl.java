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


public class PlayerManagerImpl implements PlayerManager {
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);
    private static PlayerManagerImpl instance;
    public static PlayerManagerImpl getInstance() {
        if (instance==null) instance = new PlayerManagerImpl();
        return instance;
    }

    //Add a player
    @Override
    public int addPlayer(String idPlayer, String username, String password, String telephone, String email) {
        Session session = null;
        int res = 0;
        try {
            session = FactorySession.openSession();
            Player player = new Player(idPlayer, username, password, telephone, email);
            session.save(player);
        } catch (Exception e) {
            logger.error("Error adding a player",e);

        } finally {
            session.close();
        }
        return res;
    }

    //Get a player by username
    @Override
    public Player getPlayerByUsername(String username) {
        Session session = null;
        Player player = null;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class,"username", username);
        } catch (Exception e) {
            logger.error("Error getting player", e);
        } finally {
            session.close();
        }
        return player;
    }

    //Get a player by id
    @Override
    public Player getPlayerById(String idPlayer) {
        Session session = null;
        Player player = null;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class,"idPlayer", idPlayer);
        } catch (Exception e) {
            logger.error("Error getting player", e);
        } finally {
            session.close();
        }
        logger.info("Player with id "+ player.getIdPlayer()  + " found");
        return player;
    }
}
