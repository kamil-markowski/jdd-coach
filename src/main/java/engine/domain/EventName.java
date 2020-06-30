package engine.domain;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "EventName.getByName",
                query = "SELECT e FROM EventName e where e.nameOfEvent= :name"
        )
})

@Entity
@Table(name = "event_name")
public class EventName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOfEvent;

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
}
