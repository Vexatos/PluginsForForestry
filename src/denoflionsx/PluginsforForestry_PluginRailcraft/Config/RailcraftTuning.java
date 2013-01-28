package denoflionsx.PluginsforForestry_PluginRailcraft.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class RailcraftTuning {
    
    public static boolean tuning_enabled = false;
    
    static{
        CoreTuning.tuning.registerTunable(RailcraftTuning.class);
    }

    public static class Tuning {

        @Tunable(category = "railcraft.tuning")
        public static int SugarCharcoal_AmountCentrifuged = 2;
        @Tunable(category = "railcraft.tuning")
        public static int CactusCharcoal_AmountCentrifuged = 2;
        @Tunable(category = "railcraft.tuning")
        public static int SugarCharcoal_CreosotePerCoke = 125;
        @Tunable(category = "railcraft.tuning")
        public static int CactusCharcoal_CreosotePerCoke = 125;
        @Tunable(category = "railcraft.tuning")
        public static int SugarCharcoal_AcidPerCraft = 155;
        @Tunable(category = "railcraft.tuning")
        public static int CactusCharcoal_AcidPerCraft = 155;
        @Tunable(category = "railcraft.tuning")
        public static int SugarCharcoal_CokeOvenTime = 3000;
        @Tunable(category = "railcraft.tuning")
        public static int CactusCharcoal_CokeOvenTime = 3000;
        @Tunable(category = "railcraft.tuning")
        public static int SulfuricAcid_AmountPerSqueeze = 10000;
        @Tunable(category = "railcraft.tuning", comment = "Nerfs Sugar Coke production by 1/2 due to Pulverizers doubling sugar from reeds.")
        public static boolean SugarCoke_ThermalExpansionNerf = false;
        @Tunable(category = "railcraft.tuning")
        public static int SugarSyrup_AmountPerSqueeze = 32;
        @Tunable(category = "railcraft.tuning", comment = "To make it equal with sugar.")
        public static boolean CactusCoke_ThermalExpansionNerf = false;
        @Tunable(category = "railcraft.tuning")
        public static int CactusSyrup_AmountPerSqueeze = 32;
        @Tunable(category = "railcraft.tuning", comment = "BurnTime value / conversion rate = boiler value")
        public static double Boiler_PfFConversionRate = 1.25;
    }

    public static class Enables {

        @Tunable(category = "railcraft.enables")
        public static boolean creosote_biogas = true;
        @Tunable(category = "railcraft.enables")
        public static boolean sugarcharcoal_enabled = true;
        @Tunable(category = "railcraft.enables")
        public static boolean cactuscharcoal_enabled = true;
        @Tunable(category = "railcraft.enables")
        public static boolean Backpack_Enabled = true;
    }

    public static class Fuels {

        @Tunable(category = "railcraft.fuels")
        public static int creosote_mjt = 4;
        @Tunable(category = "railcraft.fuels")
        public static int creosote_burntime = 14000;
        @Tunable(category = "railcraft.fuels")
        public static int sugarcharcoal_burntime = TileEntityFurnace.getItemBurnTime(new ItemStack(Item.coal));
        @Tunable(category = "railcraft.fuels")
        public static int sugarcoke_burntime = 3200 * 2;
        @Tunable(category = "railcraft.fuels")
        public static int cactuscharcoal_burntime = TileEntityFurnace.getItemBurnTime(new ItemStack(Item.coal));
        @Tunable(category = "railcraft.fuels")
        public static int cactuscoke_burntime = 3200 * 2;
    }

    public static class Items {

        @Tunable(category = "railcraft.items")
        public static int sugarcharcoal = 7341;
        @Tunable(category = "railcraft.items")
        public static int BackpackT1_ItemID = 7361;
        @Tunable(category = "railcraft.items")
        public static int BackpackT2_ItemID = 7362;

    }
}
