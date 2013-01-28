package denoflionsx.PluginsforForestry_PluginBlueFood.Items;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Items.PfFBase;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.PfFFood;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUncooked extends PfFBase {

    public ItemUncooked(int par1) {
        super(par1);
        this.setTextureFile(PfFFood.Core.spritesheet);
    }

    public ItemUncooked() {
        this(FoodTuning.Items.Uncooked_ItemID);
        this.add("Flour", 0, 34);
        this.add("Sausage", 1, 48);
        this.add("Ground Beef", 2, 49);
        this.add("Tentacle", 3, 50);
        this.add("Lamb Chop", 4, 51);
    }

    @Override
    public void add(String name, int meta, int texture) {
        super.add(name, meta, texture);
        PfFManagers.Foods.registerItem(denLib.toLowerCaseNoSpaces(name), new ItemStack(this,1,meta));
    }

    public ItemUncooked Recipes() {
        ItemStack w = new ItemStack(Item.wheat);
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(this, FoodTuning.Tuning.Flour_AmountPerCraft, 0), new Object[]{w.copy(),w.copy(),w.copy(),w.copy()});
        return this;
    }
}
