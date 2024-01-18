package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.DAO.PlayerManager;
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
        session.save(u);
        logger.info("Add new Player: " + u);
        return u;
    }






    @Override
    public Player getPlayerByUsername(String username){
        Player u = (Player) this.session.getByName(Player.class, username);
        if (u.getUsername() == null){
            return null;
        }
        logger.info("Get player by username: " + username);
        return u;
    }






    @Override
    public Player getPlayerByEmail(String email) {
        List<Player> players = new LinkedList<>();
        session.findAll(Player.class).forEach(player -> players.add((Player) player));
        for (Player u: players){
            if(u.getEmail().equals(email)){return u;}
        }
        return null;
    }



    @Override
    public Player getPlayerById(String id) {
        Player u = (Player) this.session.getById(Player.class, id);
        if (u.getUsername() == null){
            return null;
        }
        logger.info("Get player by id");
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
        Player player = (Player) this.session.getByName(Player.class, username);
        logger.info("Updating coins" + coins);
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




    /*
    @Override
    public List<Player> getAllPlayers() {
        List<Player> playerList = new LinkedList<>();
        session.getAll(Player.class).forEach(u -> playerList.add((Player) u));
        return playerList;
    }





    @Override
    public List<Player> getCoinRanking() {
        List<Player> playerList = new LinkedList<>();
        session.getAll(Player.class).forEach(u -> playerList.add((Player) u));
        playerList.sort((p1, p2) -> p2.getCroCoins() - p1.getCroCoins());
        return playerList;
    }

     */
}
