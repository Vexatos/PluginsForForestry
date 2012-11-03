package denoflionsx.API.Events;

import java.util.EventObject;

public class EventFuelCreated extends EventObject {

    private EventFuelCreatedObject Fuel;

    public EventFuelCreated(Object source, EventFuelCreatedObject liquid) {
        super(source);
        this.Fuel = liquid;
    }

    public EventFuelCreatedObject getFuel() {
        return Fuel;
    }
}
