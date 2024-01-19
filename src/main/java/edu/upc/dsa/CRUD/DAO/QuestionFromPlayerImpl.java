package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.SessionImpl;
import edu.upc.dsa.models.QuestionFromPlayer;
import java.util.List;
import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.DAO.QuestionFromPlayerManager;

public class QuestionFromPlayerImpl implements QuestionFromPlayerManager {

    private static QuestionFromPlayerImpl instance;
    final static Logger logger = Logger.getLogger(QuestionFromPlayerImpl.class);
    private SessionImpl session;

    private QuestionFromPlayerImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static QuestionFromPlayerImpl getInstance(){
        if(instance == null){
            instance = new QuestionFromPlayerImpl();
        }
        return instance;
    }

    @Override
    public QuestionFromPlayer addQuestionFromPlayer(QuestionFromPlayer q){
        logger.info("Add new QuestionFromPlayer: " + q);
        this.session.save(q);
        return q;
    }

    @Override
    public List<QuestionFromPlayer> getAllQuestionsFromPlayer(){
        List<QuestionFromPlayer> questionFromPlayerList = this.session.getAll(QuestionFromPlayer.class);
        logger.info("Get all QuestionFromPlayer: " + questionFromPlayerList.toString());
        return questionFromPlayerList;
    }

    @Override
    public QuestionFromPlayer deleteQuestionFromPlayer(QuestionFromPlayer q){
        logger.info("Delete QuestionFromPlayer: " + q);
        this.session.delete(q);
        return q;
    }
}
