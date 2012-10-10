package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import net.minecraft.src.ItemStack;

public interface ISilkWormRegistry {
    
    // These ItemStacks must contain items that implement ISilkWorm.
    // This system is intended for meta data items.
    
    public void registerWorm(ItemStack worm);
    
    public void registerCocoon(ItemStack cocoon);
    
    public void registerMoth(ItemStack moth);
    
    public boolean isItemStackWorm(ItemStack potentialWorm);
    
    public boolean isItemStackCocoon(ItemStack potentialCocoon);
    
    public boolean isItemStackMoth(ItemStack potentialMoth);
    
}
