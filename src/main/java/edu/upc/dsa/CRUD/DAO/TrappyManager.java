package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;


public interface TrappyManager {

   int size();

   /** Players **/
   Player registerPlayer(Player p) throws EmailAlreadyInUseException;
   String loginPlayer(Credentials c) throws PlayerNotRegisteredException, PasswordIsIncorrectException;
   List <Player> getPlayers();
   int getNumPlayers();
   int getLoggedPlayers();

   /** Items **/
   int getNumItems();
   List <Item> ShopItems();

   /** Inventory **/
   List <Inventory> InventoryMagic();

   /** Partida **/

}
