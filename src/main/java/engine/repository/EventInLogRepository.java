package engine.repository;

import engine.domain.EventInLog;
import engine.domain.EventName;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface EventInLogRepository {

    void save(EventInLog eventInLog);

    Optional<EventInLog> findByUserId (Long id);

    Optional<EventName> findByEventNameId(Long id);

    List<EventInLog> findAll();

    public String createEventInLogRecord();
}
