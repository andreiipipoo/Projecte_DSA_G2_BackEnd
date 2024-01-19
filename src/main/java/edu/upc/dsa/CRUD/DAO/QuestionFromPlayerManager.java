package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.QuestionFromPlayer;
import java.util.List;

public interface QuestionFromPlayerManager {
    public QuestionFromPlayer addQuestionFromPlayer(QuestionFromPlayer q);
    public List<QuestionFromPlayer> getAllQuestionsFromPlayer();
    public QuestionFromPlayer deleteQuestionFromPlayer(QuestionFromPlayer q);
}
