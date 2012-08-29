package net.minecraft.src.denoflionsx.plugins.Core;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.items.multiItem;

public class LiquidVacuum extends multiItem {

    public LiquidVacuum(int par1, String name) {
        super(par1, name);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        ItemStack bag = API.getItem("milkbag");
        if (entity instanceof EntityCow) {
            player.dropPlayerItemWithRandomChoice(bag.copy(), false);
        }
        return true;
    }
}
