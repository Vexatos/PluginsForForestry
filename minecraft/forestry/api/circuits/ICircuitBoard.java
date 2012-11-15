package forestry.api.circuits;

import forestry.api.core.INBTTagable;
import java.util.List;
import net.minecraft.src.TileEntity;

public interface ICircuitBoard extends INBTTagable
{
    int getPrimaryColor();

    int getSecondaryColor();

    void addTooltip(List var1);

    void onInsertion(TileEntity var1);

    void onLoad(TileEntity var1);

    void onRemoval(TileEntity var1);

    void onTick(TileEntity var1);
}
