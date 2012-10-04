package denoflionsx.core;

import denoflionsx.API.Events.*;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.Interfaces.IPfFPlugin;
import denoflionsx.Interfaces.IWorldLoaded;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;

public class IPfFPluginTemplate implements IPfFPlugin, IPluginListener, IModuleListener, IItemListener, IWorldLoaded {

    private String name;
    private boolean isLoaded = false;
    private String parent;
    public Config config;

    public IPfFPluginTemplate(String name, String parent) {
        this.name = name;
        this.parent = parent;
        this.config = new Config(name + ".cfg");
        PfFEvents.pluginLoaded.register(this);
        PfFEvents.moduleLoaded.register(this);
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        
    }
   

    @Override
    public void moduleLoaded(EventModuleLoaded event) {
    }

    // This hooks into a ForgeSubscribe for world load. Use it for really late things.
    @Override
    public void onWorldLoaded() {
        this.config.writeConfig();
    }

    @Override
    public void itemInitialized(EventItemInitialized event) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getParentName() {
        return this.parent;
    }

    @Override
    public void defaults() {
        if (this.config.doesConfigExist()) {
            this.config.readFile();
        }
    }

    @Override
    public void doSetup() {
        // Do plugin main code here.
    }

    @Override
    public Object configAccess() {
        return config;
    }

    @Override
    public boolean init() {
        if (!denLib.detect(this.parent)) {
            return false;
        }
        core.PfFCore.config.addDefault(this.name + "_Enabled=true");
        if (!core.PfFCore.config.getOptionBool(this.name + "_Enabled")) {
            return false;
        }
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
    public void setLoadedState(boolean state) {
        this.isLoaded = state;
    }

    @Override
    public void register() {
        if (!isLoaded()) {
            PfFEvents.itemInitialized.register(this);
            core.PfFCore.Handlers.World.listeners.add(this);
            setLoadedState(this.init());
            if (isLoaded()) {
                this.defaults();
                this.doSetup();
                this.recipes();
                PfFEvents.pluginLoaded.notifyListeners(this);
            }
        }
    }
}
