package app.components.messages.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Delta implements Serializable {
    private Set<String> changeLog = new HashSet<>();

    public void setChange(String parameter) {
        changeLog.add(parameter);
    }

    public Set<String> getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(Set<String> changeLog) {
        this.changeLog = changeLog;
    }
}
