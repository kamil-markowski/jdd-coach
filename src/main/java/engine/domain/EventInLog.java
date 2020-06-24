package engine.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventInLog {

    private Long id;

    private Coach coach;

    private String ip;

    private List<EventName>eventNames = new ArrayList<>();

    private String coachInfoLink;

    private LocalDate eventDate;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<EventName> getEventNames() {
        return eventNames;
    }

    public void setEventNames(List<EventName> eventNames) {
        this.eventNames = eventNames;
    }

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
