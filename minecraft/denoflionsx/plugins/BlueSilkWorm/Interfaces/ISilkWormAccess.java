package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public interface ISilkWormAccess{
    
    public int getWormSlot(IInventory inventory);
    
    public boolean hasWorm(IInventory inventory);
    
    public ItemStack getWorm(IInventory inventory);
    
}
