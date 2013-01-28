package denoflionsx.PluginsforForestry_PluginIc2.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class Ic2Tuning {

    public static boolean tuning_enabled = false;

    static {
        CoreTuning.tuning.registerTunable(Ic2Tuning.class);
    }

    public static class Enables {

        @Tunable(category = "ic2.enables")
        public static boolean PortableReactor_Enabled = true;
        @Tunable(category = "ic2.enables")
        public static boolean RadioactiveWaste_Enabled = true;
        @Tunable(category = "ic2.enables")
        public static boolean Backpack_Enabled = true;
    }

    public static class Items {

        @Tunable(category = "ic2.items")
        public static int PortableReactor_ItemID = 7343;
        @Tunable(category = "ic2.items")
        public static int RadioactiveGoo_ItemID = 7344;
        @Tunable(category = "ic2.items")
        public static int BackpackT1_ItemID = 7359;
        @Tunable(category = "ic2.items")
        public static int BackpackT2_ItemID = 7360;
    }

    public static class Fuels {

        @Tunable(category = "ic2.fuels")
        public static int RadioactiveWaste_MJt = 8;
        @Tunable(category = "ic2.fuels")
        public static int RadioactiveWaste_BurnTime = ((60 * 60) * 20);
        @Tunable(category = "ic2.fuels")
        public static int RadioactiveWaste_AmountPerSqueeze = (int)(LiquidContainerRegistry.BUCKET_VOLUME);
        
    }
}
