package engine.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "login_user")
//    private String loginUser;
//
//    @Column(name = "email_user")
//    private String emailUser;

//    private String password;

    private String ip;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventInLog> eventsInLog = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


//    public String getLoginUser() {
//        return loginUser;
//    }
//
//    public void setLoginUser(String loginUser) {
//        this.loginUser = loginUser;
//    }
//
//    public String getEmailUser() {
//        return emailUser;
//    }
//
//    public void setEmailUser(String emailUser) {
//        this.emailUser = emailUser;
//    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public List<EventInLog> getEventsInLog() {
        return eventsInLog;
    }

    public void setEventsInLog(List<EventInLog> eventsInLog) {
        this.eventsInLog = eventsInLog;
    }
}
