package net.minecraft.server.denoflionsx.plugins.Buildcraft;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.Item;

public class goldGear
{
    public static Item goldGear;

    public static void setup()
    {
        if (!core.isBukkit)
        {
            goldGear = denLib.getItem("BuildCraftCore", "goldGearItem");
        }
        else
        {
            goldGear = denLib.getItem("net.minecraft.server.BuildCraftCore", "goldGearItem");
        }
    }
}
