package denoflionsx.LiquidRoundup.Items;

import denoflionsx.LiquidRoundup.API.Interfaces.ILRContainerItem;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.Mod.Items.ItemBase;
import denoflionsx.denLib.denLib;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class ContainerBase extends ItemBase implements ILRContainerItem {

    private HashMap<Integer, Color> colors = new HashMap();
    private static final Color white = new Color(255, 255, 255);
    private int containerTexture = 0;
    private int isFull;

    public ContainerBase(int par1) {
        super(par1);
    }

    public void setContainerTexture(int tex) {
        containerTexture = tex;
    }

    @Override
    public ItemStack add(String name, int internalID, Color color) {
        Property p = LiquidRoundup.Core.config.get("names.containers",denLib.toLowerCaseNoSpaces(name),name);
        this.add(p.value, internalID, containerTexture);
        colors.put(internalID, color);
        return new ItemStack(this, 1, internalID);
    }

    @Override
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        try {
            if (par2 > 0) {
                return this.colors.get(par1ItemStack.getItemDamage()).getRGB();
            }
        } catch (Exception ex) {
            return white.getRGB();
        }
        return white.getRGB();
    }

    @Override
    public int getIconIndex(ItemStack stack, int renderPass) {
        if (renderPass > 0){
            return super.getIconIndex(stack, renderPass) + 1;
        }else{
            return super.getIconIndex(stack, renderPass);
        }
    }
    
    

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        // I kept the old goofy behavior from wooden bucket.
        // I got kind of attached to the stacking water on water weirdness.
        if (par2World.isRemote) {
            return par1ItemStack;
        }
        if (!LiquidContainerRegistry.containsLiquid(par1ItemStack, new LiquidStack(Block.waterStill, 1000))) {
            return par1ItemStack;
        }
        LiquidContainerData data = null;
        for (LiquidContainerData d : LiquidContainerRegistry.getRegisteredLiquidContainerData()) {
            if (d.filled.itemID == par1ItemStack.itemID && d.filled.getItemDamage() == par1ItemStack.getItemDamage()) {
                data = d;
            }
        }
        if (data == null) {
            return par1ItemStack;
        }
        MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (var12 == null) {

            return par1ItemStack;
        }
        int var13 = var12.blockX;
        int var14 = var12.blockY;
        int var15 = var12.blockZ;
        if (par1ItemStack.isItemEqual(data.filled)) {
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
                if (par2World.provider.isHellWorld) {
                    // Water is eaten.
                } else {
                    par2World.setBlockAndMetadataWithNotify(var13, var14, var15, Block.waterMoving.blockID, 0);
                }
                return data.container.copy();
            }
        }
        return par1ItemStack;
    }
}
