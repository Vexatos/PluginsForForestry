package net.minecraft.src.denoflionsx.plugins.EE.Modules;

import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.denoflionsx.plugins.pluginEE;

public class VanillaValues {

    public static HashMap<String, Integer> values = new HashMap();

    public static void assignValues() {

        for (Item i : Item.itemsList) {

            if (i != null) {
                int id = i.shiftedIndex;
                String name = i.getItemName();
                int EMC = pluginEE.getEEValue(id, 0);
                //core.print(name + " " + EMC);
                values.put(name, EMC);
            }

        }

    }
}
