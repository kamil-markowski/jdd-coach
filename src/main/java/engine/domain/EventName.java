package engine.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "EventName.getByName",
                query = "SELECT e FROM EventName e where e.nameOfEvent= :name"
        ),
        @NamedQuery(
                name = "EventName.getById",
                query = "SELECT e FROM EventName e where e.id= :id"
        )
})

@Entity
@Table(name = "event_name")
public class EventName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nameOfEvent;

    @OneToMany(mappedBy = "eventName", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @NotNull
    private List<EventInLog> eventsInLog = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public List<EventInLog> getEventsInLog() {
        return eventsInLog;
    }

    public void setEventsInLog(List<EventInLog> eventsInLog) {
        this.eventsInLog = eventsInLog;
    }
}