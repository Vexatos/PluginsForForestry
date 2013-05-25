package denoflionsx.PluginsforForestry.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Changelog.PfFChangelogHandler;
import denoflionsx.PluginsforForestry.EventHandler.LiquidDictionaryDebug;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Managers.PfFPluginManager;
import denoflionsx.PluginsforForestry.Proxy.PfFProxy;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.denLibMod;

@Mod(modid = "@NAME@", name = "@NAME@", version = "@VERSION@", dependencies = "@DEPENDS@")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfF {

    @Mod.Instance("@NAME@")
    public static Object instance;
    @SidedProxy(clientSide = "@PROXYCLIENT@", serverSide = "@PROXYSERVER@")
    public static PfFProxy Proxy;
    public static PfFCore core;
    public static final boolean debug = false;

    public PfF() {
        PfFAPI.plugins = new PfFPluginManager();
    }

    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        PfFAPI.instance = this;
        core = new PfFCore(event.getSourceFile());
        core.setupConfig(event);
        PfFTranslator.createInstance();
        Proxy.findInternalAddons(event.getSourceFile());
        PfFAPI.plugins.runPluginLoadEvent(event);
        core.registerWithUpdater();
        if (debug) {
            WorldEventHandler.registerHandler(new LiquidDictionaryDebug());
        }
    }

    @Mod.Init
    public void load(FMLInitializationEvent event) {
        core.setupContainers();
        PfFAPI.plugins.runPluginLoadEvent(event);
    }

    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        PfFAPI.plugins.runPluginLoadEvent(evt);
        core.setupRendering();
        PfF.Proxy.registerAllRecipes();
        PfF.Proxy.setTabs();
        denLibMod.Proxy.registerChangelogHandler(new PfFChangelogHandler());
        PfF.Proxy.print("This is PfF version " + "@VERSION@");
    }
}
