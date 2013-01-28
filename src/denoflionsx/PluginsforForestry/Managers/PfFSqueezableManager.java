package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezableManager;
import denoflionsx.PluginsforForestry.API.Objects.DictMap;
import denoflionsx.PluginsforForestry.API.Objects.DictMap.Dict;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import java.lang.annotation.Annotation;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PfFSqueezableManager implements IPfFSqueezableManager {

    public static final DictMap map = new DictMap();

    @Override
    public void registerSqueezeLiquid(IPfFLiquid liquid) {
        Class liquidClass = liquid.getClass();
        Annotation[] anns = liquidClass.getDeclaredAnnotations();
        for (Annotation a : anns) {
            if (a instanceof SqueezeLiquid) {
                SqueezeLiquid l = (SqueezeLiquid) a;
                for (String p : l.validplants()) {
                    List<Dict> list = PfFManagers.Squeeze.getSqueezablesForName(p);
                    if (list != null) {
                        for (Dict d : list) {
                            LiquidStack lq = LiquidDictionary.getLiquid(l.liquidname(), d.getLiquidAmount());
                            APIWrappers.forestry.squeezer.addRecipe((lq.amount / 200), new ItemStack[]{d.getStack()}, lq);
                            if (l.TEMode() == 0) {
                                APIWrappers.TE.transposer.addExtractionRecipe(lq.amount, d.getStack(), null, lq, false);
                            }else{
                                APIWrappers.TE.crucible.addRecipe(lq.amount, d.getStack(), lq);
                            }
                            PfFManagers.Events.SystemEvents.raiseAlert("SqueezeManager|registerSqueezeLiquid", liquidClass.getName(), lq);
                        }
                    } else {
                        PfF.Proxy.print("Nothing found for " + p + " in squeeze registry.");
                    }
                }
            }
        }
    }

    @Override
    public void registerSqueezable(IPfFSqueezable squeezeme) {
        registerSqueezable(squeezeme.getName(), squeezeme.getItemStack(), squeezeme.getLiquidAmount());
    }

    @Override
    public void registerSqueezable(String name, ItemStack squeezeme, int liquidAmount) {
        map.put(name, new Dict(name, squeezeme, liquidAmount));
        PfFManagers.Events.SystemEvents.raiseAlert("SqueezeManager|registerSqueezable", name, new Dict(name, squeezeme, liquidAmount));
    }

    @Override
    public DictMap getMap() {
        return map;
    }

    @Override
    public List<Dict> getSqueezablesForName(String name) {
        return (List<Dict>) map.get(name);
    }
}
