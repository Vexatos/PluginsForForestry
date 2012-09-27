package denoflionsx.Beta;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.core.IPfFPluginTemplate;
import denoflionsx.core.core;

public class pluginTest extends IPfFPluginTemplate{

    public pluginTest(String name, String parent) {
        super(name, parent);
    }
    
    @Override
    public boolean init() {
        core.print("THIS CODE IS ACTUALLY LOADING!");
        return super.init();
    }

    @Override
    public void defaults() {
        super.defaults();
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        super.pluginLoaded(event);
    }

    @Override
    public void recipes() {
        super.recipes();
    }
}
