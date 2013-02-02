package denoflionsx.PluginsforForestry_PluginTweaks.Integration.ExtraBees;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginTweaks.PfFTweaks;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtraBeesIntegration {

    private static final String target = "serumEmpty";
    private static HashMap<String, ItemStack> items = new HashMap();
    
    static{
        CoreTuning.tuning.registerTunable(ExtraBeesIntegration.class);
    }

    public static void Integrate() {
        try {
            Class c = Class.forName("binnie.extrabees.core.ExtraBeeItem");
            if (c == null) {
                return;
            }
            PfF.Proxy.print("Scanning for Extrabees items...");
            for (Field f : c.getDeclaredFields()) {
                items.put(f.getName(), new ItemStack((Item) f.get(null)));
                PfF.Proxy.print("Scanned Extrabees item: " + f.getName());
            }
            ItemStack vial = items.get(target);
            if (vial != null) {
                PfF.Proxy.print("Enabling stack size modification of Extrabees Empty Serum Vials.");
                PfFTweaks.Proxy.changeMaxStack(vial.getItem(), Tweaks.SerumVials_StackSize);
            }
        } catch (Exception ex) {
            PfF.Proxy.print("Cannot find extrabees. Integration disabled.");
        }
    }

    public static class Tweaks {

        @Tunable(category = "tweaks.tweaks.extrabees")
        public static int SerumVials_StackSize = 32;
    }
}
