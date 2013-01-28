package denoflionsx.LiquidRoundup.API.Interfaces;

import denoflionsx.LiquidRoundup.API.Annotations.InternalUseOnly;
import denoflionsx.LiquidRoundup.API.Objects.LRContainer;
import net.minecraft.item.ItemStack;

public interface ILRContainerManager {
    
    public void registerNewContainer(String name, String[] textures, int capacity, ItemStack empty, boolean returned, boolean blackListLava, boolean customOnly, int maxStackSize);
    
    public LRContainer getContainerByName(String name);
    
    public LRContainer[] getContainerList();
    
    @InternalUseOnly
    public int getNewInternalID();
    
}
