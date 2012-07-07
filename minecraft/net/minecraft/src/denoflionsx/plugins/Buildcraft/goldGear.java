package net.minecraft.src.denoflionsx.plugins.Buildcraft;

import net.minecraft.src.Item;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class goldGear {

    public static Item goldGear;

    public static void setup() {
        if (!core.isBukkit) {
            goldGear = denLib.getItem("BuildCraftCore", "goldGearItem");
        } else {
            goldGear = denLib.getItem("net.minecraft.server.BuildCraftCore", "goldGearItem");
        }
    }
}
