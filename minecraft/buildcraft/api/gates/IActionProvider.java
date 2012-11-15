package buildcraft.api.gates;

import java.util.LinkedList;
import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;

public interface IActionProvider
{
    LinkedList getNeighborActions(Block var1, TileEntity var2);
}
