package denoflionsx.PluginsforForestry.Integration.MFRIntegration;

import denoflionsx.PluginsforForestry.PfF;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.entity.passive.EntityMooshroom;
import powercrystals.minefactoryreloaded.api.FarmingRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryRanchable;

public class MFRIntegration {

    public static HashMap<Class, IFactoryRanchable> ranchables = new HashMap();
    public static boolean integrate = true;

    static {
        try {
            Class c = Class.forName("powercrystals.minefactoryreloaded.animals.TileEntityRancher");
            Field f = c.getDeclaredField("ranchables");
            f.setAccessible(true);
            Object o = f.get(null);
            ranchables = (HashMap<Class, IFactoryRanchable>) o;
        } catch (ClassNotFoundException ex) {
            PfF.Proxy.print("MFR not found! Integration disabled.");
            integrate = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void removeMooshroom() {
        ranchables.remove(EntityMooshroom.class);
    }
    
    public static void reRegisterMooshroom(IFactoryRanchable moo){
        if (integrate){
            removeMooshroom();
            FarmingRegistry.registerRanchable(moo);
        }
    }
}
