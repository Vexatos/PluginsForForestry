package denoflionsx.API.Interfaces;

import denoflionsx.API.Annotations.InternalUseOnly;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public interface IPfFExtractorManager {
    
    public void addBlock(int id, int meta);
    
    public void addBlock(int id);
    
    public void addBlock(Block b);
    
    public void addBlock(Block b, int meta);
    
    public void addItemStack(ItemStack i);
    
    @InternalUseOnly
    public boolean isValidTarget(int id, int meta);
    
    @InternalUseOnly
    public boolean isValidTarget(int id);
    
}
