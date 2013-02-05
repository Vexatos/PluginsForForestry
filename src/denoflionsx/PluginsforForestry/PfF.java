package denoflionsx.PluginsforForestry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.Core.PfFCore;
import denoflionsx.PluginsforForestry.Events.PfFEventManager;
import denoflionsx.PluginsforForestry.Managers.*;
import denoflionsx.PluginsforForestry.Proxy.PfFProxy;
import denoflionsx.PluginsforForestry.Version.PfFVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */
@Mod(modid = PfFVersion.name, name = PfFVersion.desc, version = PfFVersion.version, dependencies = PfFVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfF extends PfFModule {

    @Mod.Instance(PfFVersion.name)
    public static Object instance;
    @SidedProxy(clientSide = PfFVersion.ProxyClient, serverSide = PfFVersion.ProxyServer)
    public static PfFProxy Proxy;
    @Core
    public static PfFCore Core;

    public PfF() {
        Core = new PfFCore();
        PfFManagers.Events.SystemEvents = new PfFEventManager();
        PfFManagers.Items = new PfFItemManager();
        PfFManagers.LiquidVacuum = new PfFLiquidVacuumManager();
        PfFManagers.Liquids = new PfFLiquidManager();
        PfFManagers.Squeeze = new PfFSqueezableManager();
    }

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Core.preloadTextures();
    }

    @Override
    @Mod.Init
    public void load(FMLInitializationEvent event) {
        Core.setupConfig();
        PfFManagers.OmniPlant = new PfFOmniPlantManager();
        Core.setupItems();
        Core.setupBlocks();
    }

    @Override
    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        Core.lateCode();
    }

    @ForgeSubscribe
    public void finalSave(WorldEvent.Unload evt) {
        Core.config.save();
    }

    @Override
    public String targetMod() {
        return "";
    }
}
