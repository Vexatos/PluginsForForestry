package denoflionsx.PluginsforForestry.Plugins.BarrelRequirements;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items.BarrelItems;
import denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items.BarrelRecipe;
import denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items.ItemHammer;
import denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items.ItemRings;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import net.minecraft.item.ItemStack;

public class PluginBarrel implements IPfFPlugin {

    @Override
    public void onPreLoad() {
    }

    @Override
    public void onLoad() {
        if (PfFTuning.getInt(PfFTuning.Items.hammer_ItemID) > 0) {
            BarrelItems.ItemHammer = new ItemHammer();
            BarrelItems.ItemStackHammer = new ItemStack(BarrelItems.ItemHammer);
        }
        if (PfFTuning.getInt(PfFTuning.Items.rings_ItemID) > 0) {
            BarrelItems.ItemRings = new ItemRings();
            BarrelItems.ItemStackRings = new ItemStack(BarrelItems.ItemRings);
        }
        if (LRItems.barrelEmpty != null && BarrelItems.ItemStackHammer != null && BarrelItems.ItemStackRings != null){
            BarrelItems.barrelRecipe = new BarrelRecipe();
        }
        PfF.Proxy.ItemCollections.add(BarrelItems.class);
    }

    @Override
    public void onPostLoad() {
    }
}
