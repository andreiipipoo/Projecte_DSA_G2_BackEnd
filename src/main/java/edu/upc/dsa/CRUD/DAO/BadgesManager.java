package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Badges;
import java.util.List;
public interface BadgesManager {
    public Badges addBadges(Badges b);
    public List<Badges> getAllBadges();
    public void deleteBadges(Badges b);
    public Badges getBadgesByName(String name);
}
