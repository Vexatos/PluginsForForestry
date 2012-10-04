package denoflionsx.Old;

import buildcraft.api.liquids.LiquidStack;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.denLib;
import ic2.api.Items;

@Deprecated
public class addFermenterRecipes {
    
    // THIS CLASS IS DEPRICATED. WILL REMOVE WHEN BETTERFARMING PLUGIN IS UPDATED TO 1.3.2!!!
    // USE FERMENTERMANAGER INSTEAD.

    public static Item biomass = ItemInterface.getItem("liquidBiomass").getItem();
    public static Item applejuice = ItemInterface.getItem("liquidJuice").getItem();
    public static Item honey = ItemInterface.getItem("liquidHoney").getItem();
    public static Item seedoil = ItemInterface.getItem("liquidSeedOil").getItem();
    public static float bonus = 1.5f;

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
        if (denLib.detect(EnumModIDs.MODS.IC2.getID())) {
            RecipeManagers.fermenterManager.addRecipe(Items.getItem("plantBall").copy(), 400, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
            RecipeManagers.fermenterManager.addRecipe(Items.getItem("compressedPlantBall").copy(), 500, value, new LiquidStack(biomass, 1), new LiquidStack(input.getItem(), 1));
        }
    }

    public static void addItem(Item s, int value, Object t) {
        

    }

    public static void addNew(ItemStack fermented, int qty, Item product) {
        
    }
}
