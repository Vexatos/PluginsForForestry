package denoflionsx.plugins.Forestry.Modules.PeatModule;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.core.PfFModuleTemplate;
import denoflionsx.plugins.Forestry.Helpers.FermenterHelper;
import denoflionsx.Old.customFuel_OLD;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.plugins.Railcraft.CokeOvenRecipeHelper;
import net.minecraft.src.Item;

public class peatModule extends PfFModuleTemplate {

    public static customFuel liquidpeat;
    public static customFuel sugarypeat;
    private ItemIDManager IDs_Peat = new ItemIDManager(2, "Peat");
    private ItemIDManager IDs_SugaryPeat = new ItemIDManager(2, "SugaryPeat");

    public peatModule(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("# These options are for Liquid Peat");
        this.config.addDefault("LiquidPeat_AmountPerSqueeze=250");
        this.config.addDefault("LiquidPeat_PercentChanceOfAsh=40");
        this.config.addDefault("Peat_SmeltTimeInCokeOven=" + (15 * 20));
    }

    @Override
    public void itemInitialized(EventItemInitialized event) {
        if (event.getName().equals("barrelfuels")) {
            if (denLib.convertToBoolean(this.config.getOption("LiquidPeat_Enabled"))) {
                PfFManagers.ContainerManager.addLiquid("Liquid Peat", PfFManagers.ItemManager.getItem("liquidpeat"), PfFManagers.ColorManager.getColor(Colors.Values.BROWN.toString()));
            }
            if (denLib.convertToBoolean(this.config.getOption("SugaryPeat_Enabled"))) {
                PfFManagers.ContainerManager.addLiquid("Sugary Peat", PfFManagers.ItemManager.getItem("sugarypeat"), PfFManagers.ColorManager.getColor(Colors.Values.LIGHTBROWN.toString()));
            }

        }
    }

    @Override
    public void recipes() {
        //----------------------------
        // LIQUID PEAT STUFF
        //----------------------------
        if (denLib.convertToBoolean(this.config.getOption("LiquidPeat_Enabled"))) {
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{ItemInterface.getItem("peat")}, new LiquidStack(PfFManagers.ItemManager.getItem("liquidpeat").itemID, Integer.valueOf(this.config.getOption("LiquidPeat_AmountPerSqueeze")), 0), ItemInterface.getItem("ash"), Integer.valueOf(this.config.getOption("LiquidPeat_PercentChanceOfAsh")));
            CokeOvenRecipeHelper.add(new CokeOvenRecipeHelper.Recipe(ItemInterface.getItem("peat"), ItemInterface.getItem("ash"), PfFManagers.ItemManager.getItem("liquidpeat"), this.config.getOptionInt("LiquidPeat_AmountPerSqueeze"), this.config.getOptionInt("Peat_SmeltTimeInCokeOven")));

        }
        //--------------------------
        // SUGARY PEAT STUFF
        //--------------------------
        if (denLib.convertToBoolean(this.config.getOption("SugaryPeat_Enabled"))) {
            FermenterHelper.add(new ItemStack(Item.sugar), PfFManagers.ItemManager.getItem("liquidpeat"), 1000, PfFManagers.ItemManager.getItem("sugarypeat"), 1000, 1.0f);
        }
    }

    @Override
    public void doSetup() {

        liquidpeat = new customFuel("Liquid Peat", 1, 20000, customFuel_OLD.populateSprites(EnumLiquidTextures.Liquids.PEAT.getIndex()), IDs_Peat, Colors.Values.BROWN.getColor(), this);

        sugarypeat = new customFuel("Sugary Peat", 2, 40000, customFuel_OLD.populateSprites(EnumLiquidTextures.Liquids.SUGARYPEAT.getIndex()), IDs_SugaryPeat, Colors.Values.LIGHTBROWN.getColor(), this);

    }
}
