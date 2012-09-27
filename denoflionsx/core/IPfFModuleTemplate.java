package denoflionsx.core;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Interfaces.IPfFModule;
import denoflionsx.API.Interfaces.IPfFPlugin;
import denoflionsx.API.PfFEvents;

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
            PfFEvents.pluginLoaded.register(this);
            PfFEvents.moduleLoaded.register(this);
            PfFEvents.itemInitialized.register(this);
            this.init();
            this.recipes();
            this.setLoadedState(true);
            PfFEvents.moduleLoaded.notifyListeners(this);
        }
    }
}
