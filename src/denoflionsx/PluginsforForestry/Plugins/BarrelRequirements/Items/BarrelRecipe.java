package denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items;

import denoflionsx.PluginsforForestry.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BarrelRecipe implements IRegisterRecipe {

    @Override
    public void registerRecipe() {
        for (ItemStack i : OreDictionary.getOres("plankWood")) {
            PfF.Proxy.registerRecipe(LRItems.barrelEmpty, new Object[]{"WIW", "WGW", "WIW", Character.valueOf('W'), i, Character.valueOf('I'), BarrelItems.ItemStackRings, Character.valueOf('G'), new ItemStack(Block.glass)});
        }
    }
}
