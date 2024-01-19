package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Issue;
import edu.upc.dsa.CRUD.DAO.IssueManager;
import edu.upc.dsa.CRUD.MYSQL.SessionImpl;
import java.util.List;
import org.apache.log4j.Logger;

public class IssueManagerImpl implements IssueManager {

    private static IssueManagerImpl instance;
    final static Logger logger = Logger.getLogger(IssueManagerImpl.class);
    private SessionImpl session;

    private IssueManagerImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static IssueManagerImpl getInstance(){
        if(instance == null){
            instance = new IssueManagerImpl();
        }
        return instance;
    }
    @Override
    public Issue addIssue(Issue i){
        logger.info("Add new Issue: " + i);
        this.session.save(i);
        return i;
    }
    @Override
    public List<Issue> getAllIssues(){
        List<Issue> issueList = this.session.getAll(Issue.class);
        logger.info("Get all Issues: " + issueList.toString());
        return issueList;
    }
    @Override
    public void deleteIssue(Issue i){
        logger.info("Delete Issue: " + i);
        this.session.delete(i);
    }

}
