package denoflionsx.PluginsforForestry.Plugins.MFR.Straw;

import net.minecraft.entity.player.EntityPlayer;
import powercrystals.minefactoryreloaded.api.ILiquidDrinkHandler;

public class PfFStrawHandler implements ILiquidDrinkHandler {

    @Override
    public void onDrink(EntityPlayer player) {
        player.addChatMessage("Drink!");
    }
}
