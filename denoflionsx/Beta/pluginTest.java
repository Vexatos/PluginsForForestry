package denoflionsx.Beta;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Events.EventPluginLoading;
import denoflionsx.core.IPluginTemplate;
import denoflionsx.core.core;

public class pluginTest extends IPluginTemplate{

    public pluginTest(String name, String parent) {
        super(name, parent);
    }
    
    @Override
    public boolean init() {
        core.print("THIS CODE IS ACTUALLY LOADING!");
        return super.init();
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        super.pluginLoaded(event);
    }

    @Override
    public void pluginLoading(EventPluginLoading event) {
        super.pluginLoading(event);
    }

    @Override
    public void recipes() {
        super.recipes();
    }
}
