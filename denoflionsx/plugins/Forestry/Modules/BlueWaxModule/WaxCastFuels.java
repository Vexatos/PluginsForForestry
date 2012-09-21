package denoflionsx.plugins.Forestry.Modules.BlueWaxModule;

import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.core;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;
import denoflionsx.items.PfFContainer;
import denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class WaxCastFuels extends PfFContainer {

    public WaxCastFuels(int par1, String name) {
        super(par1, name);
    }

    public void addLiquid(PfFLiquid liquid) {
        this.add(liquid.getLiquidName() + " Cast", liquid.getMeta(), EnumCastTextures.REFRACTORY.getIndex());
        this.renderColors.put(liquid.getMeta(), liquid.getColor().convertRBG());
        LiquidContainerSystem.genericCapWithOverride(new ItemStack(this, 1, liquid.getMeta()), PfFManagers.ItemManager.getItem("waxcast_red"), liquid.getLiquid().itemID);
        core.print(denLib.toLowerCaseNoSpaces(liquid.getLiquidName()) + "bar");
        if (PfFManagers.ItemManager.doesItemExist(denLib.toLowerCaseNoSpaces(liquid.getLiquidName()) + "bar")) {
            FMLWrapper.MODE.FML.addShapelessRecipe(PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces(liquid.getLiquidName()) + "bar"), new Object[]{new ItemStack(this, 1, liquid.getMeta()), PfFManagers.ItemManager.getItem("rodoffreezing")});
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote) {
            return par1ItemStack;
        }
        if (par3EntityPlayer.inventory.hasItemStack(PfFManagers.ItemManager.getItem("rodoffreezing"))) {
            String name = denLib.getItemDisplayName(par1ItemStack);
            name = denLib.removeDotItemFromName(name);
            name = name.substring(0, name.length() - 4);
            name = name + "bar";
            if (PfFManagers.ItemManager.doesItemExist(name)) {
                par3EntityPlayer.dropPlayerItem(PfFManagers.ItemManager.getItem(name));
                par1ItemStack.stackSize--;
            }
        }
        return par1ItemStack;
    }
}
