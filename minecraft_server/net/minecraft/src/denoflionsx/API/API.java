package net.minecraft.src.denoflionsx.API;

import net.minecraft.src.ItemStack;

public class API {

    // Use this to access PFF items from outside the modules/plugins.
    // This method expects a string that matches the internal name of the item.
    // Make sure your mod loads after PFF to ensure the HashMap is populated!
    
    // Liquid Peat: liquidpeat, peatcap, peatcan, peatcap_red, peatbucket, peatbottle.
    // Sugary Peat: liquidsugarypeat, sugarypeatcap, sugarypeatcan, sugarypeatcap_red, sugarypeatbucket, sugarypeatbottle, sugarypeat.
    // Blue's Wax Stuff: waxtablet, waxcast, filledwaxcast, waxcast_red, wascastfilled_red, waxcastlava_red, rodoffreezing.
    // Solid Fuel Module: solidbiofuelbar.
    // Buildcraft Milk Module: bcliquidmilk, bcmilkcap, bcmilkcan, bcmilkcap_red, bcmilkbottle.
    // Citrus Juice: citrusjuice, citruscap, citruscan, citruscap_red, citrusbucket, citrusbottle.
    // Radioactive Waste: radioactivewaste, radioactivegoo, containmentbarrel, filledcontainmentbarrel.
    // Core: liquidvacuum, woodenbucket, filledwoodenbucket, milkbag.
    // APS: heavywatercap, heavywatercan, heavywatercap_red.
    // Obsolete: milkcan_depricated, milkcap_depricated, milkcap_red_depricated, milkbottle_depricated.
    
    public static ItemStack getItem(String s) {

        ItemStack I = PFFItems.registeredItems.get(s);

        if (I != null) {
            return I;
        } else {
            System.out.println("Unable to retrieve item: " + s + " from Plugins for Forestry!");
            return null;
        }

    }
}
