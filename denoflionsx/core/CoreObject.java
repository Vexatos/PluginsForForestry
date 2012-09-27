package denoflionsx.core;

import denoflionsx.API.Events.*;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.TextureManager;
import denoflionsx.Events.PfFEventCore;
import denoflionsx.Handlers.HandlerInstances;
import denoflionsx.Managers.*;
import denoflionsx.PluginsforForestry;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;
import denoflionsx.items.Containers.Containers;
import denoflionsx.items.Containers.InfusionBar;
import denoflionsx.items.CraftingTools.ItemBlacksmithHammer;
import denoflionsx.items.CraftingTools.ItemIronRing;

public class CoreObject implements IPluginListener{

    public HandlerInstances Handlers;
    public PfFEventCore Events;
    public Config config;

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        core.print(event.getPlugin().getName() + " is loaded!");
    }
 
    public void runCoreFunctions() {
        if (denLib.buildnumber < 4) {
            throw new RuntimeException("denLib is out of date. Please update!");
        }
        setupManagers();
        PfFEvents.pluginLoaded.register(this);
        this.translateColors();
        Config.ConfigDir = PluginsforForestry.proxy.getConfigDir();
        config = new Config("PluginsforForestry.cfg");
        TextureManager.Preload();
        defaults.setup();
        config.readFile();
        Config.verbose = denLib.convertToBoolean(config.getOption("Verbose"));
    }

    public void setupUniversalItems() {
        Containers.Container.register();
        ItemBlacksmithHammer.BlacksmithHammer();
        ItemIronRing.IronRing();
        InfusionBar.recipe();
    }

    public void PreLoad() {
        Handlers = new HandlerInstances();
        Events = new PfFEventCore().init();
    }

    public void setupManagers() {
        PfFManagers.ColorManager = new PfFColorManager();
        PfFManagers.ItemManager = new PfFItemManager();
        PfFManagers.ExtractorTargetManager = new PfFExtractorTargetManager();
        PfFManagers.ContainerManager = new PfFContainerManager();
        PfFManagers.ButcherKnifeManager = new PfFButcherKnifeManager();
        PfFManagers.FermenterManager = new PfFFermenterManager();
    }

    public void translateColors() {
        for (Colors.Values v : Colors.Values.values()) {
            PfFManagers.ColorManager.addColor(v.toString(), v.getR(), v.getG(), v.getB());
        }
    }
}
