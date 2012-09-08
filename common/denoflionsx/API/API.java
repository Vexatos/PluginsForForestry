package denoflionsx.API;

import java.lang.reflect.Method;
import net.minecraft.src.ItemStack;

public class API {

    public static final boolean verbose = false;

    // Use this to access PFF items from outside the modules/plugins.
    // This method expects a string that matches the internal name of the item.
    // Make sure your mod loads after PFF to ensure the HashMap is populated!
    
    // All internal item names are just the display name with no capital letters or spaces.
    // So 'Rod of Freezing' would be 'rodoffreezing'. etc.
    
    // Be sure to check for nulls in case an item has been disabled by the user!
    
    public static ItemStack getItem(String s) {

        ItemStack I = PFFItems.registeredItems.get(s);

        if (I != null) {
            //System.out.println("Loaded item " + s + " from API");
            return I;
        } else {
            System.out.println("Unable to retrieve item: " + s + " from Plugins for Forestry!");
            return null;
        }
    }

    // Use this method to check if a plugin is loaded or not.
    // Please check via this before using getItem on a plugin based item!
    // Valid plugin names: BetterFarming, MFR, Forestry, IC2, Buildcraft, APS,
    // RedPowerWorld, EE.
    
    public static boolean isPluginLoaded(String name) {
        boolean loaded;
        String Package = API.class.getPackage().toString();
        String expectedSSP = "package denoflionsx.API";
        String pluginCore_Class = "denoflionsx.plugins.pluginCore";
        if (!Package.equals(expectedSSP)){
            // If this fires we're probably on bukkit.
            pluginCore_Class = "net.minecraft.server." + pluginCore_Class;
        }
        try {
            Class pluginCore = Class.forName(pluginCore_Class);
            Method isPluginAlive = pluginCore.getMethod("isPluginAlive", new Class[]{String.class});
            String l = isPluginAlive.invoke(null, name).toString();
            if (l.toLowerCase().equals("true")) {
                if (API.verbose) {
                    System.out.println(name + " is loaded.");
                }
                loaded = true;
            } else {
                if (API.verbose) {
                    System.out.println(name + " is not loaded.");
                }
                loaded = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return loaded;
    }
    
}
