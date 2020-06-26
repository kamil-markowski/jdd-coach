package engine.servlet;

import engine.domain.Coach;
import engine.service.CoachService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/add-coach")
public class AddCoach extends HttpServlet {

    private final static Logger logger = Logger.getLogger(AddCoach.class.getName());

    @Inject
    private CoachService coachService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Coach coach = new Coach();
        coach.setFirstName(req.getParameter("firstName"));
        coach.setLastName(req.getParameter("lastName"));
        coach.setEmail(req.getParameter("email"));
        coach.setMobilePhone(req.getParameter("phone"));

        coachService.save(coach);
    }
}
