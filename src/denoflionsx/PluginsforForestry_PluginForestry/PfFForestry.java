package denoflionsx.PluginsforForestry_PluginForestry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginForestry.Core.PfFForestryCore;
import denoflionsx.PluginsforForestry_PluginForestry.Version.PfFForestryVersion;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */
@Mod(modid = PfFForestryVersion.name, name = PfFForestryVersion.desc, version = PfFForestryVersion.version, dependencies = PfFForestryVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFForestry extends PfFModule {

    @Mod.Instance(PfFForestryVersion.name)
    public static Object instance;
    @Core
    public static PfFForestryCore Core;

    public PfFForestry() {
        Core = new PfFForestryCore();
    }

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        if (this.canLoad()) {
            Core.setupConfig();
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
        return "Forestry";
    }
}
