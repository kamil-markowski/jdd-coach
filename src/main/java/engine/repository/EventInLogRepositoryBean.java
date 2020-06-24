package engine.repository;

import engine.domain.EventInLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventInLogRepositoryBean implements EventInLogRepository{

    @Override
    public void save(EventInLog eventInLog) {
    }

    @Override
    public Optional<EventInLog> findByUser(Long id) {
        return findAll().stream()
                .filter(eventInLog -> eventInLog.getId().equals(id)).findFirst();
    }

    @Override
    public List<EventInLog> findAll() {
        List<EventInLog>eventInLogs = new ArrayList<>();
        return eventInLogs;
    }
}
