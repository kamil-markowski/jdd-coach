package engine.repository;

import engine.domain.Coach;
import engine.storage.CoachDB;

import java.util.List;
import java.util.Optional;

public class CoachRepositoryBean implements CoachRepository {

    @Override
    public Optional<Coach> findById(Long id) {
        return findAll().stream()
                .filter(coach -> coach.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Coach coach) {
        CoachDB.getRepository().add(coach);
    }

    @Override
    public Coach update(Coach coach) {
        return coach;
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Coach> first = CoachDB.getRepository().stream()
                .filter(coach -> coach.getId().equals(id))
                .findFirst();
                return first.map(coach -> {
                    CoachDB.getRepository().remove(first);
                    return true;
                }).orElseGet(()->false);
    }

    @Override
    public List<Coach> findAll() {
        return CoachDB.getRepository();
    }
}
