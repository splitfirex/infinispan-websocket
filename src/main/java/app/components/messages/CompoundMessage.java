package app.components.messages;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

public class CompoundMessage implements Serializable {

    private UUID uuid;
    private Class cClass;
    private String content;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Class getcClass() {
        return cClass;

    }

    public void setcClass(Class cClass) {
        this.cClass = cClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
