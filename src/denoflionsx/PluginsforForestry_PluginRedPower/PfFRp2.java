package denoflionsx.PluginsforForestry_PluginRedPower;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginRedPower.Core.PfFRp2Core;
import denoflionsx.PluginsforForestry_PluginRedPower.Version.PfFRp2Version;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */
@Mod(modid = PfFRp2Version.name, name = PfFRp2Version.name, version = PfFRp2Version.version, dependencies = PfFRp2Version.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFRp2 extends PfFModule {

    @Mod.Instance(PfFRp2Version.name)
    public static Object instance;
    @Core
    public static PfFRp2Core Core;
    private static final boolean debug = false;
    public static boolean load = false;

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        if (this.canLoad()) {
            Core = new PfFRp2Core();
            Core.preloadTextures();
            load = this.canLoad();
        }
    }

    @Override
    @Mod.Init
    public void load(FMLInitializationEvent event) {
        if (this.canLoad()) {
            Core.setupConfig();
            Core.setupItems();
            Core.setupBlocks();
        }
    }

    @Override
    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        if (this.canLoad()) {
            if (debug) {
                PfF.Proxy.print("Redpower 2 plugin loaded!");
            }
        }
    }

    @Override
    public String targetMod() {
        return "RedPowerWorld";
    }
}
