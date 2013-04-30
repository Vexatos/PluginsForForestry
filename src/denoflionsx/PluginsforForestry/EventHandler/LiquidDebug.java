package denoflionsx.PluginsforForestry.EventHandler;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidDebug implements IdenWorldEventHandler {

    @Override
    public void onWorldLoaded() {
        ArrayList<ItemStack> items = new ArrayList();
        items.addAll(LRItems.bucketStacks.values());
        items.addAll(LRItems.woodenBucketstacks.values());
        for (ItemStack i : items){
            LiquidStack l = LiquidContainerRegistry.getLiquidForFilledItem(i);
            if (l == null){
                PfF.Proxy.severe("Liquid Container link messed up! " + i.getDisplayName());
            }else{
                PfF.Proxy.print("Item " + i.getDisplayName() + " mapped to " + l.asItemStack().getDisplayName());
                Icon icon = l.canonical().getRenderingIcon();
                if (icon == null){
                    PfF.Proxy.severe(l.asItemStack().getDisplayName() + " is missing an icon!");
                }
            }
        }
        WorldEventHandler.unregisterHandler(this);
    }

    @Override
    public void onWorldEnded() {
    }
}
