package net.minecraft.src.denoflionsx.plugins;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.EE.Modules.BuildcraftEMCModule;
import net.minecraft.src.denoflionsx.plugins.EE.Modules.ForestryEMCModule;
import net.minecraft.src.denoflionsx.plugins.EE.customEMCParser;

public class pluginEE extends pluginBase {

    private static Class EEMaps;
    private static Field alchemicalValues_Field;
    private static HashMap<Integer, HashMap> alchemicalValues = new HashMap();

    public pluginEE() {
        this.name = "pluginEE";
        this.mod = "mod_EE";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!this.loaded) {
            this.defaults();
            ForestryEMCModule.load(this);
            BuildcraftEMCModule.load(this);
            this.runConfig();
            if (this.loaded = init()) {
                recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    @Override
    protected void defaults() {
        this.config.addDefault("[EE Plugin Options]");
        this.config.addDefault("LoadIntegrationModules=true");
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return this.hooked;
        }
        if (this.getOptionBool("LoadIntegrationModules")) {
            this.registerModules();
        }
        this.hooked = true;
        return this.hooked;
    }

    public static void readEMC(Config recipes) {
        customEMCParser p = new customEMCParser();
        Iterator i = recipes.Options.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            String value = pairs.getValue().toString();
            p.parse(value);
        }
        Iterator q = p.Values.entrySet().iterator();
        while (q.hasNext()) {
            Map.Entry pairs = (Map.Entry) q.next();
            HashMap<Integer, Integer> t = (HashMap) pairs.getValue();
            Integer id = Integer.valueOf(pairs.getKey().toString());
            Iterator w = t.entrySet().iterator();
            while (w.hasNext()) {
                Map.Entry pairs2 = (Map.Entry) w.next();
                Integer dmg = Integer.valueOf(pairs2.getKey().toString());
                Integer value = Integer.valueOf(pairs2.getValue().toString());
                addEMC(id, dmg, value);
            }
        }
    }

    protected static void addEMC(int id, int dmg, int v) {
        try {
            // core.print("Damage: " + dmg);
            EEMaps = Class.forName("ee.EEMaps");
            alchemicalValues_Field = EEMaps.getField("alchemicalValues");
            alchemicalValues = (HashMap) alchemicalValues_Field.get(null);
            HashMap<Integer, Integer> temp = new HashMap();
            if (alchemicalValues.get(id) != null) {
                //core.print("Duplicate EMC found.");
                temp = alchemicalValues.get(id);
            }
            temp.put(dmg, v);
            alchemicalValues.put(id, temp);
            alchemicalValues_Field.set(null, alchemicalValues);
            //core.print(temp.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //core.print("Added Alchemical Value for: " + id + " " + dmg);
        }
    }

    protected void blacklistItem(Item i) {
        try {
            EEMaps = Class.forName("ee.EEMaps");
            Field Values = EEMaps.getField("alchemicalValues");
            Values.setAccessible(true);
            alchemicalValues = (HashMap) Values.get(null);
            HashMap Temp = new HashMap();
            Temp.put(0, 0);
            alchemicalValues.remove(i.shiftedIndex);
            alchemicalValues.put(i.shiftedIndex, Temp);
            Values.set(null, alchemicalValues);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            core.print("Removed EMC Value for " + i.getItemName());
        }
    }

    protected void blacklistItem(Block i) {
        try {
            EEMaps = Class.forName("ee.EEMaps");
            Field Values = EEMaps.getField("alchemicalValues");
            Values.setAccessible(true);
            alchemicalValues = (HashMap) Values.get(null);
            HashMap Temp = new HashMap();
            Temp.put(0, 0);
            alchemicalValues.remove(i.blockID);
            alchemicalValues.put(i.blockID, Temp);
            Values.set(null, alchemicalValues);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            core.print("Removed EMC Value for " + i.getBlockName());
        }
    }

    protected void killEEItem(String i) {
        try {
            Class EEItems = Class.forName("ee.EEItem");
            Field item = EEItems.getField(i);
            item.setAccessible(true);
            Item z = (Item) item.get(null);
            int id = z.shiftedIndex;
            Item.itemsList[id] = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            core.print("Killed EE Item " + i);
        }

    }

    private static void hookEE() {
        try {
            EEMaps = Class.forName("ee.EEMaps");
            alchemicalValues_Field = EEMaps.getField("alchemicalValues");
            alchemicalValues = (HashMap) alchemicalValues_Field.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getEEValue(int id, int dmg) {
        hookEE();
        HashMap<Integer, Integer> h = alchemicalValues.get(id);
        int value = h.get(dmg);
        return value;
    }

    public static void forceSetValue(int id, HashMap<Integer, Integer> values) {
        try {
            // Recall EE hook to refresh values.
            hookEE();
            alchemicalValues.put(id, values);
            alchemicalValues_Field.set(null, alchemicalValues);
        } catch (Exception ex) {
        }
    }

    public static int flatten(int[] i, int div) {
        int a = 0;
        for (int z : i) {
            //core.print(String.valueOf(z));
            a = a + z;
        }
        float a2 = (float)a;
        float div2 = (float)div;
        //core.print(String.valueOf(a));
        float f = a2 / div2;
        //core.print(String.valueOf(f));
        Double d = Math.ceil(f);
        //core.print(String.valueOf(d));
        a = d.intValue();
        //core.print(String.valueOf(a));
        return a;
    }

    @Override
    protected void recipes() {
    }
}
