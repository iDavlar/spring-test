package by.davlar.spring.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseListener {
    @EventListener
    public void acceptEntity(DatabaseEvent databaseEvent) {
        if (databaseEvent.isImportant()) {
            System.out.println(databaseEvent);
        }
    }
}
