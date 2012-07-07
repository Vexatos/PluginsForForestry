package net.minecraft.src.denoflionsx.plugins.Forestry;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.plugins.pluginBetterFarming;
import net.minecraft.src.denoflionsx.plugins.pluginCore;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.ic2.api.Items;

public class addFermenterRecipes {

    private static Item biomass = ItemInterface.getItem("liquidBiomass").getItem();
    private static Item applejuice = ItemInterface.getItem("liquidJuice").getItem();
    private static Item honey = ItemInterface.getItem("liquidHoney").getItem();
    private static Item seedoil = ItemInterface.getItem("liquidSeedOil").getItem();

    public static void add(ItemStack input, float value) {
        for (int i = 0; i < 3; i++) {
            RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.sapling, 1, i), 800, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        }
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.cactus, 1, 0), 200, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Item.wheat, 1, 0), 100, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Item.reed, 1, 0), 200, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Item.sugar, 1, 0), 200, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.mushroomBrown, 1, 0), 200, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(Block.mushroomCapRed, 1, 0), 200, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        if (denLib.detect("mod_IC2")) {
            RecipeManagers.fermenterManager.addRecipe(Items.getItem("plantBall").copy(), 400, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
            RecipeManagers.fermenterManager.addRecipe(Items.getItem("compressedPlantBall").copy(), 500, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        }
    }

    public static void addItem(Item s, int value, Object t) {
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(s, 1, 0), value, 1.0F, new LiquidStack(biomass, 1), new LiquidStack(Block.waterStill, 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(s, 1, 0), value, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(applejuice, 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(s, 1, 0), value, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(honey, 1));
        RecipeManagers.fermenterManager.addRecipe(new ItemStack(s, 1, 0), value, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(seedoil, 1));
        // This little bit is to prevent a potential null pointer if you call this method from within pluginBetterFarming.
        if (t instanceof pluginBetterFarming) {
            RecipeManagers.fermenterManager.addRecipe(new ItemStack(s, 1, 0), value, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(pluginBetterFarming.citrusJuice, 1));
        } else {
            if (pluginCore.plugins.get("BetterFarming").loaded) {
                RecipeManagers.fermenterManager.addRecipe(new ItemStack(s, 1, 0), value, 1.5F, new LiquidStack(biomass, 1), new LiquidStack(pluginBetterFarming.citrusJuice, 1));
            }
        }

    }

    public static void addNew(ItemStack fermented, int qty, Item product) {
        RecipeManagers.fermenterManager.addRecipe(fermented, qty, 1.0F, new LiquidStack(product, 1), new LiquidStack(Block.waterStill, 1));
        RecipeManagers.fermenterManager.addRecipe(fermented, qty, 1.5F, new LiquidStack(product, 1), new LiquidStack(applejuice, 1));
        RecipeManagers.fermenterManager.addRecipe(fermented, qty, 1.5F, new LiquidStack(product, 1), new LiquidStack(honey, 1));
        RecipeManagers.fermenterManager.addRecipe(fermented, qty, 1.5F, new LiquidStack(product, 1), new LiquidStack(seedoil, 1));
        if (pluginCore.plugins.get("BetterFarming").loaded) {
            RecipeManagers.fermenterManager.addRecipe(fermented, qty, 1.5F, new LiquidStack(product, 1), new LiquidStack(pluginBetterFarming.citrusJuice, 1));
        }
    }
}
