package denoflionsx.PluginsforForestry_PluginThaumcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginThaumcraft.Core.PfFThaumCore;
import denoflionsx.PluginsforForestry_PluginThaumcraft.Proxy.PfFThaumProxy;
import denoflionsx.PluginsforForestry_PluginThaumcraft.Version.PfFThaumVersion;
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
@Mod(modid = PfFThaumVersion.name, name = PfFThaumVersion.desc, version = PfFThaumVersion.version, dependencies = PfFThaumVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFThaum extends PfFModule {

    @Mod.Instance(PfFThaumVersion.name)
    public static Object instance;
    @SidedProxy(clientSide = PfFThaumVersion.ProxyClient, serverSide = PfFThaumVersion.ProxyServer)
    public static PfFThaumProxy Proxy;
    @Core
    public static PfFThaumCore Core;

    public PfFThaum() {
        Core = new PfFThaumCore();
    }

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        if (this.canLoad()) {
            Core.preloadTextures();
            Core.setupConfig();
            MinecraftForge.EVENT_BUS.register(this);
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
    public static boolean WorldLoaded = false;

    @ForgeSubscribe
    public void onWorldLoaded(WorldEvent.Load event) {
        if (!WorldLoaded) {
            if (this.canLoad()) {
                try {
                    MinecraftForge.EVENT_BUS.unregister(this);
                } catch (Exception ex) {
                }
            }
            WorldLoaded = true;
        }
    }

    @Override
    public String targetMod() {
        return "Thaumcraft";
    }
}
