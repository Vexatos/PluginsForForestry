package denoflionsx.PluginsforForestry.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.EventHandler.ChangelogHandler;
import denoflionsx.PluginsforForestry.EventHandler.LiquidDictionaryDebug;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Managers.PfFPluginManager;
import denoflionsx.PluginsforForestry.Proxy.PfFProxy;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.denLibMod;

@Mod(modid = "PluginsforForestry", name = "PluginsforForestry", version = "@VERSION@")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfF {

    @Mod.Instance("PluginsforForestry")
    public static Object instance;
    private static final String proxyPath = "denoflionsx.PluginsforForestry.Proxy.";
    @SidedProxy(clientSide = proxyPath + "PfFProxyClient", serverSide = proxyPath + "PfFProxyCommon")
    public static PfFProxy Proxy;
    public static PfFCore core;
    private final boolean debug = false;

    public PfF() {
        PfFAPI.plugins = new PfFPluginManager();
    }

    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        denLibMod.Proxy.registerForgeSubscribe(this);
        PfFAPI.instance = this;
        core = new PfFCore(event.getSourceFile());
        core.setupConfig(event);
        PfFTranslator.createInstance();
        PluginRegistry.register();
        PfFAPI.plugins.runPluginLoadEvent(event);
        core.registerWithUpdater();
        if (debug){
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
        WorldEventHandler.registerHandler(new ChangelogHandler());
        PfF.Proxy.print("This is PfF version " + "@VERSION@");
    }
}
