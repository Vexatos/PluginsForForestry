package denoflionsx.plugins.Core.MetaContainers;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.denLib.denLib;
import denoflionsx.items.PfFContainer;
import denoflionsx.Enums.EnumContainers;
import denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class BarrelFuels extends PfFContainer {

    public static ItemIDManager ID = new ItemIDManager(1, "BarrelFuels");

    public BarrelFuels(int par1, String name) {
        super(par1, name);
        for (PfFLiquid liquid : PfFManagers.ContainerManager.getLiquids()){
            addLiquid(liquid);
        }
    }

    public void addLiquid(PfFLiquid liquid) {
        this.add(denLib.toLowerCaseNoSpaces(liquid.getLiquidName()) + "barrel", liquid.getMeta(), EnumContainers.Containers.BARREL.getTexture(), liquid.getLiquidName() + " Barrel");
        this.renderColors.put(liquid.getMeta(), liquid.getColor().convertRBG());
        LiquidContainerSystem.barrelOverride(PfFManagers.ItemManager.getItem("barrel"),new ItemStack(this,1,liquid.getMeta()),liquid.getLiquid().itemID);
    }

    @Override
    public Item getContainerItem() {
        return PfFManagers.ItemManager.getItem("barrel").getItem();
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
        return PfFManagers.ItemManager.getItem("barrel");
    }
}
