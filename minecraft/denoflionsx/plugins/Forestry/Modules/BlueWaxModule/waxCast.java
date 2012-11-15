package denoflionsx.plugins.Forestry.Modules.BlueWaxModule;

import denoflionsx.API.PfFManagers;
import java.util.HashMap;
import java.util.List;
import net.minecraft.src.*;
import denoflionsx.items.multiItem;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer.LiquidManagerWrapper;
import net.minecraftforge.liquids.LiquidStack;

public class waxCast extends multiItem {

    HashMap<String, ItemStack> dropMap = new HashMap();

    public waxCast(int par1, String name) {
        super(par1, name);
        dropMap.put("Filled Wax Cast", new ItemStack(Block.ice));
        dropMap.put("Filled Wax Cast_Red", new ItemStack(Block.ice));
        dropMap.put("Lava Cast", new ItemStack(Block.obsidian));
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        tooltips(par1ItemStack, par3List);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Wax Cast"))) || par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Refractory Cast")))) {
            return this.customContainer(par1ItemStack, par2World, par3EntityPlayer);
        }
        if (par2World.isRemote) {
            return par1ItemStack;
        }
        if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Filled Wax Cast"))) || par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Filled Wax Cast_Red")))) {
            if (par3EntityPlayer.inventory.hasItemStack(PfFManagers.ItemManager.getItem("rodoffreezing"))) {
                par1ItemStack.stackSize--;
                par3EntityPlayer.dropPlayerItemWithRandomChoice(dropMap.get("Filled Wax Cast").copy(), false);
            }
        } else if (par1ItemStack.isItemEqual(new ItemStack(this, 1, this.metaMap.get("Lava Cast")))) {
            if (par3EntityPlayer.inventory.hasItemStack(PfFManagers.ItemManager.getItem("rodoffreezing"))) {
                par1ItemStack.stackSize--;
                par3EntityPlayer.dropPlayerItemWithRandomChoice(dropMap.get("Lava Cast").copy(), false);
            }
        }

        return par1ItemStack;
    }

    public ItemStack customContainer(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition obj = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (obj == null) {
            return par1ItemStack;
        }
        HashMap<String, Integer> coords = new HashMap();
        coords.put("x", obj.blockX);
        coords.put("y", obj.blockY);
        coords.put("z", obj.blockZ);
        coords.put("id", par2World.getBlockId(coords.get("x"), coords.get("y"), coords.get("z")));
        coords.put("meta", par2World.getBlockMetadata(coords.get("x"), coords.get("y"), coords.get("z")));
        // BC is trying to babysit me with that exception shit. Try-Catch time...
        LiquidContainer liq = null;
        try {
            liq = LiquidManagerWrapper.getEmptyContainer(par1ItemStack, new LiquidStack(coords.get("id"), 1));
        } catch (Exception ex) {
        }
        if (liq == null) {
            return par1ItemStack;
        }
        int index = invSearch(liq.filled, par3EntityPlayer);
        if (index != -1) {
            --par1ItemStack.stackSize;
            ++par3EntityPlayer.inventory.getStackInSlot(index).stackSize;
            par2World.setBlockAndMetadataWithNotify(coords.get("x"), coords.get("y"), coords.get("z"), 0, 0);
            return par1ItemStack;
        } else {
            --par1ItemStack.stackSize;
            int empty = emptySpace(par3EntityPlayer);
            par3EntityPlayer.inventory.setInventorySlotContents(empty, new ItemStack(liq.filled.getItem(), 1, liq.filled.getItemDamage()));
            par2World.setBlockAndMetadataWithNotify(coords.get("x"), coords.get("y"), coords.get("z"), 0, 0);
            return par1ItemStack;
        }
    }

    public void tooltips(ItemStack par1ItemStack, List par2List) {
        if (par1ItemStack.getItemDamage() == this.metaMap.get("Wax Cast")) {
            par2List.add("Fill me with water!");
        } else if (par1ItemStack.getItemDamage() == this.metaMap.get("Refractory Cast")) {
            par2List.add("Fill me with water or lava!");
        }
    }
}
