package denoflionsx.PluginsforForestry.Dictionary;

import denoflionsx.PluginsforForestry.Core.PfF;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Vanilla {

    public static void inject() {
        PfF.Proxy.print("Injecting vanilla crops into ore dictionary.");
        OreDictionary.registerOre("cropPumpkin", new ItemStack(Block.pumpkin));
        OreDictionary.registerOre("cropMelon", new ItemStack(Item.melon));
        OreDictionary.registerOre("cropPotato", new ItemStack(Item.potato));
        OreDictionary.registerOre("cropCarrot", new ItemStack(Item.carrot));
    }
}
