package denoflionsx;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import denoflionsx.Proxy.Proxy;
import denoflionsx.core.PfFGUIHandler;
import denoflionsx.core.core;

@Mod(modid = "mod_PluginsforForestry", name = "Plugins for Forestry", version = "1.3Dev")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class mod_PluginsforForestry {

    /*
     * This program is free software. It comes without any warranty, to the
     * extent permitted by applicable law. You can redistribute it and/or modify
     * it under the terms of the Do What The Fuck You Want To Public License,
     * Version 2, as published by Sam Hocevar. See
     * http://sam.zoy.org/wtfpl/COPYING for more details.
     */
    public static final String texture = "/denoflionsx/spritesheet.png";
    public static boolean hasPluginsLoaded = false;
    public static int count = 0;
    public static mod_PluginsforForestry instance;
    @SidedProxy(clientSide = "denoflionsx.Proxy.ProxyClient", serverSide = "denoflionsx.Proxy.ProxyServer")
    public static Proxy proxy;

    public mod_PluginsforForestry() {
        instance = this;
    }

    @Init
    public void load(FMLInitializationEvent event) {
        core.runCoreFunctions();
        NetworkRegistry.instance().registerGuiHandler(this, new PfFGUIHandler());
    }

    @PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        core.registerEarlyPlugins();
    }

    public String getPriorities() {
        return "after:*";
    }

    public int addFuel(int id, int metadata) {
        return core.addFuel(id, metadata);
    }

    public String getVersion() {
        return core.modVersion();
    }
}