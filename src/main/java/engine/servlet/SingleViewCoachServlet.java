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
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/single-view")
public class SingleViewCoachServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(SingleViewCoachServlet.class.getName());

    @Inject
    CoachService coachService;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("coach");
        Long coachId = Long.parseLong(idParam);
        Map<String, Object> dataModel = new HashMap<>();

        if (coachId < 0) {
            dataModel.put("errorMessage", "Wrong input.\n");
        } else {
            Coach foundCoach = new Coach();
            foundCoach = coachService.findById(coachId);
            if (foundCoach == null) {
                dataModel.put("errorMessage", "Drink not found.\n");
            }
            dataModel.put("coach", foundCoach);
//            statisticsService.addToStatistics(foundDrinkById);
        }

        Template template = templateProvider.getTemplate(getServletContext(), "singleView.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning(e.getMessage());
        }

    }
}
