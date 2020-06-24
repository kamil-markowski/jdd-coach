package engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobilePhone;

    private List<Course> courses = new ArrayList<>();

    private List<EventInLog> eventsInLog = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<EventInLog> getEventsInLog() {
        return eventsInLog;
    }

    public void setEventsInLog(List<EventInLog> eventsInLog) {
        this.eventsInLog = eventsInLog;
    }
}
