package denoflionsx.plugins.Millenaire;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.plugins.Forestry.Helpers.CarpenterHelper;
import denoflionsx.plugins.Millenaire.Crops.cropMudBrickProvider;
import denoflionsx.plugins.Millenaire.Enums.EnumMillBlocks;
import forestry.api.cultivation.CropProviders;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public class pluginMillenaire extends PfFPluginTemplate {

    public customFuel mud;

    public pluginMillenaire(String name, String parent) {
        super(name, parent);
    }

    public pluginMillenaire() {
        this("pluginMillenarie", EnumModIDs.MODS.millenaire.getID());
    }

    @Override
    public void defaults() {
        this.config.addDefault("MudBrick_ForestryIntegration=true");
    }

    @Override
    public void doSetup() {

    }

    @Override
    public void recipes() {
        if (this.config.getOptionBool("MudBrick_ForestryIntegration")) {
            CropProviders.cerealCrops.add(new cropMudBrickProvider());
            CarpenterHelper.addRecipeWithLiquid(EnumMillBlocks.WET_BRICK.getBlock(), new LiquidStack(Block.waterStill.blockID,1000), new Object[]{"DSX","XXX","XXX",Character.valueOf('S'), new ItemStack(Block.sand), Character.valueOf('D'), new ItemStack(Block.dirt)});
        }
    }
}
