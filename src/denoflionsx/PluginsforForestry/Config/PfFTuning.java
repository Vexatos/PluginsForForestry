package denoflionsx.PluginsforForestry.Config;

import denoflionsx.denLib.Config.Annotations.Config;
import denoflionsx.denLib.Config.Annotations.Tunable;
import net.minecraftforge.common.Configuration;

public class PfFTuning {

    @Config
    public static Configuration config;

    public static int getInt(String s) {
        return Integer.valueOf(s);
    }
    
    public static boolean getBool(String s){
        return Boolean.getBoolean(s);
    }

    @Tunable(category = "items")
    public static class Items {

        public static String barrel_ItemID = String.valueOf(4100);
        public static String barrel_capacity = String.valueOf(10000);
    }
    
    @Tunable(category = "blocks", comment = "set id to 0 to disable")
    public static class Blocks{
        public static String veggiejuice_BlockID = String.valueOf(3100);
    }
}
