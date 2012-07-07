package net.minecraft.src.denoflionsx.plugins.Forestry.Modules.solidfuel;

import net.minecraft.src.ItemStack;
import forestry.api.liquids.LiquidStack;

public class barObject {
    
    private ItemStack bar;
    private LiquidStack liquid;
    private ItemStack catalyst;

    public barObject(ItemStack bar, LiquidStack liquid, ItemStack catalyst) {
        this.bar = bar;
        this.liquid = liquid;
        this.catalyst = catalyst;
    }
    
    public ItemStack getItemStack(){
        return this.bar;
    }
    
    public LiquidStack getLiquidStack(){
        return this.liquid;
    }
    
    public ItemStack getCatalyst(){
        return this.catalyst;
    }
    
}
