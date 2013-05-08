package denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items;

import denoflionsx.PluginsforForestry.API.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BarrelRecipe implements IRegisterRecipe {

    @Override
    public void registerRecipe() {
        PfF.Proxy.registerRecipe(LRItems.barrelEmpty, new Object[]{"WIW", "WGW", "WIW", Character.valueOf('W'), Block.planks, Character.valueOf('I'), BarrelItems.ItemStackRings, Character.valueOf('G'), new ItemStack(Block.glass)});
    }
}
