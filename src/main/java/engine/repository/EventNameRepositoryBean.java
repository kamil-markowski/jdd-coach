package engine.repository;

import engine.domain.EventName;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EventNameRepositoryBean
//        implements EventNameRepository
{

    @PersistenceContext
    private EntityManager entityManager;

//    public List<Category> findAllNames() {
//        Query query = entityManager.createNamedQuery("Category.findAll");
//        return query.getResultList();
//    }
//    @Override
    public EventName getByName(String name) {
        Query qry = entityManager.createNamedQuery("EventName.getByName");
        qry.setParameter("name", name);
        List<EventName> resultList = qry.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

//    @Override
    public void save(EventName eventName) {
        entityManager.persist(eventName);
    }
}