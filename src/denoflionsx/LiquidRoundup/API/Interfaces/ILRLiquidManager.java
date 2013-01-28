package denoflionsx.LiquidRoundup.API.Interfaces;

import denoflionsx.LiquidRoundup.API.Annotations.DoNotUse;
import denoflionsx.LiquidRoundup.API.Annotations.InternalUseOnly;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;

public interface ILRLiquidManager {
    
    @DoNotUse
    public String registerLiquid(String name, int r, int g, int b);
    
    public LiquidStack registerLiquid(String name, String texture[]);
    
    // Use this one for liquids from other mods that just need containers.
    public void registerLiquid(LiquidRegisterEvent event);
    
    public int getNewInternalID();
    
    @InternalUseOnly
    public int getCurrentInt();
    
    @InternalUseOnly
    public String getSpritesheetPath(int id);
    
    @InternalUseOnly
    public String getCurrentSpritesheetPath();
    
    public ItemStack getContainer(String name);
    
    public void registerContainer(String name, ItemStack i);
    
}
