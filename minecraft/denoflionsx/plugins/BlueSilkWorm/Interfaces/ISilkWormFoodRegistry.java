package denoflionsx.plugins.BlueSilkWorm.Interfaces;

import net.minecraft.src.ItemStack;

public interface ISilkWormFoodRegistry {
    
    public void registerFood(ItemStack food, int value);
    
    public boolean isValidFood(ItemStack food);
    
    public int getValueForFood(ItemStack food);
    
}
