package denoflionsx.plugins.Core;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import denoflionsx.items.multiItem;
import net.minecraft.src.*;

public class LiquidVacuum extends multiItem {

    public static ItemStack milkbag;
    public static ItemStack mushroombag;
    public static boolean mushroombagEnabled = false;

    public LiquidVacuum(int par1, String name) {
        super(par1, name);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        ItemStack bag;
        if (mushroombagEnabled) {
            if (entity instanceof EntityMooshroom) {
                player.dropPlayerItemWithRandomChoice(mushroombag.copy(), false);
                return true;
            }
        }
        if (entity instanceof EntityCow) {
            player.dropPlayerItemWithRandomChoice(milkbag.copy(), false);
            return true;
        }
        return true;
    }
    
    

//    @Override
//    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
//        if (par2EntityLiving instanceof EntityMooshroom){
//            par2EntityLiving.dropItemWithOffset(mushroombag.itemID, 1,5);
//            return true;
//        }
//        if (par2EntityLiving instanceof EntityCow){
//            par2EntityLiving.dropItemWithOffset(milkbag.itemID,1,5);
//            return true;
//        }
//        return true;
//    }
    
    
}
