package engine.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import engine.domain.Coach;
import engine.domain.EventInLog;
import engine.domain.EventName;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.servlet.WelcomeServlet;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class EventInLogRepositoryBean implements EventInLogRepository,EventInLogRepositoryRemote {

    private final static Logger logger = Logger.getLogger(WelcomeServlet.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(EventInLog eventInLog) {
        entityManager.persist(eventInLog);
    }

    @Override
    public Optional<EventInLog> findByUserId(Long id) {
        return Optional.ofNullable(entityManager.find(EventInLog.class, id));
    }

    @Override
    public List<EventInLog> findAll() {
        Query query = entityManager
                .createNamedQuery("EventInLog.findAll");
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<String> getUsersNames() {
        return findAll().stream().map(u -> u.getEventName().toString()).collect(Collectors.toList());
    }

//    @Override
//    public List<List<String>> getEventsInLogList() {
//
//        return null;
//    }

    @Override
    public Optional<EventName> findByEventNameId(Long id) {
        return Optional.ofNullable(entityManager.find(EventName.class, id));
    }

    @Override
    public String createEventInLogRecord() {

        EventInLog recEventInLog = findAll().get((findAll().size())-1);

        long recId = findAll().stream().map(u -> u.getId().toString()).count();
        String recCoachInfoLink = recEventInLog.getCoachInfoLink();
        String recEventDate = recEventInLog.getEventDate().toString();
        String recIp = recEventInLog.getIp();
        String recEventName = recEventInLog.getEventName().getNameOfEvent();

//        String eventInLogRecord = Long.toString(recId) + " " + recCoachInfoLink + " " + recEventDate + " "
//                + recIp + " " + recEventName;

        ObjectMapper objectMapper = new ObjectMapper();
        EventInLog eventInLogForJson = new EventInLog();
        eventInLogForJson.setId(recId);
        eventInLogForJson.setCoachInfoLink(recCoachInfoLink);
        eventInLogForJson.setEventDate(recEventInLog.getEventDate());
        eventInLogForJson.setIp(recIp);

        String json = "";
        try {
            json = objectMapper.writeValueAsString(eventInLogForJson);
        } catch (JsonProcessingException e) {
            logger.warning("json not created");
        }
        return json;
    }

    @Override
    public String getEventInLogRecord() {
        String result = createEventInLogRecord();
        return result;
    }
}
