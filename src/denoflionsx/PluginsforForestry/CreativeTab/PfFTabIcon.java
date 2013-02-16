package denoflionsx.PluginsforForestry.CreativeTab;

import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Core.PfFCore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PfFTabIcon {

    public static ItemStack iconStack;

    static {
        if (CoreTuning.Enables.woodenbucket_enabled) {
            iconStack = new ItemStack(PfFCore.woodenbucket);
        } else if (CoreTuning.Enables.barrel_enabled) {
            iconStack = new ItemStack(PfFCore.barrel);
        } else if (CoreTuning.Enables.liquidvac_enabled) {
            iconStack = new ItemStack(PfFCore.barrel);
        } else {
            iconStack = new ItemStack(Item.bucketEmpty);
        }
    }
}
