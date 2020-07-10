package engine.repository;

import engine.domain.EventName;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventNameRepositoryBean {

    @PersistenceContext
    private EntityManager entityManager;

    public EventName getByName(String name) {
        Query qry = entityManager.createNamedQuery("EventName.getByName");
        qry.setParameter("name", name);
        List<EventName> resultList = qry.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public void save(EventName eventName) {
        entityManager.persist(eventName);
    }

//    public EventName findByEventNameId(Long id) {
//        Query qry = entityManager.createNamedQuery("EventName.getById");
//        qry.setParameter("id", id);
//        List<EventName> resultList = qry.getResultList();
//        return resultList.size() == 0 ? null : resultList.get(0);
//    }
}


//    public List<Category> findAllNames() {
//        Query query = entityManager.createNamedQuery("Category.findAll");
//        return query.getResultList();
//    }
//    @Override