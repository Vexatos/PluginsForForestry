package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.plugins.Core.EnumLiquidTextures;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuelSolid;

public class peatModule extends baseModule {

    public static customFuel liquidpeat;
    public static customFuel sugarypeat;
    public static customFuelSolid sugarypeatsolid;
    private ItemIDManager IDs_Peat = new ItemIDManager(2);
    private ItemIDManager IDs_SugaryPeat = new ItemIDManager(2);

    public peatModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.parent.config.addDefault("# These options are for Liquid Peat");
        this.parent.config.addDefault("LiquidPeat_AmountPerSqueeze=250");
        this.parent.config.addDefault("LiquidPeat_PercentChanceOfAsh=40");
        this.parent.config.addDefault("SugaryPeat_Liquid_AmountPerSqueeze=250");
        this.parent.config.addDefault("SugaryPeat_Carpenter_Bonus=1");
    }

    public static void load(pluginBase parent) {
        baseModule b = new peatModule(parent);
        b.register();
    }

    @Override
    protected void recipes() {
        //----------------------------
        // LIQUID PEAT STUFF
        //----------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("LiquidPeat_Enabled"))) {
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{ItemInterface.getItem("peat")}, new LiquidStack(API.getItem("liquidpeat").itemID, Integer.valueOf(this.parent.config.getOption("LiquidPeat_AmountPerSqueeze")), 0), ItemInterface.getItem("ash"), Integer.valueOf(this.parent.config.getOption("LiquidPeat_PercentChanceOfAsh")));
        }
        //--------------------------
        // SUGARY PEAT STUFF
        //--------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled"))) {
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar"))}, new LiquidStack(API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar")).getItem(), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_AmountPerSqueeze"))));
        }
    }

    @Override
    protected void init() {

        //--------------------------------------------
        liquidpeat = new customFuel("Liquid Peat", 1, 20000, customFuel.populateSprites(EnumLiquidTextures.Liquids.PEAT.getIndex()), IDs_Peat, Colors.Values.BROWN.getColor(), this.parent);


        sugarypeat = new customFuel("Sugary Peat", 2, 40000, customFuel.populateSprites(EnumLiquidTextures.Liquids.SUGARYPEAT.getIndex()), IDs_SugaryPeat, Colors.Values.LIGHTBROWN.getColor(), this.parent);

        recipes();
    }
}
