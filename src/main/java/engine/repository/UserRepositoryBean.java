package engine.repository;

import engine.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserRepositoryBean {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
    public User getByIp(String ip) {
        Query qry = entityManager.createNamedQuery("User.getByIp");
        qry.setParameter("ip",ip);
        List<User> resultList = qry.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

//    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
