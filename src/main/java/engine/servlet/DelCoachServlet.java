package engine.servlet;

import engine.domain.Coach;
import engine.domain.EventInLog;
import engine.domain.EventName;
import engine.domain.User;
import engine.mapper.EventNameMapper;
import engine.mapper.UserMapper;
import engine.repository.EventInLogRepository;
import engine.service.CoachService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/delete-coach")
public class DelCoachServlet extends HttpServlet {

    private final static String eventNameString = "coach deleted";

    @Inject
    CoachService coachService;

    @Inject
    private EventInLogRepository eventInLogRepository;

    @Inject
    private EventNameMapper eventNameMapper;

    @Inject
    private UserMapper userMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("coach");
        Long coachId = Long.parseLong(idParam);

        coachService.deleteById(coachId);

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
        eventInLog1.setCoachInfoLink("no link, coach with Id = "+ coachId + " has been deleted");
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
