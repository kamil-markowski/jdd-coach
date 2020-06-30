package engine.servlet;

import engine.domain.Coach;
import engine.domain.EventInLog;
import engine.domain.EventName;
import engine.domain.User;
import engine.freemarker.TemplateProvider;
import engine.mapper.EventNameMapper;
import engine.mapper.UserMapper;
import engine.repository.EventInLogRepository;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/edit-coach")
public class EditCoachServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(EditCoachServlet.class.getName());

    private final static String eventNameString = "coach edited";


    @Inject
    TemplateProvider templateProvider;

    @Inject
    private CoachService coachService;

    @Inject
    private EventInLogRepository eventInLogRepository;

    @Inject
    private EventNameMapper eventNameMapper;

    @Inject
    private UserMapper userMapper;

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

        Template template = templateProvider.getTemplate(getServletContext(), "editView.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("coach");
        Long coachId = Long.parseLong(idParam);

        Coach editedCoach = new Coach();
        editedCoach = coachService.findById(coachId);

        editedCoach.setLastName(req.getParameter("lastName"));
        editedCoach.setFirstName(req.getParameter("firstName"));
        editedCoach.setEmail(req.getParameter("email"));
        editedCoach.setMobilePhone(req.getParameter("mobilePhone"));

        coachService.update(editedCoach);

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        LocalDateTime eventTime = LocalDateTime.now();

        User user = new User();
        user.setIp(ipAddress);
        user = userMapper.toEntity(user);

        EventName eventName = new EventName();
        eventName.setNameOfEvent(eventNameString);
        eventName = eventNameMapper.toEntity(eventName);

        EventInLog eventInLog1 = new EventInLog();
        eventInLog1.setCoach(null);
        eventInLog1.setCoachInfoLink("/single-view?coach="+editedCoach.getId());
        eventInLog1.setIp(ipAddress);
        eventInLog1.setEventName(eventName);
        eventInLog1.setEventDate(eventTime);
        eventInLog1.setUser(user);
        eventInLogRepository.save(eventInLog1);

        resp.sendRedirect("/list-coach");
    }

    public static String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    public static String getClientIpAddress(HttpServletRequest req) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = req.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return req.getRemoteAddr();
    }


}
