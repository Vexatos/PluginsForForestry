package denoflionsx.Managers;

import denoflionsx.API.Interfaces.IPfFContainerManager;
import denoflionsx.API.Objects.PfFColor;
import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.API.PfFManagers;
import denoflionsx.plugins.pluginCoreItems;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class PfFContainerManager implements IPfFContainerManager {

    private static ArrayList<PfFLiquid> liquids = new ArrayList();
    public static boolean hasAPILiquid = false;

    @Override
    public void addLiquid(String LiquidName, ItemStack liquid, PfFColor color) {
        liquids.add(new PfFLiquid(LiquidName, liquid.itemID, liquid, color));
    }

    @Override
    public void addLiquid(String LiquidName, ItemStack liquid, int r, int g, int b) {
        liquids.add(new PfFLiquid(LiquidName, liquid.itemID, liquid, new PfFColor("Custom Color", r, g, b)));
    }

    @Override
    public void addLiquidLate(String LiquidName, ItemStack liquid, PfFColor color) {
        addLiquid(LiquidName, liquid, color);
        forceLiquidSetup(LiquidName);
    }

    @Override
    public void addLiquidLate(String LiquidName, ItemStack liquid, int r, int g, int b) {
        addLiquid(LiquidName, liquid, r, g, b);
        forceLiquidSetup(LiquidName);
    }

    @Override
    public void forceLiquidSetup(String LiquidName) {
        for (PfFLiquid l : liquids) {
            if (l.getLiquidName().equals(LiquidName)) {
                pluginCoreItems.bfuels.addLiquid(l);
                if (PfFManagers.ItemManager.doesItemExist("woodenbucket")) {
                    pluginCoreItems.fuels.addLiquid(l);
                }
            }
        }
    }

    @Override
    public ArrayList<PfFLiquid> getLiquids() {
        return liquids;
    }
}
