package denoflionsx.PluginsforForestry.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Dictionary.PfFDictionaryParser;
import denoflionsx.PluginsforForestry.Dictionary.PfFReflectionParser;
import denoflionsx.PluginsforForestry.EventHandler.DictionaryPrint;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Managers.PfFPluginManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Proxy.PfFProxy;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.lang.LangManager;
import denoflionsx.denLib.Updater.Annotations.DenMod;

@Mod(modid = "PluginsforForestry", name = "PluginsforForestry", version = "@VERSION@")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
@DenMod(name = "PluginsforForestry", version = "@VERSION@", url = "Not done yet")
public class PfF {

    @Mod.Instance("PluginsforForestry")
    public static Object instance;
    private static final String proxyPath = "denoflionsx.PluginsforForestry.Proxy.";
    @SidedProxy(clientSide = proxyPath + "PfFProxyClient", serverSide = proxyPath + "PfFProxyCommon")
    public static PfFProxy Proxy;
    public static PfFPluginManager plugins;
    public static PfFCore core;
    public static LangManager lang;
    //----
    private boolean debug = true;

    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        core = new PfFCore();
        lang = new LangManager("PluginsforForestry", event.getModConfigurationDirectory());
        core.setupConfig(event);
        PfFTranslator.createInstance();
        PfFDictionaryParser.createInstance();
        PfFReflectionParser.createInstance();
        if (debug) {
            WorldEventHandler.registerHandler(new DictionaryPrint());
        }
        plugins = new PfFPluginManager();
        plugins.registerPlugin(new PluginLR());
        plugins.runPluginLoadEvent(event);
    }

    @Mod.Init
    public void load(FMLInitializationEvent event) {
        core.setupLocalization();
        core.setupContainers();
        core.setupRendering();
        plugins.runPluginLoadEvent(event);
    }

    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        plugins.runPluginLoadEvent(evt);
    }
}
