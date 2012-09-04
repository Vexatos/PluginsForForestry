package denoflionsx.plugins.Buildcraft;

import net.minecraft.src.Item;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;

public class goldGear {

    public static Item goldGear;

    public static void setup() {
        if (!core.isBukkit) {
            goldGear = denLib.getItem("buildcraft.BuildCraftCore", "goldGearItem");
        } else {
            goldGear = denLib.getItem("net.minecraft.server.BuildCraftCore", "goldGearItem");
        }
    }
}
