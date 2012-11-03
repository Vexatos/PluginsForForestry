package denoflionsx.plugins.Forestry.Modules.ExtraFuelsModule;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.core.PfFModuleTemplate;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import net.minecraft.src.Item;

public class extraFuelsModule extends PfFModuleTemplate {

    public static customFuel pumpkinJuice;
    public static customFuel melonJuice;
    private ItemIDManager IDs_PumpkinJuice = new ItemIDManager(2, "PumpkinJuice");
    private ItemIDManager IDs_MelonJuice = new ItemIDManager(2, "MelonJuice");
    private ItemStack pumpkin = new ItemStack(Block.pumpkin);
    private ItemStack melon = new ItemStack(Item.melon);

    public extraFuelsModule(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        super.pluginLoaded(event);
    }

    @PfFSubscribe(Event = PfFEventTypes.SPECIAL)
    public void barrel(EventSpecial event) {
        if (!event.getMessage().equals(EnumEventSpecialMessages.BARREL.getMsg())) {
            return;
        }
        PfFManagers.ContainerManager.addLiquid("Pumpkin Juice", PfFManagers.ItemManager.getItem("pumpkinjuice"), PfFManagers.ColorManager.getColor(Colors.Values.ORANGE.toString()));
        PfFManagers.ContainerManager.addLiquid("Melon Juice", PfFManagers.ItemManager.getItem("melonjuice"), PfFManagers.ColorManager.getColor(Colors.Values.PINK.toString()));
    }

    @Override
    public void defaults() {
        this.config.addDefault("# These are for the Extra Fuels Module");
        this.config.addDefault("ExtraFuels_Enabled=" + "true");
        this.config.addDefault("PumpkinJuice_AmountPerSqueeze=" + 200);
        this.config.addDefault("MelonJuice_AmountPerSqueeze=" + 200);
    }

    @Override
    public void doSetup() {
        PfFEvents.specialEvent.register(this);
        pumpkinJuice = new customFuel("Pumpkin Juice", 5, 1200, customFuel.populateSprites(EnumLiquidTextures.Liquids.PUMPKINJUICE.getIndex()), this.IDs_PumpkinJuice, Colors.Values.ORANGE.getColor(), this);
        melonJuice = new customFuel("Melon Juice", 1, 6000, customFuel.populateSprites(EnumLiquidTextures.Liquids.MELONJUICE.getIndex()), this.IDs_MelonJuice, Colors.Values.PINK.getColor(), this);
    }

    @Override
    public void recipes() {
        SqueezerHelper.add(pumpkin, new LiquidStack(PfFManagers.ItemManager.getItem("pumpkinjuice").itemID, this.config.getOptionInt("PumpkinJuice_AmountPerSqueeze")));
        SqueezerHelper.add(melon, new LiquidStack(PfFManagers.ItemManager.getItem("melonjuice").itemID, this.config.getOptionInt("MelonJuice_AmountPerSqueeze")));
    }
}
