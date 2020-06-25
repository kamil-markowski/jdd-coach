package engine.repository;

import engine.domain.Coach;
import engine.domain.Course;
import engine.domain.EventInLog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class CoachRepositoryBean implements CoachRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Coach> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Coach.class, id));
    }

    @Override
    public void save(Coach coach) {
        entityManager.persist(coach);
    }

    @Override
    public Coach update(Coach coach) {
       return entityManager.merge(coach);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public List<Coach> findAll() {
        Query query = entityManager
                .createNamedQuery("Coach.findAll");
        return query.getResultList();
    }
}
