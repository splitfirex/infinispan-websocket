package app.components.cache;

import app.components.controllers.MessageSenderController;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Listener(clustered = true)
@Component("clusterListener")
public class ClusterListener {

    @Autowired
    MessageSenderController messageSenderController;

    @ViewChanged
    public void viewChanged(ViewChangedEvent event) {
        System.out.printf("---- View changed: %s ----\n", event.getNewMembers());
        messageSenderController.publishUpdates("New node entered");
    }

}
