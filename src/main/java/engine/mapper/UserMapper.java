package engine.mapper;

import engine.domain.User;
import engine.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserMapper {

    @EJB
    private UserService userService;

    public User toEntity(User user) {
        User addedUser = userService.getOrCreate(user.getIp());
        return addedUser;
    }
}