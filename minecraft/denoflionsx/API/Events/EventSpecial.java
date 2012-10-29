package denoflionsx.API.Events;

import java.util.EventObject;

public class EventSpecial extends EventObject{
    
    // This event is for certain things that you might want to know about
    // without having to scan through all the item events.
    
    // See: EnumEventSpecialMessages.
    
    private String message;

    public EventSpecial(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
