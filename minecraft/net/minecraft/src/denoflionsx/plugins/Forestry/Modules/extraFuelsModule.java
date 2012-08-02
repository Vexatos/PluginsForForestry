package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;

public class extraFuelsModule extends baseModule {

    public static customFuel pumpkinJuice;
    public static customFuel melonJuice;
    private boolean isPumpkinJuiceEnabled = false;
    private boolean isMelonJuiceEnabled = false;

    public extraFuelsModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.addDefault("# These are for the Extra Fuels Module");
        this.addDefault("ExtraFuels_Enabled=" + "true");
        this.addDefault("PumpkinJuice_Enabled=" + "true");
        this.addDefault("PumpkinJuice_ItemID=" + core.ItemIDs[9]);
        this.addDefault("PumpkinJuice_MJt=" + 5);
        this.addDefault("PumpkinJuice_BurnTime=" + 1200);
        this.addDefault("PumpkinJuice_AmountPerSqueeze=" + 200);
        this.addDefault("MelonJuice_Enabled=" + "true");
        this.addDefault("MelonJuice_ItemID=" + core.ItemIDs[10]);
        this.addDefault("MelonJuice_MJt=" + 1);
        this.addDefault("MelonJuice_BurnTime=" + (1200 * 5));
        this.addDefault("MelonJuice_AmountPerSqueeze=" + 200);
    }

    @Override
    protected void init() {
        if (!this.getOptionBool("ExtraFuels_Enabled")) {
            return;
        }
        this.isPumpkinJuiceEnabled = this.getOptionBool("PumpkinJuice_Enabled");
        this.isMelonJuiceEnabled = this.getOptionBool("MelonJuice_Enabled");
        if (this.isPumpkinJuiceEnabled) {
            pumpkinJuice = new customFuel("Pumpkin Juice", this.getOptionInt("PumpkinJuice_MJt"), this.getOptionInt("PumpkinJuice_BurnTime"), customFuel.populateSprites(5), this.getOptionInt("PumpkinJuice_ItemID"),Colors.Values.ORANGE.getColor());
        }
        if (this.isMelonJuiceEnabled) {
            melonJuice = new customFuel("Melon Juice", this.getOptionInt("MelonJuice_MJt"), this.getOptionInt("MelonJuice_BurnTime"), customFuel.populateSprites(6), this.getOptionInt("MelonJuice_ItemID"),Colors.Values.PINK.getColor());
        }
        recipes();
    }

    @Override
    protected void recipes() {
    }

    public static void load(pluginBase parent) {
        baseModule b = new extraFuelsModule(parent);
        b.register();
    }
}
