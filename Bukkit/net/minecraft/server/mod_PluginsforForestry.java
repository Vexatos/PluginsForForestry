package net.minecraft.server;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.MinecraftServer;

public class mod_PluginsforForestry extends BaseMod
{
    public static final String texture = "/denoflionsx/spritesheet.png";
    public static boolean hasPluginsLoaded = false;
    public static int count = 0;

    public mod_PluginsforForestry()
    {
        ModLoader.setInGameHook(this, true, true);
    }

    public void load()
    {
        core.runCoreFunctions();
    }

    public void modsLoaded()
    {
        core.registerEarlyPlugins();
    }

    public String getPriorities()
    {
        return "after:*";
    }

    public boolean onTickInGame(MinecraftServer var1)
    {
        if (!hasPluginsLoaded && count > 25)
        {
            core.registerLatePlugins();
            hasPluginsLoaded = true;

            if (core.isBetaBuild)
            {
                core.print("Cave Johnson: Just a heads up, we\'re gonna have a super conductor turned up full blast and pointed at you for the duration of this next test. I\'ll be honest, we\'re throwing science at the walls here to see what sticks. No idea what it\'ll do.");
            }

            count = 0;
        }
        else
        {
            ++count;
        }

        return true;
    }

    public String getVersion()
    {
        return core.modVersion();
    }
}
