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
import engine.validator.ValidatorInput;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ejb.EJB;
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
@WebServlet("/add-coach")
public class AddCoachServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(AddCoachServlet.class.getName());

    private final static String eventNameString = "coach added";

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

    @Inject
    ValidatorInput validatorInput;

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

        Integer check = 0;

        String firstNameReq = req.getParameter("firstName");
        if (firstNameReq == null || firstNameReq.isEmpty() || !validatorInput.validateSpecialChars(firstNameReq)){
            firstNameReq = "wrong input, try again";
            check += 1;
        }

        String lastNameReq = req.getParameter("lastName");
        if (lastNameReq == null || lastNameReq.isEmpty() || !validatorInput.validateSpecialChars(lastNameReq)){
            lastNameReq = "wrong input, try again";
            check += 1;
        }

        String phoneReq = req.getParameter("phone");
        if (phoneReq == null || phoneReq.isEmpty() || !validatorInput.validateMobilePhone(phoneReq)){
            phoneReq = "wrong input, try again";
            check += 1;
        }

        String emailReq = req.getParameter("email");
        if (emailReq == null || emailReq.isEmpty() || !validatorInput.validateEmail(emailReq)){
            emailReq = "wrong input, try again";
            check += 1;
        }

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> dataModel = new HashMap<>();

        if (check != 0) {

            dataModel.put("lastName",lastNameReq);
            dataModel.put("firstName",firstNameReq);
            dataModel.put("email",emailReq);
            dataModel.put("mobilePhone",phoneReq);

            Template template = templateProvider.getTemplate(getServletContext(), "wrongInput.ftlh");

            try {
                template.process(dataModel, resp.getWriter());
            } catch (TemplateException e) {
                logger.warning("Template not created");
            }

        }else {
//            Template template = templateProvider.getTemplate(getServletContext(), "startPage.ftlh");
//
//            try {
//                template.process(dataModel, resp.getWriter());
//            } catch (TemplateException e) {
//                logger.warning("Template not created");
//            }

            Coach coach = new Coach();
            coach.setFirstName(req.getParameter("firstName"));
            coach.setLastName(req.getParameter("lastName"));
            coach.setEmail(req.getParameter("email"));
            coach.setMobilePhone(req.getParameter("phone"));

            coachService.save(coach);

            Integer newCoachId = coachService.findAll().size();

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
            eventInLog1.setCoachInfoLink("/single-view?coach="+newCoachId);
            eventInLog1.setIp(ipAddress);
            eventInLog1.setEventName(eventName);
            eventInLog1.setEventDate(eventTime);
            eventInLog1.setUser(user);
            eventInLogRepository.save(eventInLog1);

            resp.sendRedirect("/list-coach");
        }
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
