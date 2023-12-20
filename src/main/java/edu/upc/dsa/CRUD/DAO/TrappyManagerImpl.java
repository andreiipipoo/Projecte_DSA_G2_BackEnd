package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.CRUD.MYSQL.Session;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import java.sql.SQLException;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

public class TrappyManagerImpl implements TrappyManager {
    private static TrappyManager instance;
    final static Logger logger = Logger.getLogger(TrappyManagerImpl.class);
    private HashMap<String, Player> playersMap1;
    protected List<Player> players;
    protected List<Item> items;
    protected List<Player> playersLogged;
    private HashMap<String, Player> playersMap2;
    public List<Player> getPlayers(){
        return players;
    }

    public TrappyManagerImpl() {
        this.players = new LinkedList<>();
        this.items = new LinkedList<>();
        this.playersLogged = new LinkedList<>();
        playersMap1 = new HashMap<>();
        playersMap2 = new HashMap<>();
    }
    public static TrappyManager getInstance() {
        if (instance==null) instance = new TrappyManagerImpl();
        return instance;
    }

    //size of players
    public int size() {
        int ret = this.players.size();
        logger.info("size " + ret);

        return ret;
    }

    //Register a player
    @Override
    public Player registerPlayer(Player p) throws EmailAlreadyInUseException {
            PlayerManager pm = new PlayerManagerImpl();
            pm.addPlayer(p.getIdPlayer(), p.getUsername(),p.getPassword(),p.getTelephone(),p.getEmail());
            logger.info("new player registered");
            return p;
    }

    //Login a player
    @Override
    public String loginPlayer(Credentials c) throws PlayerNotRegisteredException, PasswordIsIncorrectException {
        try{
            // Create an instance of the player manager
            PlayerManager pm = new PlayerManagerImpl();

            // Create a HashMap to hold the player's credentials
            HashMap<String, String> credentials = new HashMap<>();
            credentials.put("username", c.getUsername());
            credentials.put("password", c.getPassword());

            // Retrieve the player information based on the provided username
            Player player = pm.getPlayerByUsername(c.getUsername());

            // Log the player information
            logger.info(player.getUsername() + " has logged in");
            logger.info(player.getPassword() + " has logged in");

            // Check if the player's password matches the provided password
            if (player.getPassword().equals(c.getPassword())) {
                // Log successful login information
                logger.info("Successful login for user: " + c.getUsername());
                logger.info("User ID: " + player.getIdPlayer());

                // Return the user ID as a successful login response
                return player.getIdPlayer();
            } else if(player.getUsername() == null){
                // Log unsuccessful login information
                logger.info("Unsuccessful login for user: " + c.getUsername());
                // Throw an exception if the player is not registered
                throw new PlayerNotRegisteredException();
            } else {
                // Log unsuccessful login information
                logger.info("Unsuccessful login for user: " + c.getUsername());
                // Throw an exception if the player's password is incorrect
                throw new PasswordIsIncorrectException();
            }
        } catch (Exception e) {
            logger.info("Something went wrong while logging in");
        }
        logger.warn("Username or password is incorrect");
        throw new PasswordIsIncorrectException();
    }

    //Shop items
    @Override
    public List<Item> ShopItems() {
        logger.info("Shop items");
        ItemManager im = new ItemManagerImpl();
        List<Item> items = im.getItems();
        return items;
    }

    //Inventory of a player
    @Override
    public List<Inventory> InventoryMagic() {
        logger.info("Inventory of a player");
        InventoryManager invm = new InventoryManagerImpl();
        List<Inventory> inventory = invm.getInventoryItems();
        return inventory;
    }

    //Get the number of players
    @Override
    public int getNumPlayers() {
        return this.players.size();
    }

    //Get the number of items
    @Override
    public int getNumItems() {
        return this.items.size();
    }

    //Get the number of logged players
    @Override
    public int getLoggedPlayers() {
        return this.playersLogged.size();
    }
}
