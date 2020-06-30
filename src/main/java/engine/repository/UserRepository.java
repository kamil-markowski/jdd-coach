package engine.repository;

import engine.domain.User;

public interface UserRepository {

    User getByIp(String ip);

    void save(User user);
}
