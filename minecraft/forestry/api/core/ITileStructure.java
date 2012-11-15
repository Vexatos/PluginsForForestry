package forestry.api.core;

import net.minecraft.src.IInventory;
import net.minecraft.src.TileEntity;

public interface ITileStructure
{
    String getTypeUID();

    void validateStructure();

    void onStructureReset();

    ITileStructure getCentralTE();

    void setCentralTE(TileEntity var1);

    IInventory getInventory();

    void makeMaster();

    boolean isMaster();

    boolean isIntegratedIntoStructure();
}
