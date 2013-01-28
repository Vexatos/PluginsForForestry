package denoflionsx.PluginsforForestry_PluginBuildCraft.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;

public class BuildCraftTuning {

    public static boolean tuning_enabled = false;

    static {
        CoreTuning.tuning.registerTunable(BuildCraftTuning.class);
    }

    public static class Items {

        @Tunable(category = "buildcraft.items")
        public static int BackpackT1_ItemID = 7355;
        @Tunable(category = "buildcraft.items")
        public static int BackpackT2_ItemID = 7356;
    }

    public static class Enables {

        @Tunable(category = "buildcraft.enables")
        public static boolean Fuel_InBiogas = true;
        @Tunable(category = "buildcraft.enables")
        public static boolean HeavyWater_Enabled = true;
        @Tunable(category = "buildcraft.enables")
        public static boolean Backpacks_Enabled = true;
        @Tunable(category = "buildcraft.enables")
        public static boolean Fuel_CreateInStill = true;
    }

    public static class Fuels {

        @Tunable(category = "buildcraft.fuels")
        public static int Fuel_MJt = 6;
        @Tunable(category = "buildcraft.fuels")
        public static int Fuel_BurnTime = 100000 - (int) (100000 * 0.2);
    }

    public static class Coolants {

        @Tunable(category = "buildcraft.coolants")
        public static int HeavyWater_Coolant = 10;
    }
}
