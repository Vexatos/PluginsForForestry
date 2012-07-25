package net.minecraft.src.denoflionsx.items;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.pluginCore;

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

//    @Override
//    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7) {
//        if (pluginCore.debugmode && par1ItemStack.isItemEqual(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Debug Tool")))) {
//            int get = par3World.getBlockId(par4, par5, par6);
//            int meta = par3World.getBlockMetadata(par4, par5, par6);
//            core.print("ID: " + String.valueOf(get) + " | meta : " + String.valueOf(meta));
//            Block b = Block.blocksList[get];
//            Class c = b.getClass();
//            String name = c.getName();
//            denLib.classSnoop(name);
//        }
//        return true;
//    }
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        //float var4 = 1.0F;
        //double var5 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double) var4;
        //double var7 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double) var4 + 1.62D - (double) par3EntityPlayer.yOffset;
        //double var9 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double) var4;
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
        } else if (par1ItemStack.isItemEqual(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milker")))) {
//            core.print("" + var12.typeOfHit.name());
//            if (var12.entityHit instanceof EntityCow){
//                ItemStack bag = new ItemStack(pluginCore.metaItem,1,pluginCore.metaItem.metaMap.get("Milk Bag"));
//                par3EntityPlayer.dropPlayerItemWithRandomChoice(bag.copy(), false);
//            }
        }
        return par1ItemStack;
    }
}
