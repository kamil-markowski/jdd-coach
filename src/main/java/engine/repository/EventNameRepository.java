package engine.repository;

import engine.domain.EventInLog;
import engine.domain.EventName;

import java.util.Optional;

public interface EventNameRepository {

    EventName getByName(String name);

    void save(EventName eventName);
}
