package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.util.Icon;

public class ItemContainer extends ItemMeta {

    public ItemContainer(int par1, String texture) {
        super(new String[]{texture}, par1);
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return this.icons.get(0);
    }
}
