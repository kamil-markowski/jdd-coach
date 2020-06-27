package engine.servlet;

import engine.domain.Coach;
import engine.freemarker.TemplateProvider;
import engine.service.CoachService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/add-coach")
public class AddCoachServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(AddCoachServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @Inject
    private CoachService coachService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> dataModel = new HashMap<>();

        Template template = templateProvider.getTemplate(getServletContext(), "startPage.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning("Template not created");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Coach coach = new Coach();
        coach.setFirstName(req.getParameter("firstName"));
        coach.setLastName(req.getParameter("lastName"));
        coach.setEmail(req.getParameter("email"));
        coach.setMobilePhone(req.getParameter("phone"));

        coachService.save(coach);

        resp.sendRedirect("/add-coach");
    }


}
