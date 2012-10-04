package denoflionsx.API.Events;

import denoflionsx.API.Interfaces.IPfFModule;
import java.util.EventObject;

public class EventModuleLoaded extends EventObject{
    
    private IPfFModule m;

    public EventModuleLoaded(Object source, IPfFModule m) {
        super(source);
        this.m = m;
    }

    public IPfFModule getModule() {
        return m;
    }
}
