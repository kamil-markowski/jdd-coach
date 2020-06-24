package engine;

import engine.repository.CoachRepositoryBean;
import engine.storage.CoachDB;

public class TempAppCheck {
    public static void main(String[] args) {
        System.out.println("Hello");

        System.out.println(
        CoachDB.getRepository().get(0).getId()+" "+
                CoachDB.getRepository().get(1).getId());

        CoachRepositoryBean coachRepositoryBean = new CoachRepositoryBean();
        coachRepositoryBean.deleteById(2L);
        System.out.println(
        coachRepositoryBean.findAll());

    }
}
