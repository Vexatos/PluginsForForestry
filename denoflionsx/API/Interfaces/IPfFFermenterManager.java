package denoflionsx.API.Interfaces;

import net.minecraft.src.ItemStack;

public interface IPfFFermenterManager {
    
    public void addItem(ItemStack item, int bonus, ItemStack liquid);
    
    public void addItem(ItemStack item, int bonus);
    
    // Internal use only!
    public void registerPfFLiquid(ItemStack liquid, float bonus);
    
    public void createRecipes();
    
    
}
