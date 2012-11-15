package denoflionsx.plugins.Millenaire;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.plugins.Forestry.Helpers.CarpenterHelper;
import denoflionsx.plugins.Millenaire.Crops.cropMudBrickProvider;
import denoflionsx.plugins.Millenaire.Enums.EnumMillBlocks;
import forestry.api.cultivation.CropProviders;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

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
