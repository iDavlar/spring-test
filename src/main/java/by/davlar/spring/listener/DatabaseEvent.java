package by.davlar.spring.listener;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.function.Consumer;
import java.util.function.Function;

@ToString
@Getter
public class DatabaseEvent extends ApplicationEvent {

    private final AccessType accessType;
    private final Function<Object, Boolean> function;

    public DatabaseEvent(Object source, AccessType accessType, Function<Object, Boolean> function) {
        super(source);
        this.accessType = accessType;
        this.function = function;
    }

    public boolean isImportant() {
        return function.apply(this.source);
    }
}
