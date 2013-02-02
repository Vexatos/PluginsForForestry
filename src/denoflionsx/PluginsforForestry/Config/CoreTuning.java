package denoflionsx.PluginsforForestry.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.API.Annotations.TunableManager;
import denoflionsx.PluginsforForestry.PfF;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class CoreTuning {
    
    //private static final int LastUsedItemID = 7344;
    public static final TunableManager tuning = new TunableManager(PfF.Core.config);
    public static boolean tuning_enabled = false;
    
    static{
        tuning.registerTunable(CoreTuning.class);
    }

    public static class Fuel {

        @Tunable(category = "core.liquids")
        public static int MushroomSoup_MJt = 1;
        @Tunable(category = "core.liquids")
        public static int MushroomSoup_BurnTime = 40000;
    }

    public static class Tuning {

        @Tunable(category = "core.tuning", comment = "A lower growth factor makes the plant grow faster.")
        public static int growthFactor = 5;
        @Tunable(category = "core.tuning", comment = "Amount of rings created per craft.")
        public static int ringAmount = 64;
        @Tunable(category = "core.tuning")
        public static int potato_squeezeAmount = (LiquidContainerRegistry.BUCKET_VOLUME / 10);
        @Tunable(category = "core.tuning")
        public static int carrot_squeezeAmount = (LiquidContainerRegistry.BUCKET_VOLUME / 10);
        @Tunable(category = "core.tuning")
        public static int pumpkin_squeezeAmount = (int)(LiquidContainerRegistry.BUCKET_VOLUME * 0.4);
        @Tunable(category = "core.tuning")
        public static int melonSlice_squeezeAmount = 50;
        @Tunable(category = "core.tuning")
        public static int melonBlock_squeezeAmount = 500;
    }
    
    public static class Updates{
        
        @Tunable(category = "update.check", comment = "Controls update check for ALL plugins and the Core")
        public static boolean update_check = true;
        
    }

    public static class Enables {

        private static final String c = "core.enables";
        
        // Enables
        @Tunable(category = c)
        public static boolean charm_enabled = true;
        @Tunable(category = c)
        public static boolean charm_crafting = false;
        @Tunable(category = c)
        public static boolean liquidvac_enabled = true;
        @Tunable(category = c)
        public static boolean omniplant_enabled = true;
        @Tunable(category = c)
        public static boolean woodenbucket_enabled = true;
        @Tunable(category = c)
        public static boolean barrel_enabled = true;
        @Tunable(category = c)
        public static boolean veggiejuice_enabled = true;
        @Tunable(category = c)
        public static boolean fruitjuice_enabled = true;
        @Tunable(category = c)
        public static boolean pumpkinjuice_enabled = true;
        @Tunable(category = c)
        public static boolean melonjuice_enabled = true;
    }

    public static class Items {

        // Items
        @Tunable(category = "core.items")
        public static int woodenbucket = 7333;
        @Tunable(category = "core.items")
        public static int barrel = 7334;
        @Tunable(category = "core.items")
        public static int hammer = 7335;
        @Tunable(category = "core.items")
        public static int rings = 7336;
        @Tunable(category = "core.items")
        public static int liqvac = 7337;
        @Tunable(category = "core.items")
        public static int bags = 7338;
        @Tunable(category = "core.items")
        public static int seed = 7339;
        @Tunable(category = "core.items")
        public static int charm = 7342;
    }

    public static class Blocks {
        // Blocks

        @Tunable(category = "core.blocks")
        public static int omniplant = 3333;
        @Tunable(category = "core.blocks")
        public static int farmblock = 3335;
    }
}
