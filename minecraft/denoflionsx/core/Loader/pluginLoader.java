package denoflionsx.core.Loader;

import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.items.Containers.Containers;
import denoflionsx.items.Containers.InfusionBar;
import denoflionsx.items.CraftingTools.ItemBlacksmithHammer;
import denoflionsx.items.CraftingTools.ItemIronRing;

public class pluginLoader extends PfFPluginTemplate {
    
    // This really isn't a plugin.
    // It is for triggering the first event that makes the event load system work.
    // I also moved the Universal item setup calls in here.

    public pluginLoader(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void onWorldLoaded() {
        // Overriding this to prevent a useless config file from generating.
    }

    @Override
    public void itemInitialized(EventItemInitialized event) {
        if (event.getName().equals("infusionbar")) {
            InfusionBar.recipe();
        }
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
    }

    @Override
    public void doSetup() {
        Containers.Container.register();
        ItemBlacksmithHammer.BlacksmithHammer();
        ItemIronRing.IronRing();
    }

    @Override
    public boolean init() {
        // Loader cannot be disabled.
        return true;
    }
}
