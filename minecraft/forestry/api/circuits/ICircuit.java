package forestry.api.circuits;

import java.util.List;
import net.minecraft.src.TileEntity;

public interface ICircuit
{
    String getUID();

    boolean requiresDiscovery();

    int getLimit();

    String getName();

    boolean isCircuitable(TileEntity var1);

    void onInsertion(TileEntity var1);

    void onLoad(TileEntity var1);

    void onRemoval(TileEntity var1);

    void onTick(TileEntity var1);

    void addTooltip(List var1);
}
