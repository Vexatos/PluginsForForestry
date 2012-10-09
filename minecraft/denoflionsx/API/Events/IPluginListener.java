package denoflionsx.API.Events;

import java.util.EventListener;

public interface IPluginListener extends EventListener{

    public void pluginLoaded(EventPluginLoaded event);
    
}
