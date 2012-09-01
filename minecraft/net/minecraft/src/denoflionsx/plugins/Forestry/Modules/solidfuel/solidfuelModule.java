package net.minecraft.src.denoflionsx.plugins.Forestry.Modules.solidfuel;

import forestry.api.core.ItemInterface;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.API.PFFItems;
import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;

public class solidfuelModule extends baseModule {

    ExtendedLiquid biomass;
    ExtendedLiquid biofuel;
    ItemIDManager id1 = new ItemIDManager(1, "Biomass");
    ItemIDManager id2 = new ItemIDManager(1, "Biofuel");

    public solidfuelModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.addDefault("BiomassBar_Enabled=" + "true");
        this.addDefault("BiomassBar_ItemID=" + id1.getItemIDs().get(0));
        this.addDefault("BiomassBar_MJt=" + 5);
        this.addDefault("BiomassBar_BurnTime=" + 10000);
        this.addDefault("BiofuelBar_Enabled=" + "true");
        this.addDefault("BiofuelBar_ItemID=" + id2.getItemIDs().get(0));
        this.addDefault("BiofuelBar_MJt=" + 5);
        this.addDefault("BiofuelBar_BurnTime=" + 40000);
    }

    @Override
    protected void init() {
        if (this.getOptionBool("BiofuelBar_Enabled")) {
            addtoAPI("Biomass");
            biomass = new ExtendedLiquid(this.getOptionInt("BiomassBar_ItemID"), "Biomass", API.getItem("biomass"), this.getOptionInt("BiomassBar_BurnTime"), this.getOptionInt("BiomassBar_MJt"), solidfuelModule.fuelRBG.BIOMASS);
        }
        if (this.getOptionBool("BiofuelBar_Enabled")) {
            addtoAPI("Biofuel");
            biofuel = new ExtendedLiquid(this.getOptionInt("BiofuelBar_ItemID"), "Biofuel", API.getItem("biofuel"), this.getOptionInt("BiofuelBar_BurnTime"), this.getOptionInt("BiofuelBar_MJt"), solidfuelModule.fuelRBG.BIOFUEL);
        }
    }

    @Override
    protected void recipes() {
    }

    private void addtoAPI(String name) {
        String nameLowerCaseNoSpaces = denLib.toLowerCaseNoSpaces(name);
        // Register biomass with PfF's API so the system can find it.
        //-----------------------------------------------------------
        PFFItems.registerItem(nameLowerCaseNoSpaces, ItemInterface.getItem("liquid" + name).getItem());
        PFFItems.registerItem(nameLowerCaseNoSpaces + "can", ItemInterface.getItem("can" + name).getItem());
        PFFItems.registerItem(nameLowerCaseNoSpaces + "capsule_red", ItemInterface.getItem("refractory" + name).getItem());
        PFFItems.registerItem(nameLowerCaseNoSpaces + "capsule", ItemInterface.getItem("waxCapsule" + name).getItem());
        PFFItems.registerItem(nameLowerCaseNoSpaces + "bucket", ItemInterface.getItem("bucket" + name).getItem());
        //-----------------------------------------------------------
    }

    public static void load(pluginBase parent) {
        baseModule b = new solidfuelModule(parent);
        b.register();
    }

    public static enum fuelRBG {

        BIOMASS(118, 198, 16),
        BIOFUEL(223, 103, 7);
        private int r;
        private int b;
        private int g;
        private int color;

        private fuelRBG(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.color = Colors.convertRBG(r, g, b);
        }

        public int getB() {
            return b;
        }

        public int getG() {
            return g;
        }

        public int getR() {
            return r;
        }

        public int getColor() {
            return color;
        }
    }
}
