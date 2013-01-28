package denoflionsx.PluginsforForestry_PluginIc2;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginIc2.Core.PfFIc2Core;
import denoflionsx.PluginsforForestry_PluginIc2.Version.PfFIc2Version;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */
@Mod(modid = PfFIc2Version.name, name = PfFIc2Version.name, version = PfFIc2Version.version, dependencies = PfFIc2Version.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFIc2 extends PfFModule {

    @Mod.Instance(PfFIc2Version.name)
    public static Object instance;
    @Core
    public static PfFIc2Core Core;

    public PfFIc2() {
        Core = new PfFIc2Core();
    }

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        if (this.canLoad()) {
            Core.preloadTextures();
        }
    }

    @Override
    @Mod.Init
    public void load(FMLInitializationEvent event) {
        if (this.canLoad()) {
            Core.setupBlocks();
            Core.setupItems();
        }
    }

    @Override
    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        if (this.canLoad()) {
            Core.lateCode();
        }
    }

    @Override
    public String targetMod() {
        return "IC2";
    }
}
