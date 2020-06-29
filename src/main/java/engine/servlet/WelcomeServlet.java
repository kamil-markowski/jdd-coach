package engine.servlet;

import engine.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
//import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("")
public class WelcomeServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(WelcomeServlet.class.getName());

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> dataModel = new HashMap<>();

        String ipGuest = getClientIpAddress(req);

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        Cookie userCookie=new Cookie("userIp",ipGuest);
        resp.addCookie(userCookie);

        LocalDateTime eventTime = LocalDateTime.now();

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

        Template template = templateProvider.getTemplate(getServletContext(), "startPage.ftlh");

        try {
            template.process(dataModel, resp.getWriter());
        } catch (TemplateException e) {
            logger.warning("Template not created");
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