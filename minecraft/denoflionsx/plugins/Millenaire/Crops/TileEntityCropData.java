package denoflionsx.plugins.Millenaire.Crops;

import denoflionsx.plugins.Millenaire.Enums.EnumMillBlocks;
import net.minecraft.src.TileEntity;

public class TileEntityCropData extends TileEntity {

    public boolean isReady = false;

    public TileEntityCropData() {
    }

    @Override
    public void updateEntity() {
        if (this.worldObj.getBlockId(xCoord, yCoord, zCoord) == EnumMillBlocks.MUD_BRICK.getBlock().itemID) {
            if (this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == EnumMillBlocks.MUD_BRICK.getBlock().getItemDamage()) {
                if (!this.isReady) {
                    this.isReady = true;
                }
            }
        }
    }
}
