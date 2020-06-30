package engine.domain.mapper;

import engine.domain.EventName;
import engine.service.EventNameService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EventNameMapper {

    @EJB
    private EventNameService eventNameService;

    public EventName toEntity(EventName eventName) {
        eventName = eventNameService.getOrCreate(eventName.getNameOfEvent());
        return eventName;
    }
}
