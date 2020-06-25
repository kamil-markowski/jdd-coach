package engine.service;

import engine.domain.Coach;
import engine.repository.CoachRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class CoachService {

    @EJB
    private CoachRepository coachRepository;

    public Coach findById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    public void save(Coach coach) {
        coachRepository.save(coach);
    }

    public void update(Coach coach) {
        coachRepository.update(coach);
    }

    public void deleteById(Long id) {
        coachRepository.deleteById(id);
    }

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }
}
