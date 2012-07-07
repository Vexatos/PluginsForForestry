package net.minecraft.src.denoflionsx.plugins.EE;

import forestry.api.core.ItemInterface;
import java.lang.reflect.Field;
import java.util.HashMap;

public class DefaultForestryValues {

    public static HashMap<String, Integer> values = new HashMap();
    private static Class EEMaps;
    private static Field alchemicalValues_Field;
    private static HashMap<Integer, HashMap> alchemicalValues = new HashMap();

    public static void setup() {
        hookEE();
        values.put("Bronze", getEEValue(ItemInterface.getItem("ingotBronze").getItem().shiftedIndex, 0));
        values.put("Glass", getEEValue(20, 0));
        values.put("Piston", getEEValue(33, 0));
        values.put("Stick", getEEValue(280, 0));
        values.put("Cobblestone", getEEValue(4, 0));
        values.put("Wooden Gear", (values.get("Stick") * 4));
        values.put("Stone Gear", (values.get("Cobblestone") * 4) + values.get("Wooden Gear"));
        values.put("Bronze Gear", (values.get("Bronze") * 4) + values.get("Stone Gear"));
        values.put("Biogas Engine", values.get("Piston") + values.get("Glass") + (values.get("Bronze Gear") * 2) + (values.get("Bronze") * 3));
        values.put("Copper", getEEValue(ItemInterface.getItem("ingotCopper").getItem().shiftedIndex, 0));
        values.put("Copper Gear", (values.get("Copper") * 4) + (values.get("Stone Gear")));
        values.put("Peat Engine", (values.get("Copper") * 3) + (values.get("Copper Gear") * 2) + (values.get("Piston") + (values.get("Glass"))));
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
            alchemicalValues_Field.set(null,alchemicalValues);
        } catch (Exception ex) {
        }
    }
}
