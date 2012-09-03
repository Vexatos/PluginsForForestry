package net.minecraft.src.denoflionsx.plugins.Forestry.Modules.bluesWax;

import java.util.List;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.Core.EnumToolTextures;
import net.minecraft.src.forge.ITextureProvider;

public class ItemRodofFreezing extends Item implements ITextureProvider{
    
    private String name;

    public ItemRodofFreezing(int par1) {
        super(par1);
        this.setMaxStackSize(1);
        this.name = core.addName("Rod of Freezing");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition obj = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (obj == null){
            return par1ItemStack;
        }
        int id = par2World.getBlockId(obj.blockX, obj.blockY, obj.blockZ);
        if (id == Block.waterMoving.blockID || id == Block.waterStill.blockID){
            par2World.setBlockAndMetadataWithNotify(obj.blockX,obj.blockY,obj.blockZ, Block.ice.blockID, 0);
        }
        return par1ItemStack;
    }
    
    @Override
    public int getIconFromDamage(int par1) {
        return EnumToolTextures.ToolTextures.RODOFREEZING.getIndex();
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return this.name;
    }
    
     @Override public void addInformation(ItemStack par1ItemStack, List par2List){tooltips(par1ItemStack, par2List);}
     
     private void tooltips(ItemStack item, List list){
         list.add("Right click some water!");
     }

    @Override
    public String getTextureFile() {
        return mod_PluginsforForestry.texture;
    }
}
