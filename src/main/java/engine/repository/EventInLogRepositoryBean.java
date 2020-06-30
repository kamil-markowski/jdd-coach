package engine.repository;

import engine.domain.Coach;
import engine.domain.EventInLog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventInLogRepositoryBean implements EventInLogRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(EventInLog eventInLog) {
        entityManager.persist(eventInLog);
    }

    @Override
    public Optional<EventInLog> findByUserId(Long id) {
        return Optional.ofNullable(entityManager.find(EventInLog.class, id));
    }

    @Override
    public List<EventInLog> findAll() {
        Query query = entityManager
                .createNamedQuery("EventInLog.findAll");
        return query.getResultList();
    }
}
