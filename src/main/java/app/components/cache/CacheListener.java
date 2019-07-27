package app.components.cache;

import app.components.controllers.MessageSenderController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Listener(clustered = true)
@Component("cacheListener")
public class CacheListener {

    @Autowired
    MessageSenderController messageSenderController;
    ObjectMapper Obj = new ObjectMapper();


    @CacheEntryCreated
    public void entryCreated(CacheEntryCreatedEvent<String, Object> event) throws JsonProcessingException {
        messageSenderController.publishCreates(Obj.writeValueAsString(event.getValue()));
    }

    @CacheEntryModified
    public void entryModified(CacheEntryModifiedEvent<String, Object> event) throws JsonProcessingException {
        messageSenderController.publishUpdates(Obj.writeValueAsString(event.getValue()));
    }
}
