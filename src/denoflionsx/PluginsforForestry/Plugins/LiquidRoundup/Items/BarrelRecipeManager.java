package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.denLib.Mod.Handlers.DictionaryHandler;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.denLibMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BarrelRecipeManager implements IDictionaryListener{

    private ItemStack dye;
    private ItemStack iron;

    public BarrelRecipeManager() {
        dye = new ItemStack(Item.dyePowder);
        iron = new ItemStack(Item.ingotIron);
        denLibMod.DictionaryHandler.registerListener(this, DictionaryHandler.channels.ORE);
        hammer(new ItemStack(Item.stick));
        rings(iron);
    }

    @Override
    public void onEvent(String tag, short channel, Object o) {
        if (tag.equals("plankWood")) {
            barrel((ItemStack) o);
        }
        if (tag.equals("stickWood")) {
            hammer((ItemStack) o);
        }
        if (tag.equals("ingotSteel")) {
            rings((ItemStack) o);
        }
    }

    public final void hammer(ItemStack stick) {
        PfF.Proxy.registerRecipe(PluginLR.stacks.get("hammer"), new Object[]{"XiX", "III", "XSX", Character.valueOf('i'), dye, Character.valueOf('I'), Item.ingotIron, Character.valueOf('S'), stick});
    }

    public final void rings(ItemStack steel) {
        if (steel.isItemEqual(iron)) {
            PfF.Proxy.registerRecipe(PluginLR.stacks.get("rings"), new Object[]{"XHX", "III", "XXX", Character.valueOf('H'), PluginLR.stacks.get("hammer"), Character.valueOf('I'), Item.ingotIron});
        } else {
            PfF.Proxy.registerRecipe(PluginLR.stacks.get("ringsx"), new Object[]{"XHX", "III", "XXX", Character.valueOf('H'), PluginLR.stacks.get("hammer"), Character.valueOf('I'), steel});
        }
    }

    public final void barrel(ItemStack wood) {
        PfF.Proxy.registerRecipe(PluginLR.stacks.get("barrel"), new Object[]{"PRP", "PGP", "PRP", Character.valueOf('P'), wood, Character.valueOf('R'), PluginLR.stacks.get("rings"), Character.valueOf('G'), Block.glass});
    }
}
