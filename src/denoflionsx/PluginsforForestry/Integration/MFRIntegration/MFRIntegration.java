package denoflionsx.PluginsforForestry.Integration.MFRIntegration;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantExternal;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Integration.IIntegrationModule;
import denoflionsx.PluginsforForestry.PfF;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import powercrystals.minefactoryreloaded.api.FarmingRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.IFactoryRanchable;

public class MFRIntegration implements IIntegrationModule{

    public static HashMap<Class, IFactoryRanchable> ranchables = new HashMap();
    public static HashMap<Integer, IFactoryPlantable> plantables = new HashMap();
    public static ArrayList<OmniPlantExternal> externalCopy = new ArrayList();

    static {
        CoreTuning.tuning.registerTunable(MFRIntegration.class);
        try {
            Class c = Class.forName("powercrystals.minefactoryreloaded.animals.TileEntityRancher");
            Field f = c.getDeclaredField("ranchables");
            f.setAccessible(true);
            Object o = f.get(null);
            ranchables = (HashMap<Class, IFactoryRanchable>) o;
            //---
            c = Class.forName("powercrystals.minefactoryreloaded.plants.TileEntityPlanter");
            f = c.getDeclaredField("plantables");
            f.setAccessible(true);
            o = f.get(null);
            plantables = (HashMap<Integer, IFactoryPlantable>) o;
        } catch (ClassNotFoundException ex) {
            PfF.Proxy.print("MFR not found! Integration disabled.");
            Tuning.Integration = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void removeMooshroom() {
        if (Tuning.Integration) {
            ranchables.remove(EntityMooshroom.class);
        }
    }

    private static void reRegisterMooshroom(IFactoryRanchable moo) {
        if (Tuning.Integration) {
            removeMooshroom();
            FarmingRegistry.registerRanchable(moo);
        }
    }

    private static ArrayList<Integer> getRegisteredPlants(HashMap<Integer, IFactoryPlantable> plants) {
        ArrayList<Integer> list = new ArrayList();
        for (IFactoryPlantable p : plants.values()) {
            list.add(p.getSourceId());
        }
        return list;
    }

    @Override
    public void Integrate() {
        if (Tuning.Integration) {
            LiquidStack soup = LiquidDictionary.getLiquid("Mushroom Soup", 1000);
            reRegisterMooshroom(new RanchableMooshroom(soup));

        }
    }

    public static class Tuning {

        @Tunable(category = "MFR.integrate")
        public static boolean Integration = true;
    }
}
