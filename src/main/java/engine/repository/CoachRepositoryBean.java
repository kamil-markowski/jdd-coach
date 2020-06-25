package engine.repository;

import engine.domain.Coach;
import engine.domain.Course;
import engine.domain.EventInLog;
import engine.storage.CoachDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoachRepositoryBean implements CoachRepository {

    List<Coach> coaches = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<EventInLog> eventsInLogs = new ArrayList<>();

    @Override
    public Optional<Coach> findById(Long id) {
        return findAll().stream()
                .filter(coach -> coach.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Coach coach) {
        coaches.add(coach);
    }

    @Override
    public Optional<Coach> update(Coach coach, Long id) {
        return coaches.stream().filter(coach1 -> coach1.getId().equals(id)).map(coach1 -> {
            coach1.setId(1L);
            coach1.setFirstName("Jan");
            coach1.setLastName("Janka");
            coach1.setEmail("jan@wp.pl");
            coach1.setMobilePhone("123456");
            coach1.setCourses(courses);
            coach1.setEventsInLog(eventsInLogs);
            coaches.add(coach1);
            return coach1;
        }).findFirst();
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Coach> first = coaches.stream()
                .filter(coach -> coach.getId().equals(id))
                .findFirst();
                return first.map(coach -> {
                    coaches.remove(first);
                    return true;
                }).orElseGet(()->false);
    }

    @Override
    public List<Coach> findAll() {
        return coaches;
    }
}
