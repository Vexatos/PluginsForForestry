package denoflionsx.PluginsforForestry_PluginBlueFood.Items.Food;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry_PluginBlueFood.Config.FoodTuning;
import denoflionsx.PluginsforForestry_PluginBlueFood.Items.ItemFoodTemplate;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.item.ItemStack;

public class ItemCalimari extends ItemFoodTemplate {

    public ItemCalimari(int id, int healAmount, float saturationModifier, int icon, String name) {
        super(id, healAmount, saturationModifier, icon, name);
    }

    public ItemCalimari() {
        this(FoodTuning.Items.Calimari_ItemID, FoodTuning.Food.Calimari_healAmount, (float) FoodTuning.Food.Calimari_saturationModifer, 66, "Calimari");
    }
    
    public ItemCalimari recipe(){
        FMLWrapper.MODE.FML.addSmelt(PfFManagers.Foods.getItemByTag("tentacle"), new ItemStack(this));
        return this;
    }
}
