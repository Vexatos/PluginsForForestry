package denoflionsx.plugins.Buildcraft;

import denoflionsx.Enums.EnumModIDs;
import net.minecraft.src.Item;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;

public class goldGear {

    public static Item goldGear;

    public static void setup() {
        if (!core.isBukkit) {
            goldGear = denLib.getItem(EnumModIDs.MODS.BUILDCRAFT_CORE.gettheClass(), "goldGearItem");
        } else {
            goldGear = denLib.getItem("net.minecraft.server.BuildCraftCore", "goldGearItem");
        }
    }
}
