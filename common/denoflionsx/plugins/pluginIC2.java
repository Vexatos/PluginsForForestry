//package denoflionsx.plugins;
//
//import net.minecraft.src.Block;
//import net.minecraft.src.ItemStack;
//import denoflionsx.API.API;
//import denoflionsx.core.ItemIDManager;
//import denoflionsx.core.core;
//import denoflionsx.denLib.Colors;
//import denoflionsx.denLib.Config.Config;
//import denoflionsx.denLib.denLib;
//import denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
//import denoflionsx.plugins.Forestry.SqueezerWrapper;
//import denoflionsx.plugins.IC2.UraniumGoo;
//import net.minecraft.src.ic2.api.Items;
//
//public class pluginIC2 extends pluginBase {
//
//    public static customFuel radioactive;
//    private ItemIDManager ids = new ItemIDManager(2,"LiquidUranium");
//    private ItemIDManager gooids = new ItemIDManager(1,"UraniumGoo");
//    public static UraniumGoo goo;
//    //public static Block block;
//
//    public pluginIC2() {
//        this.name = "pluginIC2";
//        this.mod = "mod_IC2";
//        this.config = new Config(this.name + ".cfg");
//        register();
//    }
//
//    @Override
//    public void register() {
//        if (!loaded) {
//            this.defaults();
//            this.runConfig();
//            if (loaded = init()) {
//                recipes();
//                if (this.hooked) {
//                    core.print(this.name + " loaded!");
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void defaults() {
//        //this.config.addDefault("RadioactiveWaste_ItemID=" + core.ItemIDs[6]);
//        this.config.addDefault("LavaFromUranium=" + (1000000 / 20) * (100 - 20) / 100);
//        this.config.addDefault("ChanceOfGoo=" + 10);
//        this.config.addDefault("AmountOfFuelPerFermentation=" + 1000);
//    }
//
//    @Override
//    protected boolean init() {
//        if (!detect()) {
//            return false;
//        }
//        if (denLib.convertToBoolean(core.config.getOption("pluginIc2_Enabled"))) {
//            this.addItem("Uranium", Items.getItem("uraniumIngot"));
//            this.addItem("uraniumCell",Items.getItem("uraniumCell"));
//            radioactive = new customFuel("Radioactive Waste",10,70000,customFuel.populateSprites(2),ids,Colors.Values.LIME.getColor(),this);
//            goo = new UraniumGoo(gooids.getItemIDs().get(0),denLib.toLowerCaseNoSpaces("Uranium Goo"));
//            this.hooked = true;
//        }
//        return this.hooked;
//    }
//
//    @Override
//    protected void recipes() {
//        SqueezerWrapper.add(this.get("Uranium"), API.getItem(goo.ItemnameLowerCaseNoSpaces), this.config.getOptionInt("ChanceOfGoo"),new ItemStack(Block.lavaStill),this.config.getOptionInt("LavaFromUranium"));
//        SqueezerWrapper.add(this.get("uraniumCell"), API.getItem(goo.ItemnameLowerCaseNoSpaces));
//    }
//        
//}
