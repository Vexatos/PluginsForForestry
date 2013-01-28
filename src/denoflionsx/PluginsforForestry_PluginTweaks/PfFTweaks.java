package denoflionsx.PluginsforForestry_PluginTweaks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.PluginsforForestry.Annotations.Core;
import denoflionsx.PluginsforForestry.PfFModule;
import denoflionsx.PluginsforForestry_PluginTweaks.Core.PfFTweakCore;
import denoflionsx.PluginsforForestry_PluginTweaks.Proxy.PfFTweakProxy;
import denoflionsx.PluginsforForestry_PluginTweaks.Version.PfFTweakVersion;

/*
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2, as
 * published by Sam Hocevar. See http://sam.zoy.org/wtfpl/COPYING for more
 * details.
 */
@Mod(modid = PfFTweakVersion.name, name = PfFTweakVersion.name, version = PfFTweakVersion.version, dependencies = PfFTweakVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class PfFTweaks extends PfFModule {

    @Mod.Instance(PfFTweakVersion.name)
    public static Object instance;
    @SidedProxy(clientSide = PfFTweakVersion.ProxyClient, serverSide = PfFTweakVersion.ProxyServer)
    public static PfFTweakProxy Proxy;
    @Core
    public static PfFTweakCore Core;

    public PfFTweaks() {
        Core = new PfFTweakCore();
    }

    @Override
    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        Core.preloadTextures();
        Core.setupConfig();
    }

    @Override
    @Mod.Init
    public void load(FMLInitializationEvent event) {
        Core.setupBlocks();
    }

    @Override
    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        Core.setupItems();
    }

    @Override
    public String targetMod() {
        return "";
    }
}
