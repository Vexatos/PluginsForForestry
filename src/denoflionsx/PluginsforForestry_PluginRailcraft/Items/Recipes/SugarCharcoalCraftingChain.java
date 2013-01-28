package denoflionsx.PluginsforForestry_PluginRailcraft.Items.Recipes;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.Interfaces.IPfFFuelHandler;
import denoflionsx.PluginsforForestry_PluginRailcraft.Config.RailcraftTuning;
import denoflionsx.PluginsforForestry_PluginRailcraft.Handlers.FuelHandler;
import denoflionsx.PluginsforForestry_PluginRailcraft.PfFRailcraft;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class SugarCharcoalCraftingChain {

    public static IPfFFuelHandler fuel = new FuelHandler();
    public static LiquidStack acid;

    public static void createCraftingChain(Item sc, LiquidStack scs) {
        ItemStack sugar = new ItemStack(Item.sugar);
        // Sugar
        APIWrappers.forestry.carpenter.addRecipe(5, scs, null, new ItemStack(sc, 1, 1), new Object[]{"SXX", "XXX", "XXX", Character.valueOf('S'), sugar});
        APIWrappers.TE.transposer.addFillRecipe(150, sugar, new ItemStack(sc, 1, 1), scs, false);
        APIWrappers.forestry.centrifuge.addRecipe(5, new ItemStack(sc, 1, 1), new ItemStack(sc, RailcraftTuning.Tuning.SugarCharcoal_AmountCentrifuged, 0));
        APIWrappers.TE.pulv.addRecipe(5, new ItemStack(sc, 1, 1), new ItemStack(sc, RailcraftTuning.Tuning.SugarCharcoal_AmountCentrifuged, 0));
        // Finished Product
        LiquidStack acidCopy = StackUtils.getNewStack(acid, RailcraftTuning.Tuning.SugarCharcoal_AcidPerCraft);
        APIWrappers.forestry.carpenter.addRecipe(5, acidCopy, null, new ItemStack(sc, 1, 2), new Object[]{"PXX", "XXX", "XXX", Character.valueOf('P'), new ItemStack(sc, 1, 0)});
        APIWrappers.TE.transposer.addFillRecipe(150, new ItemStack(sc, 1, 0), new ItemStack(sc, 1, 2), acidCopy, false);
        APIWrappers.railcraft.cokeOven.addRecipe(new ItemStack(sc, 1, 2), new ItemStack(sc, 1, 3), StackUtils.getNewStack(PfFRailcraft.Core.event.Liquid, RailcraftTuning.Tuning.SugarCharcoal_CreosotePerCoke), RailcraftTuning.Tuning.SugarCharcoal_CokeOvenTime);
        fuel.registerFuel(new ItemStack(sc, 1, 2), RailcraftTuning.Fuels.sugarcharcoal_burntime);
        fuel.registerFuel(new ItemStack(sc, 1, 3), RailcraftTuning.Fuels.sugarcoke_burntime);
        FMLWrapper.MODE.FML.registerFuelHandler(fuel);
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.torchWood, 4, 0), new Object[]{new ItemStack(sc, 1, 2), new ItemStack(Item.stick)});
    }

    public static void createCraftingChainB(Item sc, LiquidStack ccs) {
        ItemStack cactus = new ItemStack(sc, 1, 4);
        FMLWrapper.MODE.FML.addRecipe(cactus, new Object[]{"CXX", "XXX", "XXX", Character.valueOf('C'), new ItemStack(Block.cactus)});
        APIWrappers.TE.pulv.addRecipe(40, new ItemStack(Block.cactus), StackUtils.getNewStack(cactus, 2));
        APIWrappers.forestry.carpenter.addRecipe(5, ccs, null, new ItemStack(sc, 1, 6), new Object[]{"CXX", "XXX", "XXX", Character.valueOf('C'), cactus});
        APIWrappers.TE.transposer.addFillRecipe(150, cactus, new ItemStack(sc, 1, 6), ccs, false);
        APIWrappers.forestry.centrifuge.addRecipe(5, new ItemStack(sc, 1, 6), new ItemStack(sc, RailcraftTuning.Tuning.CactusCharcoal_AmountCentrifuged, 5));
        APIWrappers.TE.pulv.addRecipe(5, new ItemStack(sc, 1, 6), new ItemStack(sc, RailcraftTuning.Tuning.CactusCharcoal_AmountCentrifuged, 5));
        //
        LiquidStack acidcopy = StackUtils.getNewStack(acid, RailcraftTuning.Tuning.CactusCharcoal_AcidPerCraft);
        APIWrappers.forestry.carpenter.addRecipe(5, acidcopy, null, new ItemStack(sc, 1, 7), new Object[]{"CXX", "XXX", "XXX", Character.valueOf('C'), new ItemStack(sc, 1, 5)});
        APIWrappers.TE.transposer.addFillRecipe(150, new ItemStack(sc, 1, 5), new ItemStack(sc, 1, 7), acidcopy, false);
        APIWrappers.railcraft.cokeOven.addRecipe(new ItemStack(sc, 1, 7), new ItemStack(sc, 1, 8), StackUtils.getNewStack(PfFRailcraft.Core.event.Liquid, RailcraftTuning.Tuning.CactusCharcoal_CreosotePerCoke), RailcraftTuning.Tuning.CactusCharcoal_CokeOvenTime);
        fuel.registerFuel(new ItemStack(sc, 1, 7), RailcraftTuning.Fuels.cactuscharcoal_burntime);
        fuel.registerFuel(new ItemStack(sc, 1, 8), RailcraftTuning.Fuels.cactuscoke_burntime);
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.torchWood, 4, 0), new Object[]{new ItemStack(sc, 1, 7), new ItemStack(Item.stick)});
    }
}
