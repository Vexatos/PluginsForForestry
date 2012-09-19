package denoflionsx.plugins.Forestry.Modules.PeatModule;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.plugins.Forestry.FermenterHelper;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.items.Fuels.customFuelSolid;
import denoflionsx.plugins.Railcraft.CokeOvenRecipeHelper;
import net.minecraft.src.Item;

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
        this.parent.config.addDefault("Peat_SmeltTimeInCokeOven=" + (15 * 20));
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
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{ItemInterface.getItem("peat")}, new LiquidStack(PfFManagers.ItemManager.getItem("liquidpeat").itemID, Integer.valueOf(this.parent.config.getOption("LiquidPeat_AmountPerSqueeze")), 0), ItemInterface.getItem("ash"), Integer.valueOf(this.parent.config.getOption("LiquidPeat_PercentChanceOfAsh")));
            CokeOvenRecipeHelper.add(new CokeOvenRecipeHelper.Recipe(ItemInterface.getItem("peat"),ItemInterface.getItem("ash") , PfFManagers.ItemManager.getItem("liquidpeat"), this.getOptionInt("LiquidPeat_AmountPerSqueeze"), this.getOptionInt("Peat_SmeltTimeInCokeOven")));
            PfFManagers.ContainerManager.addLiquid("Liquid Peat", PfFManagers.ItemManager.getItem("liquidpeat"), PfFManagers.ColorManager.getColor(Colors.Values.BROWN.toString()));
        }
        //--------------------------
        // SUGARY PEAT STUFF
        //--------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled"))) {
            FermenterHelper.add(new ItemStack(Item.sugar), PfFManagers.ItemManager.getItem("liquidpeat"), 1000, PfFManagers.ItemManager.getItem("sugarypeat"), 1000, 1.0f);
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar"))}, new LiquidStack(PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar")).getItem(), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_AmountPerSqueeze"))));
            PfFManagers.ContainerManager.addLiquid("Sugary Peat", PfFManagers.ItemManager.getItem("sugarypeat"), PfFManagers.ColorManager.getColor(Colors.Values.LIGHTBROWN.toString()));
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
