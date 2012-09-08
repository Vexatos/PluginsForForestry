package denoflionsx.plugins.Forestry.Modules;

import buildcraft.api.liquids.LiquidStack;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.API.API;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Colors;
import denoflionsx.plugins.Core.EnumLiquidTextures;
import denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import denoflionsx.plugins.Forestry.SqueezerWrapper;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;

public class extraFuelsModule extends baseModule {

    public static customFuel pumpkinJuice;
    public static customFuel melonJuice;
    private ItemIDManager IDs_PumpkinJuice = new ItemIDManager(2,"PumpkinJuice");
    private ItemIDManager IDs_MelonJuice = new ItemIDManager(2,"MelonJuice");
    private ItemStack pumpkin = new ItemStack(Block.pumpkin);
    private ItemStack melon = new ItemStack(Block.melon);

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
        SqueezerWrapper.add(pumpkin, new LiquidStack(API.getItem("pumpkinjuice").itemID,this.getOptionInt("PumpkinJuice_AmountPerSqueeze")));
        SqueezerWrapper.add(melon, new LiquidStack(API.getItem("melonjuice").itemID,this.getOptionInt("MelonJuice_AmountPerSqueeze")));
    }

    public static void load(pluginBase parent) {
        baseModule b = new extraFuelsModule(parent);
        b.register();
    }
}
