package denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items;

import denoflionsx.PluginsforForestry.API.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHammer extends ItemMeta implements IRegisterRecipe {

    public ItemHammer() {
        super(new String[]{"PluginsforForestry:smithing_hammer"}, PfFTuning.getInt(PfFTuning.Items.hammer_ItemID));
        this.createItemEntry(0, PfFTranslator.instance.translateKey("item.pff.hammer.name"));
        this.setContainerItem(this);
        this.setMaxStackSize(1);
    }

    @Override
    public void registerRecipe() {
        PfF.Proxy.registerRecipe(BarrelItems.ItemStackHammer, new Object[]{"XSX", "III", "XTX", Character.valueOf('S'), new ItemStack(Item.dyePowder), Character.valueOf('I'), new ItemStack(Item.ingotIron), Character.valueOf('T'), new ItemStack(Item.stick)});
    }
}
