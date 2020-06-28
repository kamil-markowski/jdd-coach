package engine.servlet;

import engine.domain.Coach;
import engine.service.CoachService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test-load-db")
public class TestLoadCoachesDbServlet extends HttpServlet {

    @Inject
    CoachService coachService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Coach coach1 = new Coach();
        coach1.setLastName("Mark");
        coach1.setFirstName("Maja");
        coach1.setEmail("maja@wp.pl");
        coach1.setMobilePhone("123456");
        coachService.save(coach1);

        Coach coach2 = new Coach();
        coach2.setLastName("Mark");
        coach2.setFirstName("Oli");
        coach2.setEmail("oli@wp.pl");
        coach2.setMobilePhone("2222222");
        coachService.save(coach2);

        Coach coach3 = new Coach();
        coach3.setLastName("Mark");
        coach3.setFirstName("Kamil");
        coach3.setEmail("kamil@wp.pl");
        coach3.setMobilePhone("999999");
        coachService.save(coach3);

        resp.sendRedirect("/");
    }
}
