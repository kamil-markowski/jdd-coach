package engine.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.domain.EventInLog;
import engine.domain.EventName;
import engine.domain.JsonEventToSend;
import engine.domain.User;
import engine.freemarker.TemplateProvider;
import engine.mapper.EventNameMapper;
import engine.mapper.UserMapper;
import engine.repository.EventInLogRepository;
import engine.repository.EventNameRepositoryBean;
import engine.service.UserService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import javax.ejb.EJB;
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
import java.util.Map;
import java.util.logging.Logger;

//import javax.naming.Context;

@WebServlet("")
public class WelcomeServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(WelcomeServlet.class.getName());

    private final static String eventNameString = "open welcome page";

    @Inject
    private TemplateProvider templateProvider;

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

//        String ipGuest = getClientIpAddress(req);

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
        eventInLog1.setCoachInfoLink("welcomePage no action on coach done");
        eventInLog1.setIp(ipAddress);
        eventInLog1.setEventName(eventName);
        eventInLog1.setEventDate(eventTime);
        eventInLog1.setUser(user);
        eventInLogRepository.save(eventInLog1);

        Template template = templateProvider.getTemplate(getServletContext(), "startPage.ftlh");
        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning("Template not created");
        }

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




//        eventInLog.setUser(user);
//mykong
//        private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {
//
//            Map<String, String> result = new HashMap<>();
//
//            Enumeration headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String key = (String) headerNames.nextElement();
//                String value = request.getHeader(key);
//                result.put(key, value);
//            }
//
//            return result;
//        }

//        ContextHolder contextHolder = new ContextHolder(req.getSession());
//        dataModel.put("name", contextHolder.getName());
//        dataModel.put("role", contextHolder.getRole());

