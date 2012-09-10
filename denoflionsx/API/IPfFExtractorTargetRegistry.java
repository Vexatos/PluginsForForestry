package denoflionsx.API;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public interface IPfFExtractorTargetRegistry {
    
    public void addBlock(int id, int meta);
    
    public void addBlock(int id);
    
    public void addBlock(Block b);
    
    public void addBlock(Block b, int meta);
    
    public void addItemStack(ItemStack i);
    
    public boolean isValidTarget(int id, int meta);
    
    public boolean isValidTarget(int id);
    
}
