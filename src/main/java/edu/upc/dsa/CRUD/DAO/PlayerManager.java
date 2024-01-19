package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Badges;
import java.util.List;

public interface PlayerManager {
    public Player addPlayer(String username, String password, String telephone, String email);

    public Player updatePlayer(String oldUsername, String username, String password, String telephone, String email);

    public Player updateCoins(String username, int coins);

    public Player getPlayerById(String id);

    public Player getPlayerByUsername(String username);

    public Player getPlayerByEmail(String email);
    public List<Player> getAllPlayers();

    //public List<Player> getCoinRanking();

    public void deletePlayer(String id);
}
