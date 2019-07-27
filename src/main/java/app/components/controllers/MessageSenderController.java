package app.components.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component("messageSenderController")
public class MessageSenderController {

    @Autowired
    private SimpMessagingTemplate template;

    public void publishUpdates(String message) {
        template.convertAndSend("/listeners/receive/updates", message);
    }

    public void publishCreates(String message) {
        template.convertAndSend("/listeners/receive/creates", message);
    }
}
