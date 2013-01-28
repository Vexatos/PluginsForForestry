package denoflionsx.LiquidRoundup.UpdateHelper;

import denoflionsx.LiquidRoundup.LiquidRoundup;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;

public class UpdateHelper {

    public UpdateHelper() {
        reg();
    }

    public final void reg() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @ForgeSubscribe
    public void onEvent(ChunkEvent.Load event) {
        List<TileEntity> list = event.getChunk().worldObj.loadedTileEntityList;
        for (TileEntity t : list) {
            if (t instanceof ITankContainer) {
                ITankContainer tank = (ITankContainer) t;
                ILiquidTank tanks[] = tank.getTanks(ForgeDirection.UNKNOWN);
                for (ILiquidTank q : tanks) {
                    if (q.getLiquid() != null) {
                        int id = q.getLiquid().itemID;
                        if (Item.itemsList[id] == null) {
                            try {
                                LiquidRoundup.Proxy.print("Found a broken liquid!");
                                World world = t.getWorldObj();
                                world.setBlock(t.xCoord, t.yCoord, t.zCoord, 0);
                            } catch (Exception ex) {
                                LiquidRoundup.Proxy.print("WorldObj is null. What the hell?");
                                LiquidRoundup.Proxy.print("Making WorldObj assumption. This might end badly. Hold onto your butts.");
                                World world = event.world;
                                world.setBlock(t.xCoord, t.yCoord, t.zCoord, 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
