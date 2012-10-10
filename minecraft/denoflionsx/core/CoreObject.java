package denoflionsx.core;

import cpw.mods.fml.common.network.IGuiHandler;
import denoflionsx.API.Events.*;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.Beta.BetaCore;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.TextureManager;
import denoflionsx.Events.PfFEventCore;
import denoflionsx.Handlers.HandlerInstances;
import denoflionsx.Interfaces.IWorldLoaded;
import denoflionsx.Managers.*;
import denoflionsx.PluginsforForestry;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;

public class CoreObject implements IWorldLoaded {

    public HandlerInstances Handlers;
    public PfFEventCore Events;
    public Config config;

    @PfFSubscribe(Event = PfFEventTypes.PLUGIN_LOADED)
    public void pluginLoaded(EventPluginLoaded event) {
        core.print(event.getPlugin().getName() + " is loaded!");
    }

    @PfFSubscribe(Event = PfFEventTypes.MODULE_LOADED)
    public void moduleLoaded(EventModuleLoaded event) {
        core.print(event.getModule().getParentName() + " | " + event.getModule().getName() + " is loaded!");
    }
    
    public void registerGUIHandler(Object instance, IGuiHandler handler){
        FMLWrapper.MODE.FML.registerGUIHandler(instance, handler);
    }

    @Override
    public void onWorldLoaded() {
        config.writeConfig();
        ItemIDManager.usedIDs.writeConfig();
    }

    public void loadPlugins() {
        PluginInstances.Loader.register();
        PluginsforForestry.proxy.registerFX();
        PfFManagers.FermenterManager.createRecipes();
        BetaCore.doStuff();
    }

    public void registerFX() {
        PluginsforForestry.proxy.registerFX();
    }

    public void runCoreFunctions() {
        if (denLib.buildnumber < 4) {
            throw new RuntimeException("denLib is out of date. Please update!");
        }
        setupManagers();
        PfFEvents.pluginLoaded.register(this);
        PfFEvents.moduleLoaded.register(this);
        PfFEvents.specialEvent.register(this);
        this.translateColors();
        Config.ConfigDir = PluginsforForestry.proxy.getConfigDir();
        config = new Config("PluginsforForestry.cfg");
        TextureManager.Preload();
        defaults.setup();
        if (config.doesConfigExist()) {
            config.readFile();
        }
        Handlers.World.listeners.add(this);
        PluginsforForestry.proxy.registerTileEntites();
        //Config.verbose = denLib.convertToBoolean(config.getOption("Verbose"));
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
