package denoflionsx.plugins.Forestry.Modules;

import buildcraft.api.liquids.LiquidStack;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.API.API;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Colors;
import denoflionsx.plugins.Core.EnumLiquidTextures;
import denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import denoflionsx.plugins.Forestry.Modules.newFuels.customFuelSolid;

public class peatModule extends baseModule {

    public static customFuel liquidpeat;
    public static customFuel sugarypeat;
    public static customFuelSolid sugarypeatsolid;
    private ItemIDManager IDs_Peat = new ItemIDManager(2,"Peat");
    private ItemIDManager IDs_SugaryPeat = new ItemIDManager(2,"SugaryPeat");

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
