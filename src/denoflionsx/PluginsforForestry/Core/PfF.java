package denoflionsx.PluginsforForestry.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.EventHandler.DictionaryPrint;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Managers.PfFPluginManager;
import denoflionsx.PluginsforForestry.Proxy.PfFProxy;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.lang.LangManager;

@Mod(modid = "PluginsforForestry", name = "PluginsforForestry", version = "@VERSION@")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfF {

    @Mod.Instance("PluginsforForestry")
    public static Object instance;
    private static final String proxyPath = "denoflionsx.PluginsforForestry.Proxy.";
    @SidedProxy(clientSide = proxyPath + "PfFProxyClient", serverSide = proxyPath + "PfFProxyCommon")
    public static PfFProxy Proxy;
    public static PfFCore core;
    public static LangManager lang;
    private boolean debug = true;

    public PfF() {
        PfFAPI.plugins = new PfFPluginManager();
        
    }

    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        PfFAPI.instance = this;
        core = new PfFCore(event.getSourceFile());
        lang = new LangManager("PluginsforForestry", event.getModConfigurationDirectory());
        core.setupConfig(event);
        PfFTranslator.createInstance();
        if (debug) {
            WorldEventHandler.registerHandler(new DictionaryPrint());
        }
        PluginRegistry.register();
        PfFAPI.plugins.runPluginLoadEvent(event);
        core.registerWithUpdater();
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
    }
}
