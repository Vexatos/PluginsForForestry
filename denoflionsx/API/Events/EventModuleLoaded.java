package denoflionsx.API.Events;

import denoflionsx.API.Interfaces.IPfFModule;
import java.util.EventObject;

public class EventModuleLoaded extends EventObject{
    
    private IPfFModule m;

    public EventModuleLoaded(Object source) {
        super(source);
    }

    public IPfFModule getModule() {
        return m;
    }
}
