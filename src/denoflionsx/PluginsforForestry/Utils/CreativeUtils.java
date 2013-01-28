package denoflionsx.PluginsforForestry.Utils;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeUtils {

    public static ArrayList<ItemStack> getAllInTab(CreativeTabs tab) {
        ArrayList<ItemStack> items = new ArrayList();
        for (Item i : Item.itemsList) {
            if (i != null) {
                if (tab == i.getCreativeTab()) {
                    items.add(new ItemStack(i));
                }
            }
        }
        for (Block b : Block.blocksList) {
            if (b != null) {
                if (tab == b.getCreativeTabToDisplayOn()) {
                    items.add(new ItemStack(b));
                }
            }
        }
        return items;
    }
}
