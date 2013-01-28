package denoflionsx.PluginsforForestry_PluginBuildCraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginBuildCraft.Core.PfFBuildCraftCore;
import denoflionsx.PluginsforForestry_PluginBuildCraft.Version.PfFBuildCraftVersion;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */
@Mod(modid = PfFBuildCraftVersion.name, name = PfFBuildCraftVersion.name, version = PfFBuildCraftVersion.version, dependencies = PfFBuildCraftVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFBuildCraft extends PfFModule {

    @Mod.Instance(PfFBuildCraftVersion.name)
    public static Object instance;
    @Core
    public static PfFBuildCraftCore Core;
    public static boolean load = false;

    public PfFBuildCraft() {
    }

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        if (this.canLoad()) {
            load = this.canLoad();
            Core = new PfFBuildCraftCore();
            Core.preloadTextures();
            Core.setupConfig();
        }
    }

    @Override
    @Mod.Init
    public void load(FMLInitializationEvent event) {
        if (this.canLoad()) {
            Core.setupItems();
            Core.setupBlocks();
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
        return "BuildCraft|Core";
    }
}
