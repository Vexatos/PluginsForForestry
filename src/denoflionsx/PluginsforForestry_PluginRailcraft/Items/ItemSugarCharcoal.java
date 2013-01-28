package denoflionsx.PluginsforForestry_PluginRailcraft.Items;

import denoflionsx.PluginsforForestry.Items.PfFBase;
import denoflionsx.PluginsforForestry_PluginRailcraft.Config.RailcraftTuning;
import denoflionsx.PluginsforForestry_PluginRailcraft.PfFRailcraft;

public class ItemSugarCharcoal extends PfFBase {

    public ItemSugarCharcoal(int par1) {
        super(par1);
        this.setTextureFile(PfFRailcraft.Core.spritesheet);
        this.add("Purified Sugar", 0, 1);
        this.add("Sugar Mixture", 1, 2);
        this.add("Sugar Charcoal", 2, 3);
        this.add("Sugar Coke", 3, 4);
        //--------------------------------
        if (RailcraftTuning.Enables.cactuscharcoal_enabled) {
            this.add("Cactus Dust", 4, 9);
            this.add("Purified Cactus", 5, 5);
            this.add("Cactus Mixture", 6, 6);
            this.add("Cactus Charcoal", 7, 7);
            this.add("Cactus Coke", 8, 8);
        }
    }

    public ItemSugarCharcoal() {
        this(RailcraftTuning.Items.sugarcharcoal);
    }
}
