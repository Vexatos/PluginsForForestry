package denoflionsx.API;

import java.lang.reflect.Method;

public class PfFAPI {

    public static final boolean verbose = false;
    
    public static boolean isPluginLoaded(String name) {
        boolean loaded;
        String Package = PfFAPI.class.getPackage().toString();
        String expectedSSP = "package denoflionsx.API";
        String pluginCore_Class = "denoflionsx.plugins.PluginRegistry";
        if (!Package.equals(expectedSSP)){
            // If this fires we're probably on bukkit.
            pluginCore_Class = "net.minecraft.server." + pluginCore_Class;
        }
        try {
            Class pluginCore = Class.forName(pluginCore_Class);
            Method isPluginAlive = pluginCore.getMethod("isPluginAlive", new Class[]{String.class});
            String l = isPluginAlive.invoke(null, name).toString();
            if (l.toLowerCase().equals("true")) {
                if (PfFAPI.verbose) {
                    System.out.println(name + " is loaded.");
                }
                loaded = true;
            } else {
                if (PfFAPI.verbose) {
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
