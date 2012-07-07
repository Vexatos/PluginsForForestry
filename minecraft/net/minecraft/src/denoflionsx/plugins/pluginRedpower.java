package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.denLib;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;

public class pluginRedpower extends pluginBase {

    //------------------------------------------------
    // THIS PLUGIN IS DISABLED IN ALL PUBLIC RELEASES!
    //------------------------------------------------
    // This plugin is for my personal use. I will not
    // enable it for public releases because I never 
    // got permission from Elorram to relect into her code.
    //------------------------------------------------
    protected String theClass = "RedPowerBase";

    public pluginRedpower() {
        this.mod = "mod_RedPowerCore";
        this.name = "pluginRedpower";
        this.isLate = true;
        if (core.isBukkit){
            theClass = "net.minecraft.server." + theClass;
        }
        this.register();
    }

    @Override
    protected boolean init() {

        if (!detect()) {
            hooked = false;
            core.print(mod + " not found!");
            return hooked;
        }

        try {
            this.addItem("Nikolite",denLib.getItemStack(theClass, "itemNikolite"));
            this.addItem(theClass, "itemDyeIndigo", "Indigo Dye", 0);
            hooked = true;


        } catch (Exception ex) {

            ex.printStackTrace();
            hooked = false;

        } finally {
        }

        return hooked;
    }

    @Override
    protected void recipes() {
        FuelManager.copperEngineFuel.put(this.items.get("Nikolite").itemID, new EngineCopperFuel(this.items.get("Nikolite"), 1, 5000));
        ModLoader.addRecipe(new ItemStack(Item.dyePowder, 1, 4), new Object[]{
                    "XXX",
                    "XIX",
                    "XXX",
                    Character.valueOf('I'), this.items.get("Indigo Dye")});
    }

    @Override
    public void register() {
        if (!loaded) {
            if (loaded = init()) {
                recipes();
                core.print(getName() + " Loaded!");
            }
        }
    }

    @Override
    protected void defaults() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
