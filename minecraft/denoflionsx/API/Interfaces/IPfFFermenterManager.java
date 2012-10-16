package denoflionsx.API.Interfaces;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public interface IPfFFermenterManager {
    
    public void addItem(ItemStack item, int bonus, ItemStack liquid);
    
    public void addItem(ItemStack item, int bonus);
    
    public ArrayList getAllFermentableObjects();
    
    @InternalUseOnly
    public void registerPfFLiquid(ItemStack liquid, float bonus);
    
    @InternalUseOnly
    public void createRecipes();
    
    
}
