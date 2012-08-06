package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import net.minecraft.src.ic2.api.Items;

public class pluginIC2 extends pluginBase {

    public static customFuel radioactive;
    private ItemIDManager ids = new ItemIDManager(2,"LiquidUranium");
    //public static Block block;

    public pluginIC2() {
        this.name = "pluginIC2";
        this.mod = "mod_IC2";
        this.config = new Config(this.name + ".cfg");
        register();
    }

    @Override
    public void register() {
        if (!loaded) {
            this.defaults();
            this.runConfig();
            if (loaded = init()) {
                recipes();
                if (this.hooked) {
                    core.print(this.name + " loaded!");
                }
            }
        }
    }

    @Override
    protected void defaults() {
        //this.config.addDefault("RadioactiveWaste_ItemID=" + core.ItemIDs[6]);
        this.config.addDefault("LavaFromUranium=" + (1000000 / 20) * (100 - 20) / 100);
        this.config.addDefault("ChanceOfGoo=" + 10);
        this.config.addDefault("AmountOfFuelPerFermentation=" + 1000);
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return false;
        }
        if (denLib.convertToBoolean(core.config.getOption("pluginIc2_Enabled"))) {
            this.addItem("Uranium", Items.getItem("uraniumIngot"));
            this.addItem("Scrap", Items.getItem("scrap"));
            this.addBlock("Reinforced Stone", Items.getItem("reinforcedStone"));
            this.addBlock("Reinforced Glass", Items.getItem("reinforcedGlass"));
            this.addItem("Plates", Items.getItem("advancedAlloy"));
            
            radioactive = new customFuel("Liquid Uranium",10,70000,customFuel.populateSprites(2),ids,Colors.Values.LIME.getColor(),this);
            
            this.hooked = true;
        }
        return this.hooked;
    }

    @Override
    protected void recipes() {
    }
        
}
