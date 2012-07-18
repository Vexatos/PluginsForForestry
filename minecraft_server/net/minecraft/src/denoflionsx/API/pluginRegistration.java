package net.minecraft.src.denoflionsx.API;

import java.lang.reflect.Method;

public class pluginRegistration {

    public static boolean checkPlugin(String name) {
        boolean loaded;
        try {
            Class pluginCore = Class.forName("denoflionsx.plugins.pluginCore");
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
