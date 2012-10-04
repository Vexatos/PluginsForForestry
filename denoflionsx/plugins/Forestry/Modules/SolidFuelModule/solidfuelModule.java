package denoflionsx.plugins.Forestry.Modules.SolidFuelModule;

import forestry.api.core.ItemInterface;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.core.PfFModuleTemplate;
import denoflionsx.denLib.denLib;

public class solidfuelModule extends PfFModuleTemplate {

    ExtendedLiquid biomass;
    ExtendedLiquid biofuel;
    ItemIDManager id1 = new ItemIDManager(1, "Biomass");
    ItemIDManager id2 = new ItemIDManager(1, "Biofuel");

    public solidfuelModule(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("BiomassBar_Enabled=" + "true");
        this.config.addDefault("BiomassBar_ItemID=" + id1.getItemIDs().get(0));
        this.config.addDefault("BiomassBar_MJt=" + 5);
        this.config.addDefault("BiomassBar_BurnTime=" + 10000);
        this.config.addDefault("BiofuelBar_Enabled=" + "true");
        this.config.addDefault("BiofuelBar_ItemID=" + id2.getItemIDs().get(0));
        this.config.addDefault("BiofuelBar_MJt=" + 5);
        this.config.addDefault("BiofuelBar_BurnTime=" + 40000);
    }

    @Override
    public void doSetup() {
        if (this.config.getOptionBool("BiofuelBar_Enabled")) {
            addtoAPI("Biomass");
            biomass = new ExtendedLiquid(this.config.getOptionInt("BiomassBar_ItemID"), "Biomass", EnumForestryLiquids.BIOMASS.getLiquid(), this.config.getOptionInt("BiomassBar_BurnTime"), this.config.getOptionInt("BiomassBar_MJt"), solidfuelModule.fuelRBG.BIOMASS);
        }
        if (this.config.getOptionBool("BiofuelBar_Enabled")) {
            addtoAPI("Biofuel");
            biofuel = new ExtendedLiquid(this.config.getOptionInt("BiofuelBar_ItemID"), "Biofuel", EnumForestryLiquids.BIOFUEL.getLiquid(), this.config.getOptionInt("BiofuelBar_BurnTime"), this.config.getOptionInt("BiofuelBar_MJt"), solidfuelModule.fuelRBG.BIOFUEL);
        }
    }

    @Override
    public void recipes() {
    }

    private void addtoAPI(String name) {
        String nameLowerCaseNoSpaces = denLib.toLowerCaseNoSpaces(name);
        // Register biomass with PfF's API so the system can find it.
        //-----------------------------------------------------------
        PfFManagers.ItemManager.registerItem(nameLowerCaseNoSpaces, ItemInterface.getItem("liquid" + name).getItem());
        PfFManagers.ItemManager.registerItem(nameLowerCaseNoSpaces + "can", ItemInterface.getItem("can" + name).getItem());
        PfFManagers.ItemManager.registerItem(nameLowerCaseNoSpaces + "capsule_red", ItemInterface.getItem("refractory" + name).getItem());
        PfFManagers.ItemManager.registerItem(nameLowerCaseNoSpaces + "capsule", ItemInterface.getItem("waxCapsule" + name).getItem());
        PfFManagers.ItemManager.registerItem(nameLowerCaseNoSpaces + "bucket", ItemInterface.getItem("bucket" + name).getItem());
        //-----------------------------------------------------------
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
