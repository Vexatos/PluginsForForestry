package denoflionsx.LiquidRoundup;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.LiquidRoundup.Core.LiquidRoundupCore;
import denoflionsx.LiquidRoundup.Managers.LRContainerManager;
import denoflionsx.LiquidRoundup.Managers.LRLiquidManager;
import denoflionsx.LiquidRoundup.Proxy.Proxy;
import denoflionsx.LiquidRoundup.Version.LRVersion;
import java.util.ArrayList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;

/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */

@Mod(modid = LRVersion.name, name = LRVersion.desc, version = LRVersion.version, dependencies = LRVersion.req)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class LiquidRoundup {

    @Mod.Instance(LRVersion.name)
    public static Object instance;
    @SidedProxy(clientSide = LRVersion.ProxyClient, serverSide = LRVersion.ProxyServer)
    public static Proxy Proxy;
    public static LiquidRoundupCore Core;
    public static final boolean debug = true;
    public static ArrayList<LiquidRegisterEvent> events = new ArrayList();

    public LiquidRoundup() {
        Core = new LiquidRoundupCore();
    }

    @ForgeSubscribe
    public void liquidEvent(LiquidRegisterEvent event) {
        events.add(event);
    }

    @ForgeSubscribe
    public void finalSave(WorldEvent.Unload evt) {
        Core.config.save();
    }

    @Mod.PreInit
    public void preLoad(FMLPreInitializationEvent event) {
        Core.setupConfig();
        LRManagers.Liquids = new LRLiquidManager();
        LRManagers.Containers = new LRContainerManager();
        MinecraftForge.EVENT_BUS.register(this);
        Core.preloadTextures();
        Core.setupBlocks();
        Core.setupItems();
    }

    @Mod.Init
    public void load(FMLInitializationEvent event) {
    }

    @Mod.PostInit
    public void modsLoaded(FMLPostInitializationEvent evt) {
        Core.lateCode();
        if (debug) {
            Core.debugCode();
        }
    }
}
