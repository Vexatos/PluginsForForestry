package denoflionsx.PluginsforForestry.Plugins.Forestry.Utils;

import denoflionsx.PluginsforForestry.Utils.UtilsTemplate;
import forestry.api.core.ItemInterface;
import net.minecraft.item.ItemStack;

public class ForestryUtils extends UtilsTemplate {

    public ForestryUtils() {
        super("Forestry");
    }

    public ItemStack getItemStack(String name) {
        try {
            return ItemInterface.getItem(name);
        } catch (NoClassDefFoundError ex) {
            this.doAlert();
            return null;
        }
    }
}
