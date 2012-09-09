package denoflionsx.API;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public interface IPfFItemRegistry {
    
    public void registerItem(String s, ItemStack i);
    
    public void registerItem(String s, Item i, int dmg);
    
    public void registerItem(String s, Item i);
    
    public ItemStack getItem(String s);
    
    public boolean doesItemExist(String s);
    
}
