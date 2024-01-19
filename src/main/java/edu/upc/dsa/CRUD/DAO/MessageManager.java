package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Message;
import java.util.List;
public interface MessageManager {
    public Message addMessage(Message m);
    public List<Message> getAllMessages();
    public int MessagesSize();
}
