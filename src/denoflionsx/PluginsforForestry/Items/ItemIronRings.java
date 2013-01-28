package denoflionsx.PluginsforForestry.Items;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIronRings extends PfFBase {

    public ItemIronRings(int par1) {
        super(par1);
    }

    public ItemIronRings() {
        super(CoreTuning.Items.rings);
        this.add("Iron Ring", 0, 35);
    }

    public final ItemIronRings createRecipe() {
        ItemStack rings = new ItemStack(this, CoreTuning.Tuning.ringAmount, 0);
        ItemStack hammer = PfFManagers.Items.getItemByTag("blacksmithinghammer");
        FMLWrapper.MODE.FML.addRecipe(rings, new Object[]{"XIX", "IHI", "XIX", Character.valueOf('I'), Item.ingotIron, Character.valueOf('H'), hammer});
        return this;
    }
}
