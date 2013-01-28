package denoflionsx.LiquidRoundup.gfx;

import cpw.mods.fml.client.FMLTextureFX;
import java.lang.reflect.Constructor;

public class LiquidFX {
    
    public static Class TextureLiquidsFX;
    public static final String c = "forestry.core.render.TextureLiquidsFX";
    
    static{
        setup();
    }

    public static void setup() {
        try {
            TextureLiquidsFX = Class.forName(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            
        }
    }

    public static FMLTextureFX addTexture(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, int iconIndex, String textureFile) {
        FMLTextureFX tex = null;
        try {
            Constructor make = TextureLiquidsFX.getDeclaredConstructor(new Class[]{int.class, int.class, int.class, int.class, int.class, int.class, int.class, String.class});
            tex = (FMLTextureFX) make.newInstance(rMin, rMax, gMin, gMax, bMin, bMax, iconIndex, textureFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tex;
    }
    
}
