package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public interface ISilkWormMothAccess {
    
    public int getMothSlot(IInventory inventory);
    
    public boolean hasMoth(IInventory inventory);
    
    public ItemStack getMoth(IInventory inventory);
    
}
