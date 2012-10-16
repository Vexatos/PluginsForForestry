package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import net.minecraft.src.ItemStack;

public interface ISilkWorm {
    
    public void progressWormGrowth(ItemStack worm);
    
    public void setupWorm(ItemStack worm);
    
    public boolean isWormValid(ItemStack worm);
    
    public int getCurrentStageProgress(ItemStack worm);
    
}
