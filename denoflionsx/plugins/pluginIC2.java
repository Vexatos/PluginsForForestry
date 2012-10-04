package denoflionsx.plugins;

import denoflionsx.Old.pluginBase;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumModIDs;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;
import denoflionsx.Old.customFuel_OLD;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import denoflionsx.plugins.IC2.UraniumGoo;
import ic2.api.Items;

public class pluginIC2 extends pluginBase {

    public static customFuel_OLD radioactive;
    private ItemIDManager ids = new ItemIDManager(2, "LiquidUranium");
    private ItemIDManager gooids = new ItemIDManager(1, "UraniumGoo");
    public static UraniumGoo goo;
    //public static Block block;

    public pluginIC2() {
        this.name = "pluginIC2";
        this.mod = EnumModIDs.MODS.IC2.getID();
        this.config = new Config(this.name + ".cfg");
        register();
    }

    @Override
    protected void defaults() {
        this.config.addDefault("RadioactiveWaste_ItemID=" + ids.getItemIDs().get(0));
        this.config.addDefault("RadioactiveGoo_ItemID=" + gooids.getItemIDs().get(0));
        this.config.addDefault("LavaFromUranium=" + (1000000 / 20) * (100 - 20) / 100);
        this.config.addDefault("ChanceOfGoo=" + 10);
        this.config.addDefault("AmountOfFuelPerFermentation=" + 1000);
        this.config.addDefault("WoodenBucketBarrelIntegration=" + "false");
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return false;
        }
        this.addItem("Uranium", Items.getItem("uraniumIngot"));
        this.addItem("uraniumCell", Items.getItem("reactorUraniumSimple"));
        radioactive = new customFuel_OLD("Radioactive Waste", 10, 70000, customFuel_OLD.populateSprites(2), ids, Colors.Values.LIME.getColor(), this);
        goo = new UraniumGoo(gooids.getItemIDs().get(0), denLib.toLowerCaseNoSpaces("Uranium Goo"));
        this.hooked = true;
        return this.hooked;
    }

    @Override
    protected void recipes() {
        SqueezerHelper.add(this.get("Uranium"), PfFManagers.ItemManager.getItem(goo.ItemnameLowerCaseNoSpaces), this.config.getOptionInt("ChanceOfGoo"), new ItemStack(Block.lavaStill), this.config.getOptionInt("LavaFromUranium"));
        SqueezerHelper.add(this.get("uraniumCell"), PfFManagers.ItemManager.getItem(goo.ItemnameLowerCaseNoSpaces));
        PfFManagers.FermenterManager.addItem(PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Uranium Goo")), this.getOptionInt("AmountOfFuelPerFermentation"), PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Radioactive Waste")));
        if (this.config.getOptionBool("WoodenBucketBarrelIntegration")) {
            PfFManagers.ContainerManager.addLiquid("Radioactive Waste", PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Radioactive Waste")), PfFManagers.ColorManager.getColor(Colors.Values.LIME.toString()));
        }
    }
}
