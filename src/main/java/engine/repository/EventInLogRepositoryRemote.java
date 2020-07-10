package engine.repository;

import engine.domain.EventInLog;

import javax.ejb.Remote;
import java.util.List;
import java.util.Optional;

@Remote
public interface EventInLogRepositoryRemote {

    List<String> getUsersNames();

    String getEventInLogRecord(EventInLog recEventInLog);
}
