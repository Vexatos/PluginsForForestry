package denoflionsx.plugins.Core.Items.MetaContainers;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.denLib.denLib;
import denoflionsx.items.PfFContainer;
import denoflionsx.Enums.EnumContainers.Containers;
import denoflionsx.plugins.Forestry.Utility.LiquidContainerSystem;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class WoodenBucketFuels extends PfFContainer {

    public static ItemIDManager ID = new ItemIDManager(1, "WoodenBucketFuel");

    public WoodenBucketFuels(int par1, String name) {
        super(par1, name);
        this.setContainerItem(PfFManagers.ItemManager.getItem("woodenbucket").getItem());
        this.setMaxStackSize(1);
        for (PfFLiquid l : PfFManagers.ContainerManager.getLiquids()) {
            addLiquid(l);
        }
    }

    @Override
    public Item getContainerItem() {
        return PfFManagers.ItemManager.getItem("woodenbucket").getItem();
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
        return PfFManagers.ItemManager.getItem("woodenbucket").copy();
    }

    public final void addLiquid(PfFLiquid liquid) {
        this.add(denLib.toLowerCaseNoSpaces(liquid.getLiquidName()) + "bucket", liquid.getMeta(), Containers.BUCKET.getTexture(), liquid.getLiquidName() + " Bucket");
        this.renderColors.put(liquid.getMeta(), liquid.getColor().convertRBG());
        LiquidContainerSystem.bucketWithOverride(new ItemStack(this, 1, liquid.getMeta()), liquid.getLiquid(), PfFManagers.ItemManager.getItem("woodenbucket"));
    }
}
