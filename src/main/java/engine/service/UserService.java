package engine.service;

import engine.domain.EventName;
import engine.domain.User;
import engine.repository.UserRepository;
import engine.repository.UserRepositoryBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(EventNameService.class.getName());

    @EJB
    private UserRepositoryBean userRepositoryBean;

    public User getOrCreate(String ip) {
        User user = userRepositoryBean.getByIp(ip);
        if (user == null) {
            user = new User();
            user.setIp(ip);
            userRepositoryBean.save(user);
        }
        return user;
    }
}
