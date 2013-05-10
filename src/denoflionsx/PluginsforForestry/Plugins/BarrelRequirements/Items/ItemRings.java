package denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items;

import denoflionsx.PluginsforForestry.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRings extends ItemMeta implements IRegisterRecipe {

    public ItemRings() {
        super(new String[]{"PluginsforForestry:iron_hoop"}, PfFTuning.getInt(PfFTuning.Items.rings_ItemID));
        this.createItemEntry(0, PfF.Proxy.translate("item.pff.rings.name"));
    }

    @Override
    public void registerRecipe() {
        ItemStack s = BarrelItems.ItemStackRings.copy();
        s.stackSize = 64;
        PfF.Proxy.registerRecipe(s, new Object[]{"XXX","XHX","III", Character.valueOf('H'), BarrelItems.ItemStackHammer, Character.valueOf('I'), new ItemStack(Item.ingotIron)});
    }
}
