package engine.repository;

import engine.domain.Coach;
import engine.domain.EventInLog;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class EventInLogRepositoryBean implements EventInLogRepository,EventInLogRepositoryRemote {

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
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<String> getUsersNames() {
        return findAll().stream().map(u -> u.getEventName().toString()).collect(Collectors.toList());
    }
}
