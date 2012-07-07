package net.minecraft.server.denoflionsx.plugins.Forestry;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.pluginBetterFarming;
import net.minecraft.server.denoflionsx.plugins.pluginCore;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import ic2.api.Items;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

public class addFermenterRecipes
{
    private static Item biomass = ItemInterface.getItem("liquidBiomass").getItem();
    private static Item applejuice = ItemInterface.getItem("liquidJuice").getItem();
    private static Item honey = ItemInterface.getItem("liquidHoney").getItem();
    private static Item seedoil = ItemInterface.getItem("liquidSeedOil").getItem();

    public static void add(ItemStack var0, float var1)
    {
        for (int var2 = 0; var2 < 3; ++var2)
        {
            RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.SAPLING, 1, var2), 800, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        }

        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.CACTUS, 1, 0), 200, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Item.WHEAT, 1, 0), 100, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Item.SUGAR_CANE, 1, 0), 200, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Item.SUGAR, 1, 0), 200, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.BROWN_MUSHROOM, 1, 0), 200, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.BIG_MUSHROOM_2, 1, 0), 200, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));

        if (denLib.detect("mod_IC2"))
        {
            RecipeManagers.fermenterManager.addRecipe(Items.getItem("plantBall").cloneItemStack(), 400, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
            RecipeManagers.fermenterManager.addRecipe(Items.getItem("compressedPlantBall").cloneItemStack(), 500, var1, new LiquidStack(biomass, 1), new LiquidStack(var0.getItem(), 1));
        }
    }

    public static void addItem(Item var0, int var1, Object var2)
    {
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(var0, 1, 0), var1, 1.0F, new LiquidStack(biomass, 1), new LiquidStack(Block.STATIONARY_WATER, 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(var0, 1, 0), var1, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(applejuice, 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(var0, 1, 0), var1, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(honey, 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(var0, 1, 0), var1, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(seedoil, 1));

        if (var2 instanceof pluginBetterFarming)
        {
            RecipeManagers.fermenterManager.addRecipe(new ItemStack(var0, 1, 0), var1, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(pluginBetterFarming.citrusJuice, 1));
        }
        else if (((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
        {
            RecipeManagers.fermenterManager.addRecipe(new ItemStack(var0, 1, 0), var1, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(pluginBetterFarming.citrusJuice, 1));
        }
    }

    public static void addNew(ItemStack var0, int var1, Item var2)
    {
        RecipeManagers.fermenterManager.addRecipe(var0, var1, 1.0F, new LiquidStack(var2, 1), new LiquidStack(Block.STATIONARY_WATER, 1));
        RecipeManagers.fermenterManager.addRecipe(var0, var1, 1.5F, new LiquidStack(var2, 1), new LiquidStack(applejuice, 1));
        RecipeManagers.fermenterManager.addRecipe(var0, var1, 1.5F, new LiquidStack(var2, 1), new LiquidStack(honey, 1));
        RecipeManagers.fermenterManager.addRecipe(var0, var1, 1.5F, new LiquidStack(var2, 1), new LiquidStack(seedoil, 1));

        if (((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
        {
            RecipeManagers.fermenterManager.addRecipe(var0, var1, 1.5F, new LiquidStack(var2, 1), new LiquidStack(pluginBetterFarming.citrusJuice, 1));
        }
    }
}
