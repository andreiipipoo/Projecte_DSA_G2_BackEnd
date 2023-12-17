package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;


public interface TrappyManager {

   /** Players **/
   public void registerPlayer(String username, String password, String telephone, String email) throws PlayerExistsException, MissingDataException;
   public Player addPlayer(Player player) throws NotAnEmailException, MissingDataException, PlayerExistsException;
   public Player getPlayer(String id) throws PlayerNotFoundException;
   public List<Player> findAllPlayers();
   public int sizePlayers();
   public void updatePlayer(String field, String player, String value);
   public Credentials loginPlayer(String username, String password) throws PlayerNotFoundException, MissingDataException;

    /** Items **/




    /** Shop **/





    /** Partida **/






}
