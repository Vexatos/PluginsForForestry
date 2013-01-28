package denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food;

import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemFoodTemplate;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCookedEgg extends ItemFoodTemplate {

    public ItemCookedEgg(int id, int healAmount, float saturationModifier, int icon, String name) {
        super(id, healAmount, saturationModifier, icon, name);
        this.setCreativeTab(PfF.Core.tab);
    }

    public ItemCookedEgg() {
        this(FoodTuning.Items.CookedEgg_ItemID, FoodTuning.Food.CookedEgg_healAmount, (float) FoodTuning.Food.CookedEgg_saturationModifier, 32, "Fried Egg");
    }
    
    public ItemCookedEgg recipe(){
        FMLWrapper.MODE.FML.addSmelt(new ItemStack(Item.egg), new ItemStack(this));
        return this;
    }
}
