package denoflionsx.PluginsforForestry.EventHandler;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.denLibMod;
import java.util.ArrayList;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.OreDictionary;

public class DictionaryPrint implements IdenWorldEventHandler {

    private ArrayList<OreDictionary.OreRegisterEvent> events = new ArrayList();

    public DictionaryPrint() {
        denLibMod.Proxy.registerForgeSubscribe(this);
    }

    @Override
    public void onWorldEnded() {
    }

    @Override
    public void onWorldLoaded() {
        PfF.Proxy.print("Printing entire OreDictionary to console...");
        for (OreDictionary.OreRegisterEvent e : events) {
            PfF.Proxy.print(e.Name);
        }
        WorldEventHandler.unregisterHandler(this);
    }

    @ForgeSubscribe
    public void onOreDict(OreDictionary.OreRegisterEvent event) {
        events.add(event);
    }
}
