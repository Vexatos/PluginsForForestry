package denoflionsx.PluginsforForestry_PluginRedPower.Config;

import denoflionsx.PluginsforForestry.API.Annotations.DepricatedTunable;
import denoflionsx.PluginsforForestry.API.Annotations.Tunable;

public class RedpowerTuning {
    
    public static boolean tuning_enabled = false;
    
    public static class Enables{
        
        @DepricatedTunable(category = "redpower.enables")
        public static boolean Backpack_Enabled = true;
        @Tunable(category = "redpower.integration")
        public static boolean Flax_Omniplant = true;
        
    }
    
    public static class Items{
        
        @DepricatedTunable(category = "redpower.items")
        public static int BackpackT1_ItemID = 7363;
        @DepricatedTunable(category = "redpower.items")
        public static int BackpackT2_ItemID = 7364;
        
    }
    
}
