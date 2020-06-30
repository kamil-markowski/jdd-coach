package engine.repository;

import engine.domain.EventName;

public interface EventNameRepository {

    EventName getByName(String name);

    void save(EventName eventName);
}
