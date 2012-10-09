package denoflionsx.Managers;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.Interfaces.IPfFContainerManager;
import denoflionsx.API.Objects.PfFColor;
import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
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
    public void onSpecialEvent(EventSpecial event){
        if (event.getMessage().toLowerCase().contains("barrel")){
            for (PfFLiquid l : liquids){
                pluginCoreItems.bfuels.addLiquid(l);
            }
        }else if (event.getMessage().toLowerCase().contains("bucket")){
            for (PfFLiquid l : liquids){
                pluginCoreItems.fuels.addLiquid(l);
            }
        }else if (event.getMessage().toLowerCase().contains("cast")){
            for (PfFLiquid l : liquids){
                BlueWaxmodule.fuels.addLiquid(l);
            }
        }
    }

    @Override
    public void addLiquid(String LiquidName, ItemStack liquid, PfFColor color) {
        liquids.add(new PfFLiquid(LiquidName,liquid.itemID,liquid,color));
        forceLiquidSetup(LiquidName);
    }

    @Override
    public void addLiquid(String LiquidName, ItemStack liquid, int r, int g, int b) {
        liquids.add(new PfFLiquid(LiquidName,liquid.itemID,liquid,new PfFColor("" + r + g + b,r,g,b)));
        forceLiquidSetup(LiquidName);
    }

    @Override
    public void forceLiquidSetup(String LiquidName) {
        for (PfFLiquid l : liquids) {
            if (l.getLiquidName().equals(LiquidName)) {
                if (PfFManagers.ItemManager.doesItemExist("barrelfuels")) {
                    pluginCoreItems.bfuels.addLiquid(l);
                }
                if (PfFManagers.ItemManager.doesItemExist("woodenbucket")) {
                    pluginCoreItems.fuels.addLiquid(l);
                }
                if (PfFManagers.ItemManager.doesItemExist("waxcast_red")) {
                    BlueWaxmodule.fuels.addLiquid(l);
                }
            }
        }
    }

    @Override
    public ArrayList<PfFLiquid> getLiquids() {
        return liquids;
    }
}
