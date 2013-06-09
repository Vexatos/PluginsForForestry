package denoflionsx.PluginsforForestry.API;

import cpw.mods.fml.common.event.FMLInterModComms;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPluginManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.creativetab.CreativeTabs;

public class PfFAPI {

    //----------------------------
    public static Object instance;
    //----------------------------
    public static IPfFPluginManager plugins;
    //----------------------------
    public static CreativeTabs tab;
    
    // Use me during init!
    public static void sendLiquidBanRequest(String container, String liquidDictionaryTag) {
        FMLInterModComms.sendMessage("PluginsforForestry", container, liquidDictionaryTag);
    }
    
    public static String[] getContainerTagsForBanRequest() {
        ArrayList<String> names = new ArrayList();
        try {
            Class c = Class.forName("denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.Blacklists");
            for (Field f : c.getDeclaredFields()) {
                Object o = f.get(null);
                if (o instanceof String[]) {
                    names.add(f.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return names.toArray(new String[names.size()]);
    }
}
