package engine.service;

import engine.domain.EventName;
import engine.repository.EventNameRepository;
import engine.repository.EventNameRepositoryBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class EventNameService {

    private static final Logger LOGGER = Logger.getLogger(EventNameService.class.getName());

    @EJB
    private EventNameRepositoryBean eventNameRepositoryBean;

    public EventName getOrCreate(String name) {
        EventName eventName = eventNameRepositoryBean.getByName(name);
        if (eventName == null) {
            eventName = new EventName();
            eventName.setNameOfEvent(name);
            eventNameRepositoryBean.save(eventName);
        }
        return eventName;
    }
}
