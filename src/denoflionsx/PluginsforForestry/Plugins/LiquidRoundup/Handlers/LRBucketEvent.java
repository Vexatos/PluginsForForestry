package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class LRBucketEvent implements IBucketEventHandler {

    @Override
    public void onBucketFill(FillBucketEvent e) {
        ItemStack empty = e.current;
        int id = e.world.getBlockId(e.target.blockX, e.target.blockY, e.target.blockZ);
        int meta = e.world.getBlockMetadata(e.target.blockX, e.target.blockY, e.target.blockZ);
        ItemStack f = LiquidContainerRegistry.fillLiquidContainer(new LiquidStack(id, 1000, meta), empty);
        if (f != null) {
            e.world.setBlockToAir(e.target.blockX, e.target.blockY, e.target.blockZ);
            e.result = f.copy();
            e.setResult(Event.Result.ALLOW);
        }
    }
}
