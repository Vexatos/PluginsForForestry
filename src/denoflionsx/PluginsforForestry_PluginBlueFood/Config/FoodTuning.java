package denoflionsx.PluginsforForestry_PluginBlueFood.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;

public class FoodTuning {

    public static boolean tuning_enabled = false;

    static {
        CoreTuning.tuning.registerTunable(FoodTuning.class);
    }

    public static class Enables {

        @Tunable(category = "food.enables")
        public static boolean CookedEgg_Enabled = true;
        @Tunable(category = "food.enables", comment = "What heartless fool would disable cupcakes?")
        public static boolean Cupcake_Enabled = true;
        @Tunable(category = "food.enables")
        public static boolean ButcherKnife_Enabled = true;
    }

    public static class Enchantments {

        @Tunable(category = "food.enchants.enables")
        public static boolean BeastSlaying_Enabled = true;
        @Tunable(category = "food.enchants.ids")
        public static int BeastSlaying_EnchantID = 90;
        @Tunable(category = "food.enchants.tuning")
        public static int BeastSlaying_BonusMultiplier = 3;
    }

    public static class Tuning {

        @Tunable(category = "food.tuning")
        public static int ButcherKnife_DamageVsEntity = 6;
        @Tunable(category = "food.tuning")
        public static int ButcherKnife_DamageVsAnimalMultiplier = 5;
        @Tunable(category = "food.tuning")
        public static int ButcherKnife_BonusChickenFeathers = 2;
        @Tunable(category = "food.tuning")
        public static int ButcherKnife_GroundBeefDropped = 1;
        @Tunable(category = "food.tuning")
        public static int ButcherKnife_LambChopDropped = 1;
        @Tunable(category = "food.tuning")
        public static int ButcherKnife_TentacleDropped = 1;
        @Tunable(category = "food.tuning")
        public static int ButcherKnife_SausageDropped = 1;
        @Tunable(category = "food.tuning")
        public static int Flour_AmountPerCraft = 4;
        @Tunable(category = "food.tuning")
        public static int Cupcake_AmountPerCraft = 4;
        @Tunable(category = "food.tuning", comment = "This value can be zero.")
        public static int ButcherKnife_InternalCooldown = 2;
    }

    public static class Items {

        @Tunable(category = "food.items")
        public static int CookedEgg_ItemID = 7345;
        @Tunable(category = "food.items")
        public static int Cupcake_ItemID = 7346;
        @Tunable(category = "food.items")
        public static int Uncooked_ItemID = 7347;
        @Tunable(category = "food.items")
        public static int ButcherKnife_ItemID = 7348;
        @Tunable(category = "food.items")
        public static int CookedSausage_ItemID = 7349;
        @Tunable(category = "food.items")
        public static int CheeseBurger_ItemID = 7350;
        @Tunable(category = "food.items")
        public static int Calimari_ItemID = 7351;
        @Tunable(category = "food.items")
        public static int CookedLambChop_ItemID = 7352;
    }

    public static class Food {

        @Tunable(category = "food.stats")
        public static int CookedEgg_healAmount = 4;
        @Tunable(category = "food.stats")
        public static double CookedEgg_saturationModifier = 6.0;
        //----
        @Tunable(category = "food.stats")
        public static int Cupcake_healAmount = 5;
        @Tunable(category = "food.stats")
        public static double Cupcake_saturationModifer = 7.0;
        //----
        @Tunable(category = "food.stats")
        public static int CookedSausage_healAmount = 8;
        @Tunable(category = "food.stats")
        public static double CookedSausage_saturationModifer = 10.0;
        //----
        @Tunable(category = "food.stats")
        public static int CheeseBurger_healAmount = 8;
        @Tunable(category = "food.stats")
        public static double CheeseBurger_saturationModifer = 10.0;
        //----
        @Tunable(category = "food.stats")
        public static int Calimari_healAmount = 8;
        @Tunable(category = "food.stats")
        public static double Calimari_saturationModifer = 10.0;
        //----
        @Tunable(category = "food.stats")
        public static int CookedLambChop_healAmount = 8;
        @Tunable(category = "food.stats")
        public static double CookedLambChop_saturationModifer = 10.0;
    }
}
