package denoflionsx.Managers;

import denoflionsx.API.Interfaces.IPfFContainerManager;
import denoflionsx.API.Objects.PfFColor;
import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.API.PfFManagers;
import denoflionsx.plugins.Forestry.Modules.BlueWaxModule.BlueWaxmodule;
import denoflionsx.plugins.pluginCoreItems;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class PfFContainerManager implements IPfFContainerManager {

    private static ArrayList<PfFLiquid> liquids = new ArrayList();
    public static boolean hasAPILiquid = false;

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
