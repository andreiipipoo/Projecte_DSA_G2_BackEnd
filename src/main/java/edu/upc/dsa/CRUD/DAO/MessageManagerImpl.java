package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.SessionImpl;
import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.DAO.MessageManager;
import edu.upc.dsa.models.Message;
import java.util.List;

public class MessageManagerImpl implements MessageManager {

    private static MessageManagerImpl instance;
    final static Logger logger = Logger.getLogger(MessageManagerImpl.class);
    private SessionImpl session;

    private MessageManagerImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static MessageManagerImpl getInstance(){
        if(instance == null){
            instance = new MessageManagerImpl();
        }
        return instance;
    }

    @Override
    public Message addMessage(Message m){
        logger.info("Add new Message: " + m);
        this.session.save(m);
        return m;
    }

    @Override
    public List<Message> getAllMessages(){
        List<Message> messageList = this.session.getAll(Message.class);
        logger.info("Get all Messages: " + messageList.toString());
        return messageList;
    }

    @Override
    public int MessagesSize(){
        List<Message> messageList = this.getAllMessages();
        return messageList.size();
    }


}
