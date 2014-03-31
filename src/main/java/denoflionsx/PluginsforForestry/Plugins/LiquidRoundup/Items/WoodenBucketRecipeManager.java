package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.denLib.Mod.Handlers.DictionaryHandler;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.denLibMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class WoodenBucketRecipeManager implements IDictionaryListener {

    public WoodenBucketRecipeManager() {
        denLibMod.DictionaryHandler.registerListener(this, DictionaryHandler.channels.ORE);
        PfF.Proxy.registerRecipe(PluginLR.stacks.get("woodenBucket"), new Object[]{"LXL", "XLX", "XXX", Character.valueOf('L'), Block.wood});
        PfF.Proxy.registerRecipe(PluginLR.stacks.get("woodenBucket"), new Object[]{"XXX", "LXL", "XLX", Character.valueOf('L'), Block.wood});
    }

    @Override
    public void onEvent(String tag, short channel, Object o) {
        if (tag.equals("logWood")) {
            ItemStack i = (ItemStack) o;
            PfF.Proxy.registerRecipe(PluginLR.stacks.get("woodenBucket"), new Object[]{"LXL", "XLX", "XXX", Character.valueOf('L'), i});
            PfF.Proxy.registerRecipe(PluginLR.stacks.get("woodenBucket"), new Object[]{"XXX", "LXL", "XLX", Character.valueOf('L'), i});
        }
    }
}
