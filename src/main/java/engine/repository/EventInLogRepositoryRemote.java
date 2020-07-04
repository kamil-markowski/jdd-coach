package engine.repository;

import engine.domain.EventInLog;

import javax.ejb.Remote;
import java.util.List;
import java.util.Optional;

@Remote
public interface EventInLogRepositoryRemote {

    void save(EventInLog eventInLog);

//    Optional<EventInLog> findByUserId (Long id);

    List<String> getUsersNames();

//    List<EventInLog> findAll();
}
