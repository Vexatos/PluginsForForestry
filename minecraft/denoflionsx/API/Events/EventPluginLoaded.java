package denoflionsx.API.Events;

import denoflionsx.API.Interfaces.IPfFPlugin;
import java.util.EventObject;

public class EventPluginLoaded extends EventObject{
    
    private IPfFPlugin l;

    public EventPluginLoaded(Object source, IPfFPlugin l) {
        super(source);
        this.l = l;
    }

    public IPfFPlugin getPlugin() {
        return l;
    }

}
