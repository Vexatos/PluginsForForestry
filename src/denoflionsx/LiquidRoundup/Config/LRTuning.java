package denoflionsx.LiquidRoundup.Config;

import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.API.Annotations.TunableManager;

public class LRTuning {

    public static final TunableManager tuning = new TunableManager(LiquidRoundup.Core.config);
    public static boolean tuning_enabled = false;

    static {
        tuning.registerTunable(LRTuning.class);
    }

    public static class Enables{
        
        @Tunable(category = "enables")
        public static boolean LiquidBackpacks_Enabled = true;
        
    }
    
    public static class Items {

        @Tunable(category = "items")
        public static int LiquidBackpackT2_ItemID = 7353;
        @Tunable(category = "items")
        public static int LiquidBackpackT1_ItemID = 7354;
    }
}
