package engine.repository;

import engine.domain.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachRepository {

    Optional<Coach> findById(Long id);

    void save(Coach coach);

    Optional<Coach> update(Coach coach, Long id);

    Boolean deleteById(Long id);

    List<Coach> findAll();
}
