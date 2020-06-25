package engine.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_in_log")
public class EventInLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "eventsInLog")
    private List<Coach> coaches;

    private String ip;

    @ManyToOne
    @JoinColumn(name = "event_name")
    private EventName eventName;

    private String coachInfoLink;

    private LocalDate eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Coach> getCoach() {
        return coaches;
    }

    public void setCoach(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

//    public List<EventName> getEventNames() {
//        return eventNames;
//    }
//
//    public void setEventNames(List<EventName> eventNames) {
//        this.eventNames = eventNames;
//    }

    public String getCoachInfoLink() {
        return coachInfoLink;
    }

    public void setCoachInfoLink(String coachInfoLink) {
        this.coachInfoLink = coachInfoLink;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
