package denoflionsx.PluginsforForestry.Config;

import denoflionsx.denLib.Config.Annotations.Comment;
import denoflionsx.denLib.Config.Annotations.Config;
import denoflionsx.denLib.Config.Annotations.Tunable;
import net.minecraftforge.common.Configuration;

public class PfFTuning {
    
    private static int normalIDRange = 4344;
    private static int bucketIDRangeStart = 4444;
    private static int blockIDRange = 3100;
    
    @Config
    public static Configuration config;

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
        public static String vanillaVeggies_SqueezeAmount = String.valueOf(100);
    }

    @Tunable(category = "items", comment = "set id to 0 to disable")
    public static class Items {

        public static String barrel_ItemID = String.valueOf(normalIDRange++);
        public static String capsule_ItemID = String.valueOf(normalIDRange++);
        public static String redcapsule_ItemID = String.valueOf(normalIDRange++);
        public static String can_ItemID = String.valueOf(normalIDRange++);
        public static String hammer_ItemID = String.valueOf(normalIDRange++);
        public static String rings_ItemID = String.valueOf(normalIDRange++);
        @Comment(comment = "Liquids only consume one item or block id depending on mode.")
        public static String veggiejuice_ItemID = String.valueOf(normalIDRange++);
        public static String liquidpeat_ItemID = String.valueOf(normalIDRange++);
        @Comment(comment = "Void bucket is incomplete and forcefully disabled.")
        public static String voidbucket_ItemID = String.valueOf(normalIDRange++);
    }

    @Tunable(category = "barrel")
    public static class Barrel {

        public static String barrel_capacity = String.valueOf(10000);
    }

    @Tunable(category = "items.buckets", comment = "set id to 0 to disable")
    public static class Buckets {

        // Buckets (+100 of normal ID range)
        public static String woodenbucket_ItemID = String.valueOf(bucketIDRangeStart++);
        public static String woodenbucket_filled_ItemID = String.valueOf(bucketIDRangeStart++);
        public static String bucket_filled_ItemID = String.valueOf(bucketIDRangeStart++);
    }

    @Tunable(category = "blocks", comment = "set id to 0 to disable")
    public static class Blocks {

        @Comment(comment = "Liquids only consume one item or block id depending on mode.")
        public static String veggiejuice_BlockID = String.valueOf(blockIDRange++);
        public static String liquidpeat_BlockID = String.valueOf(blockIDRange++);
    }

    @Tunable(category = "rendering", comment = "These options are for development")
    public static class Rendering {

        @Comment(comment = "This causes the renderer to refresh from the txt file every time.")
        public static String render_debugMode = String.valueOf(false);
    }

    @Tunable(category = "furnace")
    public static class Furnace {

        public static String fuel_LiquidContainersForFurnace = String.valueOf(true);
        @Comment(comment = "With default values biofuel/ethanol will be equivilant to lava in a vanilla furnace.")
        public static String fuel_LiquidContainerForFurnace_NerfPercent = String.valueOf(50);
    }

    @Tunable(category = "recipereplacement")
    public static class RecipeReplacement {

        @Comment(comment = "Replaces the Nether Star with an Ender Pearl.")
        public static String recipeReplacement_MFR_PortableSpawner = String.valueOf(false);
    }

    @Tunable(category = "mfr")
    public static class MFR {

        @Comment(comment = "Shapeless in the workbench.")
        public static String ForestryFertilizerFromMFRFertilizer = String.valueOf(true);
    }

    @Tunable(category = "railcraft.items", comment = "Set ID to 0 to disable features")
    public static class Railcraft {

        @Comment(comment = "Meta Item. Contains Sugar Charcoal, Sugar Coke, Cactus Charcoal, Cactus Coke.")
        public static String plugin_railcraft_charcoal = String.valueOf(normalIDRange++);
    }
}
