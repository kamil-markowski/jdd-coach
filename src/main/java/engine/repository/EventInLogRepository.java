package engine.repository;

import engine.domain.EventInLog;
import java.util.List;
import java.util.Optional;

public interface EventInLogRepository {

    void save(EventInLog eventInLog);

    Optional<EventInLog> findByUserId (Long id);

    List<EventInLog> findAll();
}
