package denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemFoodTemplate;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.ItemStack;

public class ItemCheeseBurger extends ItemFoodTemplate {

    public ItemCheeseBurger(int id, int healAmount, float saturationModifier, int icon, String name) {
        super(id, healAmount, saturationModifier, icon, name);
    }

    public ItemCheeseBurger() {
        this(FoodTuning.Items.CheeseBurger_ItemID, FoodTuning.Food.CheeseBurger_healAmount, (float) FoodTuning.Food.CheeseBurger_saturationModifer, 65, "Cheeseburger");
    }

    public ItemCheeseBurger recipe() {
        FMLWrapper.MODE.FML.addSmelt(PfFManagers.Foods.getItemByTag("groundbeef"), new ItemStack(this));
        return this;
    }
}
