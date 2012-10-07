package denoflionsx.API.Interfaces;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public interface IPfFItemManager {
    
    @InternalUseOnly
    public void registerItem(String s, ItemStack i);
    
    @InternalUseOnly
    public void registerItem(String s, Item i, int dmg);
    
    @InternalUseOnly
    public void registerItem(String s, Item i);
    
    public void dumpItemsToConsole();
    
    public ItemStack getItem(String s);
    
    public ItemStack getNewItemStack(String s, int amount);
    
    public ItemStack getItemQuietly(String s);
    
    public ArrayList<ItemStack> getAllContainersForLiquid(String liquid);
    
    public ArrayList<ItemStack> getContainersForLiquidNoBarrel(String liquid);
    
    public boolean doesItemExist(String s);
    
}
