package denoflionsx.core;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Interfaces.IPfFModule;
import denoflionsx.API.Interfaces.IPfFPlugin;
import denoflionsx.API.PfFEvents;
import denoflionsx.denLib.Config.Config;

public class IPfFModuleTemplate extends IPfFPluginTemplate implements IPfFModule {

    private IPfFPlugin Parent = null;

    public IPfFModuleTemplate(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        // This always needs to be overridden to prevent rogue config files.
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        if (this.Parent == null && event.getPlugin().getName().equals(this.getParentName())) {
            this.Parent = event.getPlugin();
            this.register();
        }
    }

    @Override
    public IPfFPlugin getParent() {
        return Parent;
    }

    @Override
    public Object configAccess() {
        return this.Parent.configAccess();
    }

    @Override
    public void register() {
        // Overriding this so pluginLoaded doesn't fire for modules.
        if (!this.isLoaded()) {
            PfFEvents.itemInitialized.register(this);
            this.setLoadedState(this.init());
            if (isLoaded()) {
                this.defaults();
                this.doSetup();
                this.recipes();
                PfFEvents.moduleLoaded.notifyListeners(this);
            }
        }
    }

    @Override
    public boolean init() {
        // Get access to parent config through interface.
        config = (Config) this.Parent.configAccess();
        config.addDefault(this.getName() + "_Enabled=" + "true");
        if (!config.getOptionBool(this.getName() + "_Enabled")) {
            return false;
        }
        return true;
    }
}
