package net.minecraft.src.denoflionsx.items;

import java.util.HashMap;
import java.util.List;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.blueswaxModule;

public class waxCast extends multiItem {

    HashMap<String, ItemStack> dropMap = new HashMap();

    public waxCast(int par1, String name) {
        super(par1, name);
        dropMap.put("Filled Wax Cast", new ItemStack(Block.ice));
        dropMap.put("Filled Wax Cast_Red",new ItemStack(Block.ice));
        dropMap.put("Lava Cast",new ItemStack(Block.obsidian));
    }

    @Override public void addInformation(ItemStack par1ItemStack, List par2List){tooltips(par1ItemStack, par2List);}

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Wax Cast"))) || par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Refractory Cast")))) {
            return this.customContainer(par1ItemStack, par2World, par3EntityPlayer);
        } else if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Filled Wax Cast"))) || par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Filled Wax Cast_Red")))) {
            if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(this,1,this.metaMap.get("Rod of Freezing")))){
                par1ItemStack.stackSize--;
                par3EntityPlayer.dropPlayerItemWithRandomChoice(dropMap.get("Filled Wax Cast").copy(), false);
            }
        } else if (par1ItemStack.isItemEqual(new ItemStack(this,1,this.metaMap.get("Lava Cast")))){
            if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(this,1,this.metaMap.get("Rod of Freezing")))){
                par1ItemStack.stackSize--;
                par3EntityPlayer.dropPlayerItemWithRandomChoice(dropMap.get("Lava Cast").copy(), false);
            }
        } else if (par1ItemStack.isItemEqual(new ItemStack(this,1,this.metaMap.get("Test")))){
            MovingObjectPosition obj = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
            int coords[] = new int[]{obj.blockX,obj.blockY,obj.blockZ};
            coords[1]++;
            par2World.setBlockAndMetadataWithNotify(coords[0], coords[1], coords[2], blueswaxModule.thatch.blockID, 0);
        }
        return par1ItemStack;
    }

    public void tooltips(ItemStack par1ItemStack, List par2List) {
        if (par1ItemStack.getItemDamage() == this.metaMap.get("Wax Cast")) {
            par2List.add("Fill me with water!");
        } else if (par1ItemStack.getItemDamage() == this.metaMap.get("Refractory Cast")) {
            par2List.add("Fill me with water or lava!");
        } else if (par1ItemStack.getItemDamage() == this.metaMap.get("Rod of Freezing")) {
            par2List.add("Used with Wax Casts.");
        }
    }
}
