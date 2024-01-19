package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.DAO.PlayerManager;
import edu.upc.dsa.models.Badges;
import edu.upc.dsa.models.Player;
import org.apache.log4j.Logger;
import java.util.LinkedList;
import java.util.List;
import edu.upc.dsa.CRUD.MYSQL.SessionImpl;

public class PlayerManagerImpl implements PlayerManager {
    private static PlayerManagerImpl instance;
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);
    private SessionImpl session;

    private PlayerManagerImpl(){
        this.session = SessionImpl.getInstance();
    }
    public static PlayerManager getInstance(){
        if(instance == null){
            instance = new PlayerManagerImpl();
        }
        return instance;
    }


    @Override
    public Player addPlayer(String username, String password, String telephone,String email){
        Player u = new Player(username,password,telephone,email);
        logger.info("Add new Player: " + u);
        session.save(u);
        return u;
    }


    @Override
    public Player getPlayerByUsername(String username){
        Player u = (Player) this.session.getByName(Player.class, username);
        if (u.getUsername() == null){
            return null;
        }
        logger.info("Player by username: " + u.toString());
        return u;
    }


    @Override
    public Player getPlayerByEmail(String email) {
        List<Player> playerList = this.getAllPlayers();
        for (Player u : playerList) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }



    @Override
    public Player getPlayerById(String id) {
        Player u = (Player) this.session.getById(Player.class, id);
        if (u.getUsername() == null){
            return null;
        }
        logger.info("Player by id: " + u.toString());
        return u;
    }


    @Override
    public void deletePlayer(String id) {
        Player player = (Player) this.session.getById(Player.class, id);
        logger.info("Deleting the following player: " + player);
        session.delete(player);
    }


    @Override
    public Player updateCoins(String username, int coins) {
        Player player = this.getPlayerByUsername(username);
        player.setCroCoins(coins);
        this.session.update(player);
        return player;
    }


    @Override
    public Player updatePlayer(String oldUsername, String username, String password, String telephone, String email)  {
        Player player = (Player) this.session.getByName(Player.class, oldUsername);
        player.setUsername(username);
        player.setPassword(password);
        player.setTelephone(telephone);
        player.setEmail(email);
        this.session.update(player);
        return player;
    }


    @Override
    public List<Player> getAllPlayers() {
        List<Player> playerList = new LinkedList<>();
        session.getAll(Player.class).forEach(u -> playerList.add((Player) u));
        return playerList;
    }


    /*
    @Override
    public List<Player> getCoinRanking() {
        List<Player> playerList = new LinkedList<>();
        session.getAll(Player.class).forEach(u -> playerList.add((Player) u));
        playerList.sort((p1, p2) -> p2.getCroCoins() - p1.getCroCoins());
        return playerList;
    }

     */

}
