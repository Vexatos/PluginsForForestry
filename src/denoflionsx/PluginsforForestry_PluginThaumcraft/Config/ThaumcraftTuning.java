package denoflionsx.PluginsforForestry_PluginThaumcraft.Config;

import denoflionsx.PluginsforForestry.API.Annotations.DepricatedTunable;
import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;

public class ThaumcraftTuning {

    public static boolean tuning_enabled = false;

    static {
        CoreTuning.tuning.registerTunable(ThaumcraftTuning.class);
    }
    
    public static class Enables{
        
        @DepricatedTunable(category = "thaumcraft.enables")
        public static boolean AuraCollector_Enabled = false;
        @Tunable(category = "thaumcraft.enables")
        public static boolean Backpacks_Enabled = true;
        
    }

    public static class Blocks {

        @DepricatedTunable(category = "thaumcraft.blocks")
        public static int auracollector = 3334;
    }

    public static class Items {

        @DepricatedTunable(category = "thaumcraft.items")
        public static int testauradrain = 7340;
        @Tunable(category = "thaumcraft.items")
        public static int BackpackT1_ItemID = 7365;
        @Tunable(category = "thaumcraft.items")
        public static int BackpackT2_ItemID = 7366;
    }

    public static class Fuels {

        @DepricatedTunable(category = "thaumcraft.fuels")
        public static int LiquidAura_MJt = 1;
        @DepricatedTunable(category = "thaumcraft.fuels")
        public static int LiquidAura_BurnTime = 40000;
    }
}
