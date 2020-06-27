package engine.servlet;

import engine.domain.Coach;
import engine.freemarker.TemplateProvider;
import engine.service.CoachService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/list-coach")
public class ListOfCoachesServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(ListOfCoachesServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @Inject
    CoachService coachService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> dataModel = new HashMap<>();

        List<Coach> listOfCoaches = coachService.findAll();

        dataModel.put("list",listOfCoaches);

        Template template = templateProvider.getTemplate(getServletContext(), "listCoaches.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning("Template not created");
        }
    }
}
