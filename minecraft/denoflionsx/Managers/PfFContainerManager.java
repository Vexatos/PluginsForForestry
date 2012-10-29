package denoflionsx.Managers;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.Interfaces.IPfFContainerManager;
import denoflionsx.API.Objects.PfFColor;
import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.API.PfFEvents;
import denoflionsx.plugins.Forestry.Modules.BlueWaxModule.BlueWaxmodule;
import denoflionsx.plugins.Core.pluginCoreItems;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class PfFContainerManager implements IPfFContainerManager {

    private static ArrayList<PfFLiquid> liquids = new ArrayList();

    public PfFContainerManager() {
        PfFEvents.specialEvent.register(this);
    }

    @PfFSubscribe(Event = PfFEventTypes.SPECIAL)
    public void onSpecialEvent(EventSpecial event) {
        if (event.getMessage().equals(EnumEventSpecialMessages.BARREL.getMsg())) {
            for (PfFLiquid l : liquids) {
                pluginCoreItems.bfuels.addLiquid(l);
            }
        } else if (event.getMessage().equals(EnumEventSpecialMessages.BUCKET.getMsg())) {
            for (PfFLiquid l : liquids) {
                pluginCoreItems.fuels.addLiquid(l);
            }
        } else if (event.getMessage().equals(EnumEventSpecialMessages.CAST.getMsg())) {
            for (PfFLiquid l : liquids) {
                BlueWaxmodule.fuels.addLiquid(l);
            }
        }
    }

    @Override
    public void addLiquid(String LiquidName, ItemStack liquid, PfFColor color) {
        liquids.add(new PfFLiquid(LiquidName, liquid.itemID, liquid, color));
        if (pluginCoreItems.bfuels != null) {
            pluginCoreItems.bfuels.addLiquid(new PfFLiquid(LiquidName, liquid.itemID, liquid, color));
        }
        if (pluginCoreItems.fuels != null) {
            pluginCoreItems.fuels.addLiquid(new PfFLiquid(LiquidName, liquid.itemID, liquid, color));
        }
        if (BlueWaxmodule.fuels != null){
            BlueWaxmodule.fuels.addLiquid(new PfFLiquid(LiquidName, liquid.itemID, liquid, color));
        }
    }

    @Override
    public void addLiquid(String LiquidName, ItemStack liquid, int r, int g, int b) {
        addLiquid(LiquidName, liquid, new PfFColor("" + r + g + b, r, g, b));
    }

    @Override
    public ArrayList<PfFLiquid> getLiquids() {
        return liquids;
    }
}
