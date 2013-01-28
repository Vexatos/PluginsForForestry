package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquidMixManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraftforge.liquids.LiquidStack;

public class PfFLiquidMixManager implements IPfFLiquidMixManager {

    public static final HashMap<String, LiquidStack> reagents = new HashMap();
    public static final HashMap<String, LiquidStack> validLiquids = new HashMap();
    public static final HashMap<String, LiquidStack> combinedLiquids = new HashMap();

    @Override
    public void createMixtures() {
        Iterator i = reagents.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            String name = String.valueOf(pairs.getKey());
            LiquidStack reagent = (LiquidStack) pairs.getValue();
            Iterator q = validLiquids.entrySet().iterator();
            while (q.hasNext()){
                 Map.Entry pairs2 = (Map.Entry) i.next();
                String lname = String.valueOf(pairs2.getKey());
                LiquidStack liquid = (LiquidStack) pairs2.getValue();
            }
        }
    }

    @Override
    public void registerLiquid(String name, LiquidStack liquid) {
        validLiquids.put(name, liquid);
    }

    @Override
    public void registerReagent(String name, LiquidStack reagent) {
        reagents.put(name, reagent);
    }
}
