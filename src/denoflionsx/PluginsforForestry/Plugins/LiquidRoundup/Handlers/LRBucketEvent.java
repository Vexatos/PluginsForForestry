package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Handlers;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class LRBucketEvent implements IBucketEventHandler {

    @Override
    public void onBucketFill(FillBucketEvent e) {
        int id = e.world.getBlockId(e.target.blockX, e.target.blockY, e.target.blockZ);
        ItemStack f = null;
        if (e.current.isItemEqual(LRItems.ItemStackWoodenBucketEmpty)) {
            f = LRItems.woodenBucketstacks.get(id);
        }else if (e.current.isItemEqual(LiquidContainerRegistry.EMPTY_BUCKET)){
            f = LRItems.bucketStacks.get(id);
        }
        if (f != null) {
            e.world.setBlockToAir(e.target.blockX, e.target.blockY, e.target.blockZ);
            e.result = f.copy();
            e.setResult(Event.Result.ALLOW);
        }
    }
}
