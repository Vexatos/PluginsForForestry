package denoflionsx.PluginsforForestry_PluginBlueFood.gfx;

public class FoodGfx {

    public static String pack;

    static {
        Class c = FoodGfx.class;
        pack = c.getPackage().getName();
        pack = pack.replace(".", "/");
        pack = "/" + pack;
    }
}
