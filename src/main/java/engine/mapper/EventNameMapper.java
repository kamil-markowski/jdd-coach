package engine.mapper;

import engine.domain.EventName;
import engine.service.EventNameService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EventNameMapper {

    @EJB
    private EventNameService eventNameService;

    public EventName toEntity(EventName eventName) {
         EventName addedEventName = eventNameService.getOrCreate(eventName.getNameOfEvent());
        return addedEventName;
    }
}