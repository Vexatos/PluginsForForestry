package denoflionsx.LiquidRoundup.API.Interfaces;

import java.awt.Color;
import net.minecraft.item.ItemStack;

// Implement this to make your own container item from scratch.
// All the boiler plate code related to meta items is expected to be done.

public interface ILRContainerItem {
    
    public ItemStack add(String name, int internalID, Color color);
    
}
