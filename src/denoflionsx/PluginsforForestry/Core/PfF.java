package denoflionsx.PluginsforForestry.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.EventHandler.DictionaryPrint;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Managers.PfFPluginManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRecipes.PluginLiquidRecipes;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Plugins.MFR.PluginMFR;
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
    public static PfFPluginManager plugins;
    public static PfFCore core;
    public static LangManager lang;
    private boolean debug = true;

    public void setupPlugins() {
        plugins = new PfFPluginManager();
        plugins.registerPlugin(new PluginLR());
        plugins.registerPlugin(new PluginLiquidRecipes());
        plugins.registerPlugin(new PluginMFR());
        core.setupLocalization();
    }

    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        core = new PfFCore(event.getSourceFile());
        lang = new LangManager("PluginsforForestry", event.getModConfigurationDirectory());
        core.setupConfig(event);
        PfFTranslator.createInstance();
        if (debug) {
            WorldEventHandler.registerHandler(new DictionaryPrint());
        }
        this.setupPlugins();
        plugins.runPluginLoadEvent(event);
        core.registerWithUpdater();
    }

    @Mod.Init
    public void load(FMLInitializationEvent event) {
        core.setupContainers();
        core.setupRendering();
        plugins.runPluginLoadEvent(event);
    }

    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        plugins.runPluginLoadEvent(evt);
    }
}
