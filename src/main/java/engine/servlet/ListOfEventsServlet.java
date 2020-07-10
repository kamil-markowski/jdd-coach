package engine.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.domain.*;
import engine.freemarker.TemplateProvider;
import engine.mapper.EventNameMapper;
import engine.mapper.UserMapper;
import engine.repository.EventInLogRepository;
import engine.service.CoachService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/list-event")
public class ListOfEventsServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(ListOfEventsServlet.class.getName());

    private final static String eventNameString = "open list of events page";

    @Inject
    TemplateProvider templateProvider;

    @Inject
    CoachService coachService;

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
        Map<String, Object> dataModel = new HashMap<>();

        Request get = Request.Get("http://localhost:8080/tracking/rest/list");
        String stringJson = get.execute().returnContent().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonEventToSend[] jsonEventRecieved = objectMapper.readValue(stringJson,JsonEventToSend[].class);
        for (JsonEventToSend jsonRec: jsonEventRecieved) {
            Request getJsonRec = Request.Get("http://localhost:8080/tracking/rest/list");
        }


        List<Coach> listOfCoaches = coachService.findAll();

        dataModel.put("list",jsonEventRecieved);

        Template template = templateProvider.getTemplate(getServletContext(), "listEvents.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning("Template not created");
        }

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        Cookie userCookie=new Cookie("userIp",ipAddress);
        userCookie.setMaxAge(30*24*60*60);
        resp.addCookie(userCookie);

        LocalDateTime eventTime = LocalDateTime.now();

        User user = new User();
        user.setIp(ipAddress);
        user = userMapper.toEntity(user);

        EventName eventName = new EventName();
        eventName.setNameOfEvent(eventNameString);
        eventName = eventNameMapper.toEntity(eventName);

        EventInLog eventInLog1 = new EventInLog();
        eventInLog1.setCoach(null);
        eventInLog1.setCoachInfoLink("list of events no action on coach done");
        eventInLog1.setIp(ipAddress);
        eventInLog1.setEventName(eventName);
        eventInLog1.setEventDate(eventTime);
        eventInLog1.setUser(user);
        eventInLogRepository.save(eventInLog1);

        String json = eventInLogRepository.createEventInLogRecord(eventInLog1);
        String host = "http://127.0.0.1:8080/tracking/rest/add";
        StringEntity jsonEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        Request request = Request.Post(host);
        request.body(jsonEntity).execute();
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
