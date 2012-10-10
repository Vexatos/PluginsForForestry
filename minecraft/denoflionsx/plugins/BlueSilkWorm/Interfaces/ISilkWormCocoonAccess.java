package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public interface ISilkWormCocoonAccess {
    
    public int getCocoonSlot(IInventory inventory);
    
    public boolean hasCocoon(IInventory inventory);
    
    public ItemStack getCocoon(IInventory inventory);
    
}
