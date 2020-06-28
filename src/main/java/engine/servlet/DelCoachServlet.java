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

@WebServlet("/del-coach")
public class DelCoachServlet extends HttpServlet {

    @Inject
    CoachService coachService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("coach");
        Long coachId = Long.parseLong(idParam);
        coachService.deleteById(coachId);

        resp.sendRedirect("/list-coach");
    }
}
