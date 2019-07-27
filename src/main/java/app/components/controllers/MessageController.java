package app.components.controllers;

import app.components.messages.CompoundMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import app.components.cache.LocalCacheManager;


@Controller("messageController")
public class MessageController {

    @Autowired
    LocalCacheManager localCacheManager;
    ObjectMapper Obj = new ObjectMapper();

    @Autowired
    MessageSenderController messageSenderController;

    @MessageMapping("/send")
    public void sendMessage(CompoundMessage message) {
        localCacheManager.getCache().put(message.getUuid(), message);
    }

    @MessageMapping("/connect")
    @SendTo("/listeners/connected")
    public String initCluster(CompoundMessage message) throws Exception {
        if (!localCacheManager.getCacheCreated()) {
            localCacheManager.initCache(message.getContent());
            localCacheManager.getCache().put(message.getUuid(), message);
        }
        return Obj.writeValueAsString(localCacheManager.getCache().entrySet());
    }


}
