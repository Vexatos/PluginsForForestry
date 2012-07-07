package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.denoflionsx.core.core;

public class mod_PluginsforForestry extends BaseMod {

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

    public mod_PluginsforForestry() {
        ModLoader.setInGameHook(this, true, true);
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
    public boolean onTickInGame(float var1, Minecraft var2) {
        if (!hasPluginsLoaded && count > core.delay) {
            core.registerLatePlugins();
            hasPluginsLoaded = true;
            if (core.isBetaBuild) {
                core.print("Cave Johnson: Just a heads up, we're gonna have a super conductor turned up full blast and pointed at you for the duration of this next test. I'll be honest, we're throwing science at the walls here to see what sticks. No idea what it'll do.");
            }
            count = 0;
        }else{
            count++;
        }
        return true;
    }

    @Override
    public String getVersion() {
        return core.modVersion();
    }
}