package engine;

import engine.domain.Coach;
import engine.domain.Course;
import engine.domain.EventInLog;
import engine.repository.CoachRepositoryBean;
import engine.service.CoachService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TempAppCheck {

//    @Inject
//    CoachService coachService;
//
//    @Inject
//    CoachRepositoryBean coachRepositoryBean;

    public static void main(String[] args) {



        List<Course> courses = new ArrayList<>();
        List<EventInLog> eventsInLogs = new ArrayList<>();

        Coach coach1 = new Coach();
        coach1.setId(1L);
        coach1.setFirstName("Jan");
        coach1.setLastName("Janka");
        coach1.setEmail("jan@wp.pl");
        coach1.setMobilePhone("123456");
        coach1.setCourses(courses);
        coach1.setEventsInLog(eventsInLogs);
        System.out.println(("done"));

    }
}
