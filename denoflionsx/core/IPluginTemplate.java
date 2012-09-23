package denoflionsx.core;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Events.IPluginListener;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.Events.EventPluginLoading;
import denoflionsx.API.Interfaces.IPfFPlugin;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;

public class IPluginTemplate implements IPfFPlugin, IPluginListener {

    private String name;
    private boolean isLoaded = false;
    private String parent;
    private Config config;

    public IPluginTemplate(String name, String parent) {
        this.name = name;
        this.parent = parent;
        this.config = new Config(name + ".cfg");
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
    }

    @Override
    public void pluginLoading(EventPluginLoading event) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean init() {
        if (!denLib.detect(this.parent)) {
            return false;
        }
        if (!core.PfFCore.config.getOptionBool(this.name + "_Enabled")){
            return false;
        }
        PfFEvents.pluginLoading.notifyListeners(this);
        return true;
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void recipes() {
    }

    @Override
    public void register() {
        PfFEvents.pluginLoaded.register(this);
        PfFEvents.pluginLoading.register(this);
        this.init();
        this.recipes();
        isLoaded = true;
        PfFEvents.pluginLoaded.notifyListeners(this);
    }
}
