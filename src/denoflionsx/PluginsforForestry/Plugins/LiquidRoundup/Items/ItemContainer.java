package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemContainer extends ItemMeta {

    public MATERIAL madeOf;

    public ItemContainer(int par1, String texture, MATERIAL madeof) {
        super(new String[]{texture}, par1);
        this.madeOf = madeof;
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return this.icons.get(0);
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {
        return this.icons.get(0);
    }

    public static enum MATERIAL {

        wood(true),
        iron(false),
        wax(true),
        refractory(false),
        glass(true);
        private boolean doesMelt;

        private MATERIAL(boolean doesMelt) {
            this.doesMelt = doesMelt;
        }

        // Thanks NetBeans for the awesome auto-generated method name...
        public boolean isDoesMelt() {
            return doesMelt;
        }
    }
}
