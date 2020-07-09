package engine.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "EventInLog.findAll",
                query = "SELECT e FROM EventInLog e"
        )
})

@Entity
@Table(name = "event_in_log")
public class EventInLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "eventsInLog")
//    @NotNull
    private List<Coach> coaches;

    @NotNull
    private String ip;

    @ManyToOne
    @JoinColumn(name = "event_name_id")
//    @NotNull
    private EventName eventName;

//    @NotNull
    @Column(name = "coach_info_link")
    private String coachInfoLink;

    @NotNull
    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @NotNull
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

    public EventName getEventName() {
        return eventName;
    }

    public void setEventName(EventName eventName) {
        this.eventName = eventName;
    }

    public String getCoachInfoLink() {
        return coachInfoLink;
    }

    public void setCoachInfoLink(String coachInfoLink) {
        this.coachInfoLink = coachInfoLink;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
