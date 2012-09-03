package net.minecraft.src.denoflionsx.plugins.BluesFood;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.API.PFFItems;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class ItemButcherKnife extends ItemFoodTool {

    public ItemButcherKnife(int par1, int texture, int pew, int maxDamage, String name) {
        super(par1, texture, pew, maxDamage, name);
        PFFItems.registerItem(denLib.toLowerCaseNoSpaces(name), this);
    }

    @Override
    public int getDamageVsEntity(Entity par1Entity) {
        // If entity is an animal kick it's ass in one shot.
        if (par1Entity instanceof EntityAnimal) {
            return (this.maxDamage * 5);
        } else {
            return this.maxDamage;
        }
    }
    
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
        // If entity is an animal give us some extra loot!
        if (par2EntityLiving instanceof EntityAnimal) {
            EntityPlayer player = (EntityPlayer) par3EntityLiving;
            player.dropPlayerItemWithRandomChoice(API.getItem("milkbag").copy(), false);
        }
        return true;
    }
}
