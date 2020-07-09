package engine.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    @Column(name = "list_of_coaches")
    private List<Coach> listOfCoaches = new ArrayList<>();

    @Column(name = "code_language")
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
        return listOfCoaches;
    }

    public void setListOfCouches(List<Coach> listOfCouches) {
        this.listOfCoaches = listOfCouches;
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
