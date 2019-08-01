package app.components.controllers;

import app.components.messages.CompoundMessage;
import app.components.messages.core.MetaObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import app.components.cache.LocalCacheManager;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


@Controller("messageController")
public class MessageController {

    @Autowired
    LocalCacheManager localCacheManager;
    ObjectMapper Obj = new ObjectMapper();

    @Autowired
    MessageSenderController messageSenderController;

    ExecutorService executorService = Executors.newFixedThreadPool(8);

    @MessageMapping("/send")
    public void sendMessage(CompoundMessage message) throws IOException {

        IntStream.range(0, 20000).parallel().forEach(x -> {
            executorService.execute(() -> {
                System.out.println(String.format("starting expensive task thread %s", Thread.currentThread().getName()));
                try {
                    message.setUuid(UUID.randomUUID());
                    if (localCacheManager.getCache().containsKey(message.getUuid().toString())) {
                        Object currentObject = localCacheManager.getCache().get(message.getUuid().toString());
                        MetaObject castedObject = (MetaObject) currentObject;
                        ((MetaObject) currentObject).processChanges(Obj.readValue(message.getContent(), message.getcClass()));
                        if (castedObject.changed())
                            localCacheManager.getCache().put(message.getUuid().toString(), castedObject);
                    } else {
                        Object newObject = Obj.readValue(message.getContent(), message.getcClass());
                        MetaObject castedObject = (MetaObject) newObject;
                        castedObject.setUuid(message.getUuid());
                        castedObject.setClassName(message.getcClass());

                        localCacheManager.getCache().put(message.getUuid().toString(), castedObject);
                    }
                } catch (Exception ex) {

                }
            });
        });
    }

    @MessageMapping("/connect")
    @SendTo("/listeners/connected")
    public String initCluster(CompoundMessage message) throws Exception {
        if (!localCacheManager.getCacheCreated()) {
            localCacheManager.initCache(message.getContent());
            localCacheManager.getCache().put(message.getUuid().toString(), message);
        }
        return Obj.writeValueAsString(localCacheManager.getCache().entrySet());
    }


}
