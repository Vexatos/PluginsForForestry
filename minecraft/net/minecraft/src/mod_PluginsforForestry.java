package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.denoflionsx.core.BetaQuotes;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.BluesFood.MachineOven;
import net.minecraft.src.forge.IGuiHandler;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

public class mod_PluginsforForestry extends NetworkMod implements IGuiHandler {

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
    public static BaseMod instance;

    public mod_PluginsforForestry() {
        ModLoader.setInGameHook(this, true, true);
        instance = this;
        MinecraftForge.setGuiHandler(this, this);
    }

    @Override
    public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 1) {
            return new MachineOven.GUIOven(player, (MachineOven.TileEntityOven) world.getBlockTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public void load() {
        core.runCoreFunctions();
    }

    @Override
    public void modsLoaded() {
        core.registerEarlyPlugins();
    }

    @Override
    public String getPriorities() {
        return "after:*";
    }

    @Override
    public int addFuel(int id, int metadata) {
        return core.addFuel(id, metadata);
    }

    @Override
    public boolean onTickInGame(float var1, Minecraft var2) {
        if (!hasPluginsLoaded && count > core.delay) {
            core.registerLatePlugins();
            hasPluginsLoaded = true;
            if (core.isBetaBuild) {
                BetaQuotes.setup();
                core.print(BetaQuotes.getRandomQuote());
            }
            count = 0;
        } else {
            count++;
        }
        return true;
    }

    @Override
    public String getVersion() {
        return core.modVersion();
    }
}