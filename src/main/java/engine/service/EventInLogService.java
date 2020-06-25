package engine.service;

import engine.domain.EventInLog;
import engine.repository.EventInLogRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class EventInLogService {

    @EJB
    private EventInLogRepository eventInLogRepository;

    public void save(EventInLog eventInLog) {
        eventInLogRepository.save(eventInLog);
    }

    public EventInLog findByUserId (Long id) {
        return eventInLogRepository.findByUserId(id).orElse(null);
    }

    public List<EventInLog> findAll() {
        return eventInLogRepository.findAll();
    }
}
