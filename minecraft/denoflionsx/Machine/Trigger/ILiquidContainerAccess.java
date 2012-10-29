package denoflionsx.Machine.Trigger;

import net.minecraft.src.ItemStack;

public interface ILiquidContainerAccess {
    
    public int getContainerSlot();
    
    public ItemStack getContainer();
    
    public boolean hasLiquidContainer();
    
}
