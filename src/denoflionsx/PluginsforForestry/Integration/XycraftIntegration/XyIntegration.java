package denoflionsx.PluginsforForestry.Integration.XycraftIntegration;

import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Core.OmniPlantList;
import denoflionsx.PluginsforForestry.PfF;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class XyIntegration {

    public static HashMap<String, ItemStack> items;

    public static void integrate() {
        try {
            Class xy = Class.forName("soaryn.xycraft.world.WorldItems");
            if (xy != null) {
                PfF.Proxy.print("Xycraft found. Scanning for Items.");
                items = new HashMap();
                Field[] fields = xy.getDeclaredFields();
                for (Field f : fields) {
                    Object o = f.get(null);
                    if (o instanceof Item) {
                        items.put(f.getName(), new ItemStack((Item) o));
                        PfF.Proxy.print("Scanned Xycraft Item: " + f.getName());
                    }
                }
                CoreTuning.tuning.registerTunable(XyIntegration.class);
                if (Enables.AluminumCans_Integrate) {
                    PfF.Proxy.print("Assimilating Xycraft Aluminum Can.");
                    ItemStack Can = items.get("aluminumCan");
                    if (Can != null) {
                        LiquidRoundup.Proxy.ExtractItemStackSprite(Can);
                        String path = "/denoflionsx/PluginsforForestry/gfx/containers/";
                        LRManagers.Containers.registerNewContainer("Foil", new String[]{path + "can.png", path + "overlays/generic_capsule_overlay.png"}, LiquidContainerRegistry.BUCKET_VOLUME, Can, false, false, true, 64);
                    } else {
                        PfF.Proxy.print("Cannot find Xycraft Alumnium Can!");
                    }
                }
                if (Enables.Corn_Omniplant) {
                    PfF.Proxy.print("Assimilating Xycraft Corn.");
                    ItemStack corn = items.get("corn");
                    ItemStack seed = items.get("kernel");
                    if (corn != null && seed != null) {
                        OmniPlantList.OmniplantExternalPlants.addToWhiteList(seed, corn);
                    } else {
                        if (corn == null) {
                            PfF.Proxy.print("Cannot find Xycraft corn.");
                        }
                        if (seed == null){
                            PfF.Proxy.print("Cannot find Xycraft corn kernels.");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            PfF.Proxy.print("Xycraft not found! Integration aborted.");
        } catch (Exception ex) {
            PfF.Proxy.print("Xycraft not found! Integration aborted.");
        }
    }

    public static class Enables {

        @Tunable(category = "xycraft.integration")
        public static boolean AluminumCans_Integrate = true;
        @Tunable(category = "xycraft.integration")
        public static boolean Corn_Omniplant = true;
    }
}
