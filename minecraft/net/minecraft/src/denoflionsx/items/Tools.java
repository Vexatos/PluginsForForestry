package net.minecraft.src.denoflionsx.items;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class Tools extends multiItem {

    public Tools(int par1, String name) {
        super(par1, name);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!stack.isItemEqual(API.getItem("liquidvacuum"))) {
            return true;
        }
        ItemStack bag = API.getItem("milkbag");
        if (entity instanceof EntityCow) {
            player.dropPlayerItemWithRandomChoice(bag.copy(), false);
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (var12 == null) {
            return par1ItemStack;
        }
        int var13 = var12.blockX;
        int var14 = var12.blockY;
        int var15 = var12.blockZ;
        if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Wooden Bucket")))) {
            if (par2World.getBlockMaterial(var13, var14, var15) == Material.water && par2World.getBlockMetadata(var13, var14, var15) == 0) {
                par2World.setBlockWithNotify(var13, var14, var15, 0);
                return new ItemStack(this, 1, this.metaMap.get("Filled Wooden Bucket"));
            }
        } else if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Filled Wooden Bucket")))) {
            if (var12.sideHit == 0) {
                --var14;
            }

            if (var12.sideHit == 1) {
                ++var14;
            }

            if (var12.sideHit == 2) {
                --var15;
            }

            if (var12.sideHit == 3) {
                ++var15;
            }

            if (var12.sideHit == 4) {
                --var13;
            }

            if (var12.sideHit == 5) {
                ++var13;
            }
            if (par2World.isAirBlock(var13, var14, var15) || par2World.getBlockMaterial(var13, var14, var15) == Material.water) {
                if (!denLib.convertToBoolean(core.config.getOption("WoodenBucketWorksInNether")) && par2World.worldProvider.isHellWorld){
                    // Water is eaten.
                }else{
                  par2World.setBlockAndMetadataWithNotify(var13, var14, var15, Block.waterMoving.blockID, 0);  
                }   
                return new ItemStack(this, 1, this.metaMap.get("Wooden Bucket"));
            }
        }
        return par1ItemStack;
    }
}
