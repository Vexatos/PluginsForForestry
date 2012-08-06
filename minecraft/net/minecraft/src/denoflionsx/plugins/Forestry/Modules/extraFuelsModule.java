package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.plugins.Core.EnumLiquidTextures;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;

public class extraFuelsModule extends baseModule {

    public static customFuel pumpkinJuice;
    public static customFuel melonJuice;
    private boolean isPumpkinJuiceEnabled = false;
    private boolean isMelonJuiceEnabled = false;
    private ItemIDManager IDs_PumpkinJuice = new ItemIDManager(2,"PumpkinJuice");
    private ItemIDManager IDs_MelonJuice = new ItemIDManager(2,"MelonJuice");

    public extraFuelsModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.addDefault("# These are for the Extra Fuels Module");
        this.addDefault("ExtraFuels_Enabled=" + "true");
        this.addDefault("PumpkinJuice_AmountPerSqueeze=" + 200);
        this.addDefault("MelonJuice_AmountPerSqueeze=" + 200);
    }

    @Override
    protected void init() {
        if (!this.getOptionBool("ExtraFuels_Enabled")) {
            return;
        }

        pumpkinJuice = new customFuel("Pumpkin Juice", 5, 1200, customFuel.populateSprites(EnumLiquidTextures.Liquids.PUMPKINJUICE.getIndex()), this.IDs_PumpkinJuice, Colors.Values.ORANGE.getColor(), this.parent);


        melonJuice = new customFuel("Melon Juice", 1, 6000, customFuel.populateSprites(EnumLiquidTextures.Liquids.MELONJUICE.getIndex()), this.IDs_MelonJuice, Colors.Values.PINK.getColor(), this.parent);

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
