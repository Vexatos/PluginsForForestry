package denoflionsx.PluginsforForestry.Integration.AEIntegration;

import denoflionsx.PluginsforForestry.Integration.IIntegrationModule;
import denoflionsx.PluginsforForestry.Integration.XycraftIntegration.XyIntegration;
import denoflionsx.PluginsforForestry.PfF;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class AEIntegration implements IIntegrationModule {

    public static HashMap<String, ItemStack> items = new HashMap();
    private static final String target = "matQuartz";

    @Override
    public void Integrate() {
        try {
            Class c = Class.forName("appeng.api.Materials");
            if (c == null) {
                return;
            } else {
                PfF.Proxy.print("Scanning for AE items...");
                for (Field f : c.getDeclaredFields()) {
                    Object o = f.get(null);
                    if (o != null) {
                        if (o instanceof ItemStack) {
                            items.put(f.getName(), (ItemStack) o);
                            PfF.Proxy.print("Scanned AE item: " + f.getName());
                        }
                    }
                }
                ItemStack q = items.get(target);
                if (q != null){
                    XyIntegration.altQuartz = q;
                }
            }
        } catch (Exception ex) {
            PfF.Proxy.print("Cannot find Appeng. Integration disabled.");
        }
    }
}
