package denoflionsx.PluginsforForestry_PluginBlueFood;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.Managers.PfFFoodManager;
import denoflionsx.PluginsforForestry.Managers.PfFLiquidVacuumManager;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginBlueFood.Core.PfFFoodCore;
import denoflionsx.PluginsforForestry_PluginBlueFood.Version.PfFFoodVersion;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */

@Mod(modid = PfFFoodVersion.name, name = PfFFoodVersion.name, version = PfFFoodVersion.version, dependencies = PfFFoodVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFFood extends PfFModule {

    @Mod.Instance(PfFFoodVersion.name)
    public static Object instance;
    @Core
    public static PfFFoodCore Core;
    
    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        PfFManagers.Foods = new PfFFoodManager();
        PfFManagers.ButcherKnife = new PfFLiquidVacuumManager();
        Core = new PfFFoodCore();
        Core.preloadTextures();
    }

    @Override
    @Mod.Init
    public void load(FMLInitializationEvent event) {
        Core.setupBlocks();
        Core.setupItems();
    }

    @Override
    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        Core.lateCode();
    }

    @Override
    public String targetMod() {
        return "";
    }
    
    
}
