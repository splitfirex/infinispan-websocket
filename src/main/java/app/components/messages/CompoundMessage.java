package app.components.messages;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

public class CompoundMessage implements Serializable {

    private String uuid;
    private String cClass;
    private String content;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getcClass() {
        return cClass;
    }

    public void setcClass(String cClass) {
        this.cClass = cClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
