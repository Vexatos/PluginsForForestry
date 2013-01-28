package denoflionsx.LiquidRoundup.Utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class StackUtils {
    
    public static ItemStack getNewStack(ItemStack stack, int amount){
        return new ItemStack(stack.itemID,amount,stack.getItemDamage());
    }
    
    public static LiquidStack getNewStack(LiquidStack stack, int amount){
        return new LiquidStack(stack.itemID,amount,stack.itemMeta);
    }
    
}
