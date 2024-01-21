package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Badges;
import edu.upc.dsa.CRUD.DAO.BadgesManager;
import edu.upc.dsa.CRUD.MYSQL.SessionImpl;
import java.util.List;
import org.apache.log4j.Logger;

public class BadgesManagerImpl implements BadgesManager {

    private static BadgesManagerImpl instance;
    final static Logger logger = Logger.getLogger(BadgesManagerImpl.class);
    private SessionImpl session;

    private BadgesManagerImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static BadgesManagerImpl getInstance(){
        if(instance == null){
            instance = new BadgesManagerImpl();
        }
        return instance;
    }
    @Override
    public Badges addBadges(Badges b){
        logger.info("Add new Badges: " + b);
        this.session.save(b);
        return b;
    }
    @Override
    public List<Badges> getAllBadges(){
        List<Badges> badgesList = this.session.getAll(Badges.class);
        logger.info("Get all Badges: " + badgesList.toString());
        return badgesList;
    }
    @Override
    public void deleteBadges(Badges b){
        logger.info("Delete Badges: " + b);
        this.session.delete(b);
    }

    @Override
    public Badges getBadgesByName(String name){
        Badges b = (Badges) this.session.getByName(Badges.class, name);
        if(b.getName() == null) {
            return null;
        }
        logger.info("Badges to get: " + b);
        return b;
    }

}
