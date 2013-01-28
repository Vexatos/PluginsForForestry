package denoflionsx.LiquidRoundup.APIWrappers.Ic2;

import denoflionsx.LiquidRoundup.LiquidRoundup;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import net.minecraft.item.ItemStack;

public class Ic2 {

    public Compressor compressor = new Compressor();
    public Items_ items = new Items_();
    private boolean hasWarned = false;

    private void warning() {
        if (!hasWarned) {
            LiquidRoundup.Proxy.print("Cannot find Ic2 API!");
            hasWarned = true;
        }
    }

    public class Items_ {

        public ItemStack getItem(String tag) {
            try {
                return Items.getItem(tag);
            } catch (NoClassDefFoundError ex) {
                warning();
                return null;
            }
        }
    }

    public class Compressor {

        public void addCompressorRecipe(ItemStack input, ItemStack output) {
            try {
                Ic2Recipes.addCompressorRecipe(input, output);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }
}
