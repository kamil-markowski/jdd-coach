package engine.storage;

import engine.domain.Coach;
import engine.domain.Course;
import engine.domain.EventInLog;

import java.util.ArrayList;
import java.util.List;

public class CoachDB {

    private static List<Coach> coachRepository = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<EventInLog> eventsInLogs = new ArrayList<>();

    public static List<Coach> getRepository() {
        if (0 == coachRepository.size()) {
            fillRepositoryWithDefaults();
        }
        return coachRepository;
    }

    private static void fillRepositoryWithDefaults() {

        Coach coach1 = new Coach();
        coach1.setId(1L);
        coach1.setFirstName("Jan");
        coach1.setLastName("Janka");
        coach1.setEmail("jan@wp.pl");
        coach1.setMobilePhone("123456");
        coach1.setCourses(courses);
        coach1.setEventsInLog(eventsInLogs);
        coachRepository.add(coach1);

        Coach coach2 = new Coach();
        coach2.setId(2L);
        coach2.setFirstName("Mark");
        coach2.setLastName("Marko");
        coach2.setEmail("marl@wp.pl");
        coach2.setMobilePhone("654321");
        coach2.setCourses(courses);
        coach2.setEventsInLog(eventsInLogs);
        coachRepository.add(coach2);
    }

    public static boolean contains(Coach coach) {
        List<Coach> repository = getRepository();
        for (Coach coachFromList : repository) {
            if (coachFromList.getId().equals(coach.getId())) {
                return true;
            }
        }
        return false;
    }
}

