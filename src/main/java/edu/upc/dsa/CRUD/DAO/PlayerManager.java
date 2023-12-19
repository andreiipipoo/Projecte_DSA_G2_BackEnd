package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.sql.SQLException;
import java.util.List;

public interface PlayerManager {
    public int addPlayer(String id, String username, String password, String telephone, String email);
    public Player getPlayerById(String id);
    public Player getPlayerByUsername(String username);
}
