package engine.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private Long id;

    private String courseName;

    private String courseCode;

    private List<Coach> listOfCouches = new ArrayList<>();

    private String codeLanguage;

    private Long participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<Coach> getListOfCouches() {
        return listOfCouches;
    }

    public void setListOfCouches(List<Coach> listOfCouches) {
        this.listOfCouches = listOfCouches;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    public Long getParticipants() {
        return participants;
    }

    public void setParticipants(Long participants) {
        this.participants = participants;
    }
}
