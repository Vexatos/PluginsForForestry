package denoflionsx.PluginsforForestry.Config;

import denoflionsx.denLib.Config.Annotations.Comment;
import denoflionsx.denLib.Config.Annotations.Config;
import denoflionsx.denLib.Config.Annotations.Tunable;
import net.minecraftforge.common.Configuration;

public class PfFTuning {

    @Config
    public static Configuration config;
    public static final int bucketIDRangeStart = 4444;

    public static int getInt(String s) {
        return Integer.valueOf(s);
    }

    public static boolean getBool(String s) {
        return s.toLowerCase().equals("true");
    }

    public static float getFloat(String s) {
        return Float.valueOf(s);
    }

    @Tunable(category = "liquids")
    public static class Liquids {

        @Comment(comment = "If true liquids will take up 1 block id per.")
        public static String liquid_asBlock = String.valueOf(true);
        //---------------------------------------------------------
        public static String veggiejuice_FermenterBonus = String.valueOf(1.5f);
        public static String liquidpeat_FermenterBonus = String.valueOf(1.5f);
    }

    @Tunable(category = "items", comment = "set id to 0 to disable")
    public static class Items {

        public static String barrel_ItemID = String.valueOf(4100);
        public static String barrel_capacity = String.valueOf(10000);
        public static String capsule_ItemID = String.valueOf(4101);
        public static String redcapsule_ItemID = String.valueOf(4102);
        public static String can_ItemID = String.valueOf(4103);
        @Comment(comment = "Liquids only consume one item or block id depending on mode.")
        public static String veggiejuice_ItemID = String.valueOf(4150);
        public static String liquidpeat_ItemID = String.valueOf(4151);
    }

    @Tunable(category = "items.buckets", comment = "set id to 0 to disable")
    public static class Buckets {

        // Buckets (+100 of normal ID range)
        public static String woodenbucket_ItemID = String.valueOf(bucketIDRangeStart);
    }

    @Tunable(category = "blocks", comment = "set id to 0 to disable")
    public static class Blocks {

        @Comment(comment = "Liquids only consume one item or block id depending on mode.")
        public static String veggiejuice_BlockID = String.valueOf(3100);
        public static String liquidpeat_BlockID = String.valueOf(3101);
    }
    
    @Tunable(category = "rendering", comment = "These options are for development")
    public static class Rendering{
        
        @Comment(comment = "This causes the renderer to refresh from the txt file every time.")
        public static String render_debugMode = String.valueOf(false);
        
    }
}
