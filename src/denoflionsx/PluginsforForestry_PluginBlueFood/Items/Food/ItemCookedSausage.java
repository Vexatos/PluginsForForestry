package denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemFoodTemplate;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.ItemStack;

public class ItemCookedSausage extends ItemFoodTemplate {

    public ItemCookedSausage(int id, int healAmount, float saturationModifier, int icon, String name) {
        super(id, healAmount, saturationModifier, icon, name);
    }

    public ItemCookedSausage() {
        this(FoodTuning.Items.CookedSausage_ItemID, FoodTuning.Food.CookedSausage_healAmount, (float) FoodTuning.Food.CookedSausage_saturationModifer, 64, "Cooked Sausage");
    }
    
    public ItemCookedSausage recipe(){
        FMLWrapper.MODE.FML.addSmelt(PfFManagers.Foods.getItemByTag("sausage"), new ItemStack(this));
        return this;
    }
}
