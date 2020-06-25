package engine;

import engine.domain.Coach;
import engine.domain.Course;
import engine.domain.EventInLog;
import engine.repository.CoachRepositoryBean;
import engine.storage.CoachDB;

import java.util.ArrayList;
import java.util.List;

public class TempAppCheck {
    public static void main(String[] args) {
        System.out.println("Hello");

//        System.out.println(
//        CoachDB.getRepository().get(0).getId()+" "+
//                CoachDB.getRepository().get(1).getId());

        CoachRepositoryBean test = new CoachRepositoryBean();
        List<Course> courses = new ArrayList<>();
        List<EventInLog> eventsInLogs = new ArrayList<>();

        Coach coach1 = new Coach();
        coach1.setId(1L);
        coach1.setFirstName("Jan");
        coach1.setLastName("Janka");
        coach1.setEmail("jan@wp.pl");
        coach1.setMobilePhone("123456");
//        coach1.setCourses(courses);
//        coach1.setEventsInLog(eventsInLogs);
        test.save(coach1);
        Boolean deleted = test.deleteById(1L);

        test.findById(1L);
        System.out.println(deleted);
        System.out.println(test.findAll().size());



//        coachRepositoryBean.deleteById(2L);
//        System.out.println(
//        coachRepositoryBean.findAll());

    }
}
