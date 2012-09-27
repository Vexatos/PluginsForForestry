package denoflionsx.items.Containers;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.core;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.plugins.Forestry.CarpenterHelper;
import forestry.api.core.ItemInterface;
import net.minecraft.src.Item;

public class InfusionBar {

    private static ItemStack bar = PfFManagers.ItemManager.getItem("infusionbar");
    public static MODE mode = MODE.WORKBENCH;

    public static void recipe() {
        core.PfFCore.config.addDefault("# Modes are WORKBENCH and CARPENTER");
        core.PfFCore.config.addDefault("InfusionBarMode=WORKBENCH");
        for (MODE m : MODE.values()) {
            if (m.toString().equals(core.PfFCore.config.getOption("InfusionBarMode"))) {
                mode = m;
            }
        }
        Object[] o = new Object[]{
            "PPP",
            "PAP",
            "PPP",
            Character.valueOf('A'), ItemInterface.getItem("ash"),
            Character.valueOf('P'), Item.paper};
        Object[] o2 = new Object[]{
            "AAA",
            "AAA",
            "AAA",
            Character.valueOf('A'), ItemInterface.getItem("ash")};
        try {
            if (bar != null) {
                if (mode.equals(MODE.WORKBENCH)) {
                    FMLWrapper.MODE.FML.addRecipe(bar, o);
                    FMLWrapper.MODE.FML.addRecipe(bar, o2);
                } else {
                    CarpenterHelper.addRecipeNoLiquid(bar, o);
                    CarpenterHelper.addRecipeNoLiquid(bar, o2);
                }
            }else{
                core.print("If you're seeing this: Infusion bar came up null. Please alert den.");
            }

        } catch (Exception ex) {
            // For some reason id conflicts from other mod blocks triggers this error.
            // I have no idea why.
            core.print("If you're seeing this text something went wrong.");
        }

    }

    public static enum MODE {

        WORKBENCH,
        CARPENTER;
    }
}
