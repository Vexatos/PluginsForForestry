package denoflionsx.PluginsforForestry_PluginBuildCraft.gfx;

public class BCGfxPackage {

    public static String pack;

    static {
        Class c = BCGfxPackage.class;
        pack = c.getPackage().getName();
        pack = pack.replace(".", "/");
        pack = "/" + pack;
    }
}
