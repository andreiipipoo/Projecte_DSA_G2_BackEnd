package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Issue;
import java.util.List;

public interface IssueManager {
    public Issue addIssue(Issue i);
    public List<Issue> getAllIssues();
    public void deleteIssue(Issue i);
}
