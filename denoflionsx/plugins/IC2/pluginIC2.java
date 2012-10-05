package denoflionsx.plugins.IC2;

import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.denLib;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import denoflionsx.plugins.IC2.Items.UraniumGoo;
import ic2.api.Items;

public class pluginIC2 extends PfFPluginTemplate {

    public static customFuel radioactive;
    private ItemIDManager ids = new ItemIDManager(2, "LiquidUranium");
    private ItemIDManager gooids = new ItemIDManager(1, "UraniumGoo");
    public static UraniumGoo goo;
    public static ItemStack uranium = Items.getItem("uraniumIngot");
    public static ItemStack uraniumcell = Items.getItem("reactorUraniumSimple");

    public pluginIC2(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("RadioactiveWaste_ItemID=" + ids.getItemIDs().get(0));
        this.config.addDefault("RadioactiveGoo_ItemID=" + gooids.getItemIDs().get(0));
        this.config.addDefault("LavaFromUranium=" + (1000000 / 20) * (100 - 20) / 100);
        this.config.addDefault("ChanceOfGoo=" + 10);
        this.config.addDefault("AmountOfFuelPerFermentation=" + 1000);
        this.config.addDefault("WoodenBucketBarrelIntegration=" + "false");
    }

    @Override
    public void doSetup() {
        radioactive = new customFuel("Radioactive Waste", 10, 70000, customFuel.populateSprites(2), ids, Colors.Values.LIME.getColor(), this);
        goo = new UraniumGoo(this.config.getOptionInt("RadioactiveGoo_ItemID"), denLib.toLowerCaseNoSpaces("Uranium Goo"));
    }

    @Override
    public void recipes() {
        SqueezerHelper.add(uranium, PfFManagers.ItemManager.getItem(goo.ItemnameLowerCaseNoSpaces), this.config.getOptionInt("ChanceOfGoo"), new ItemStack(Block.lavaStill), this.config.getOptionInt("LavaFromUranium"));
        SqueezerHelper.add(uraniumcell, PfFManagers.ItemManager.getItem(goo.ItemnameLowerCaseNoSpaces));
        PfFManagers.FermenterManager.addItem(PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Uranium Goo")), this.config.getOptionInt("AmountOfFuelPerFermentation"), PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Radioactive Waste")));
        if (this.config.getOptionBool("WoodenBucketBarrelIntegration")) {
            PfFManagers.ContainerManager.addLiquid("Radioactive Waste", PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Radioactive Waste")), PfFManagers.ColorManager.getColor(Colors.Values.LIME.toString()));
        }
    }
}
