package denoflionsx.API.Events;

import java.util.EventObject;

public class EventSpecial extends EventObject{
    
    // This event is for certain things that you might want to know about
    // without having to scan through all the item events.
    
    // POSSIBLE MESSAGES: Its a barrel!, Its a bucket!, Its a cast!
    // These messages are for when each type of container is ready to hold mod liquids.
    
    private String message;

    public EventSpecial(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
