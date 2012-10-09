package denoflionsx.API.Events;

import java.util.EventListener;

public interface IModuleListener extends EventListener{
    
    public void moduleLoaded(EventModuleLoaded event);
    
}
