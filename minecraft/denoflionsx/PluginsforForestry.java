package denoflionsx;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.Proxy.Proxy;
import denoflionsx.Version.PfFVersion;
import denoflionsx.core.core;

@Mod(modid = "mod_PluginsforForestry", name = "Plugins for Forestry", version = PfFVersion.version, dependencies = "required-after:Forestry;required-after:denLib")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PluginsforForestry{

    /*
     * This program is free software. It comes without any warranty, to the
     * extent permitted by applicable law. You can redistribute it and/or modify
     * it under the terms of the Do What The Fuck You Want To Public License,
     * Version 2, as published by Sam Hocevar. See
     * http://sam.zoy.org/wtfpl/COPYING for more details.
     */
    public static final String texture = "/denoflionsx/spritesheet.png";
    @Instance("PluginsforForestry")
    public static PluginsforForestry instance;
    @SidedProxy(clientSide = "denoflionsx.Proxy.ProxyClient", serverSide = "denoflionsx.Proxy.ProxyServer")
    public static Proxy proxy;

    public PluginsforForestry() {
        instance = this;
    }

    @PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        core.PfFCore.PreLoad();
    }

    @Init
    public void load(FMLInitializationEvent event) {
        core.PfFCore.runCoreFunctions();
    }

    @PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        core.PfFCore.loadPlugins();
        core.print("-----------------------------");
        core.print("IGNORE THE FOLLOWING WARNING:");
        core.PfFCore.registerGUIHandler(this, core.PfFCore.Handlers.GUI);
        core.print("-----------------------------");
    }
}