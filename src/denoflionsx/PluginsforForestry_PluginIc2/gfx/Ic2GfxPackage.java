package denoflionsx.PluginsforForestry_PluginIc2.gfx;

public class Ic2GfxPackage {
    
    public static String pack;
    
    static{
        Class c = Ic2GfxPackage.class;
        pack = c.getPackage().getName();
        pack = pack.replace(".", "/");
        pack = "/" + pack;
    }
    
}
