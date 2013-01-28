package denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemFoodTemplate;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.ItemStack;

public class ItemCookedLambChop extends ItemFoodTemplate {

    public ItemCookedLambChop(int id, int healAmount, float saturationModifier, int icon, String name) {
        super(id, healAmount, saturationModifier, icon, name);
    }

    public ItemCookedLambChop() {
        this(FoodTuning.Items.CookedLambChop_ItemID, FoodTuning.Food.CookedLambChop_healAmount, (float) FoodTuning.Food.CookedLambChop_saturationModifer, 67, "Cooked Lamb Chop");
    }
    
    public ItemCookedLambChop recipe(){
        FMLWrapper.MODE.FML.addSmelt(PfFManagers.Foods.getItemByTag("lambchop"), new ItemStack(this));
        return this;
    }
}
