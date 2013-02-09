package denoflionsx.PluginsforForestry_PluginTweaks.Integration.ExtraBees;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Integration.IIntegrationModule;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginTweaks.PfFTweaks;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtraBeesIntegration implements IIntegrationModule {

    private final String target = "serumEmpty";
    private HashMap<String, ItemStack> items = new HashMap();

    static {
        CoreTuning.tuning.registerTunable(ExtraBeesIntegration.class);
    }

    @Override
    public void Integrate() {
        try {
            Class c = Class.forName("binnie.extrabees.core.ExtraBeeItem");
            if (c == null) {
                return;
            }
            PfF.Proxy.print("Scanning for Extrabees items...");
            for (Field f : c.getDeclaredFields()) {
                Item i = (Item) f.get(null);
                String msg = "";
                if (i != null) {
                    items.put(f.getName(), new ItemStack(i));
                } else {
                    msg = " Found null.";
                }
                PfF.Proxy.print("Scanned Extrabees item: " + f.getName() + "." + msg);
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
