package denoflionsx.plugins.EE.Modules;

import java.io.File;
import java.util.HashMap;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import denoflionsx.plugins.pluginEE;

public class BuildcraftEMCModule extends baseModule {

    private String d = ",";
    public HashMap<String, Integer> values = new HashMap();
    public static Config recipes = new Config("pluginEE_BuildcraftEMCValues.cfg");
    private String Core = "";
    private String Energy = "";
    private String Transport = "";
    private String Factory = "";
    private String Builders = "";
    private String gearSuffix = "GearItem";
    private String engine = "engineBlock";
    private String waterproof = "pipeWaterproof";
    private int engineID = 0;
    private HashMap<String, Integer> damage = new HashMap();
    private boolean isTest = false;

    public BuildcraftEMCModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
    }

    @Override
    protected void init() {
        Core = "BuildCraftCore";
        Energy = "BuildCraftEnergy";
        Transport = "BuildCraftTransport";
        Factory = "BuildCraftFactory";
        Builders = "BuildCraftBuilders";

        if (core.isBukkit) {
            Core = core.BukkitShift(Core);
            Energy = core.BukkitShift(Energy);
            Transport = core.BukkitShift(Transport);
            Factory = core.BukkitShift(Factory);
            Builders = core.BukkitShift(Builders);
        }

        // Misc
        values.put("Iron", pluginEE.getEEValue(Item.ingotIron.shiftedIndex, 0));
        values.put("Gold", pluginEE.getEEValue(Item.ingotGold.shiftedIndex, 0));
        values.put("Diamond", pluginEE.getEEValue(Item.diamond.shiftedIndex, 0));
        values.put("Wood Plank", pluginEE.getEEValue(Block.planks.blockID, 0));
        values.put("Cobblestone", pluginEE.getEEValue(Block.cobblestone.blockID, 0));
        values.put("Stone", pluginEE.getEEValue(Block.stone.blockID, 0));
        values.put("Stick", pluginEE.getEEValue(Item.stick.shiftedIndex, 0));
        values.put("Piston", pluginEE.getEEValue(Block.pistonBase.blockID, 0));
        values.put("Glass", pluginEE.getEEValue(Block.glass.blockID, 0));
        values.put("Cactus", pluginEE.getEEValue(Block.cactus.blockID, 0));
        values.put("Obsidian", pluginEE.getEEValue(Block.obsidian.blockID, 0));
        values.put("Redstone", pluginEE.getEEValue(Item.redstone.shiftedIndex, 0));
        values.put("Iron Pick", pluginEE.getEEValue(Item.pickaxeSteel.shiftedIndex, 0));
        values.put("Workbench", pluginEE.getEEValue(Block.workbench.blockID, 0));
        values.put("Diamond Pick", pluginEE.getEEValue(Item.pickaxeDiamond.shiftedIndex, 0));
        values.put("Lapiz", pluginEE.getEEValue(Item.dyePowder.shiftedIndex, 4));
        values.put("Redstone Torch", pluginEE.getEEValue(Block.torchRedstoneActive.blockID, 0));
        values.put("Ink", pluginEE.getEEValue(Item.dyePowder.shiftedIndex, 0));
        values.put("Chest", pluginEE.getEEValue(Block.chest.blockID, 0));
        values.put("Yellow", pluginEE.getEEValue(Item.dyePowder.shiftedIndex, 11));
        values.put("Paper", pluginEE.getEEValue(Item.paper.shiftedIndex, 0));

        // Gears
        values.put("Wooden Gear", (values.get("Stick") * 4));
        values.put("Stone Gear", (values.get("Cobblestone") * 4) + values.get("Wooden Gear"));
        values.put("Iron Gear", (values.get("Stone Gear")) + (values.get("Iron") * 4));
        values.put("Gold Gear", (values.get("Iron Gear")) + (values.get("Gold") * 4));
        values.put("Diamond Gear", (values.get("Gold Gear")) + (values.get("Diamond") * 4));

        // Engines
        damage.put("Redstone", 0);
        damage.put("Stone", 1);
        damage.put("Iron", 2);
        engineID = getBlockID(Energy, engine);

        values.put("Redstone Engine", (values.get("Wooden Gear") * 2) + (values.get("Wood Plank") * 3) + (values.get("Piston")) + (values.get("Glass")));
        values.put("Stone Engine", (values.get("Stone Gear") * 2) + (values.get("Cobblestone") * 3) + (values.get("Piston")) + (values.get("Glass")));
        values.put("Iron Engine", (values.get("Iron Gear") * 2) + (values.get("Iron") * 3) + (values.get("Piston")) + (values.get("Glass")));

        // Parts
        values.put("Waterproof", (values.get("Cactus")));
        values.put("Tank", (values.get("Glass")) * 8);
        values.put("Marker", (values.get("Redstone Torch")) + (values.get("Lapiz")));
        values.put("Template", (values.get("Paper") * 8) + (values.get("Ink")));

        // Pipes
        int temp[] = new int[]{values.get("Wood Plank"), values.get("Wood Plank"), values.get("Glass")};
        int woodpipe = pluginEE.flatten(temp, 8);
        values.put("Wood Pipe", woodpipe);
        values.put("Cobblestone Pipe", pluginEE.flatten(new int[]{values.get("Cobblestone") * 2, values.get("Glass")}, 8));
        values.put("Stone Pipe", pluginEE.flatten(new int[]{values.get("Stone") * 2, values.get("Glass")}, 8));
        values.put("Gold Pipe", pluginEE.flatten(new int[]{values.get("Gold") * 2, values.get("Glass")}, 8));
        values.put("Iron Pipe", pluginEE.flatten(new int[]{values.get("Iron") * 2, values.get("Glass")}, 8));
        values.put("Diamond Pipe", pluginEE.flatten(new int[]{values.get("Diamond") * 2, values.get("Glass")}, 8));
        values.put("Obsidian Pipe", pluginEE.flatten(new int[]{values.get("Obsidian") * 2, values.get("Glass")}, 8));
        // Waterproof Pipes
        values.put("Wooden Waterproof Pipe", values.get("Wood Pipe") + values.get("Waterproof"));
        values.put("Cobblestone Waterproof Pipe", values.get("Cobblestone Pipe") + values.get("Waterproof"));
        values.put("Stone Waterproof Pipe", values.get("Stone Pipe") + values.get("Waterproof"));
        values.put("Gold Waterproof Pipe", values.get("Gold Pipe") + values.get("Waterproof"));
        values.put("Iron Waterproof Pipe", values.get("Iron Pipe") + values.get("Waterproof"));
        // Conductive Pipes
        values.put("Wooden Conductive Pipe", values.get("Wood Pipe") + values.get("Redstone"));
        values.put("Stone Conductive Pipe", values.get("Stone Pipe") + values.get("Redstone"));
        values.put("Golden Conductive Pipe", values.get("Gold Pipe") + values.get("Redstone"));
        // Machines
        values.put("Mining Well", (values.get("Iron") * 6) + (values.get("Redstone")) + (values.get("Iron Gear")) + (values.get("Iron Pick")));
        values.put("Pump", (values.get("Mining Well")) + (values.get("Tank")));
        values.put("Auto Workbench", (values.get("Workbench")) + (values.get("Wooden Gear") * 4));
        values.put("Quarry", (values.get("Iron Gear") * 3) + (values.get("Gold Gear") * 2) + (values.get("Redstone")) + (values.get("Diamond Gear") * 2) + (values.get("Diamond Pick")));
        values.put("Filler", (values.get("Gold Gear") * 2) + (values.get("Yellow") * 2) + (values.get("Ink") * 2) + (values.get("Chest")) + (values.get("Workbench") + (values.get("Marker"))));
        values.put("Builder", (values.get("Filler") - (values.get("Gold Gear") * 2)) + (values.get("Diamond Gear") * 2));
        values.put("Template Drawer", (values.get("Builder") - (values.get("Chest"))) + (values.get("Template")));
        values.put("Refinery", (values.get("Redstone Torch") * 2) + (values.get("Tank") * 3) + (values.get("Diamond Gear") * 1));


        if (isTest) {
            File t = new File(recipes.ConfigDir + "pluginEE_BuildcraftEMCValues.cfg");
            if (t.exists()) {
                t.delete();
            }
        }
        recipes();
        pluginEE.readEMC(recipes);
    }

    @Override
    protected void recipes() {
        recipes.addDefault("[Define Custom EMC Here]");
        recipes.addDefault("# Gears");
        recipes.addDefault("WoodenGear=" + getItemID(Core, createGearName("wooden")) + d + 0 + d + values.get("Wooden Gear"));
        recipes.addDefault("StoneGear=" + getItemID(Core, createGearName("stone")) + d + 0 + d + values.get("Stone Gear"));
        recipes.addDefault("IronGear=" + getItemID(Core, createGearName("iron")) + d + 0 + d + values.get("Iron Gear"));
        recipes.addDefault("GoldGear=" + getItemID(Core, createGearName("gold")) + d + 0 + d + values.get("Gold Gear"));
        recipes.addDefault("DiamondGear=" + getItemID(Core, createGearName("diamond")) + d + 0 + d + values.get("Diamond Gear"));
        recipes.addDefault("# Engines");
        recipes.addDefault("RedstoneEngine=" + engineID + d + damage.get("Redstone") + d + values.get("Redstone Engine"));
        recipes.addDefault("StoneEngine=" + engineID + d + damage.get("Stone") + d + values.get("Stone Engine"));
        recipes.addDefault("IronEngine=" + engineID + d + damage.get("Iron") + d + values.get("Iron Engine"));
        recipes.addDefault("# Parts");
        recipes.addDefault("Waterproof=" + getItemID(Transport, waterproof) + d + 0 + d + values.get("Waterproof"));
        recipes.addDefault("Tank=" + getBlockID(Factory, "tankBlock") + d + 0 + d + values.get("Tank"));
        recipes.addDefault("Marker=" + getBlockID(Builders, "markerBlock") + d + 0 + d + values.get("Marker"));
        recipes.addDefault("Template=" + getItemID(Builders, "templateItem") + d + 0 + d + values.get("Template"));
        recipes.addDefault("# Pipes");
        recipes.addDefault("WoodPipe=" + getItemID(Transport, "pipeItemsWood") + d + 0 + d + values.get("Wood Pipe"));
        recipes.addDefault("CobblestonePipe=" + getItemID(Transport, "pipeItemsCobblestone") + d + 0 + d + values.get("Cobblestone Pipe"));
        recipes.addDefault("StonePipe=" + getItemID(Transport, "pipeItemsStone") + d + 0 + d + values.get("Stone Pipe"));
        recipes.addDefault("IronPipe=" + getItemID(Transport, "pipeItemsIron") + d + 0 + d + values.get("Iron Pipe"));
        recipes.addDefault("GoldPipe=" + getItemID(Transport, "pipeItemsGold") + d + 0 + d + values.get("Gold Pipe"));
        recipes.addDefault("DiamondPipe=" + getItemID(Transport, "pipeItemsDiamond") + d + 0 + d + values.get("Diamond Pipe"));
        recipes.addDefault("ObsidianPipe=" + getItemID(Transport, "pipeItemsObsidian") + d + 0 + d + values.get("Obsidian Pipe"));
        recipes.addDefault("# Waterproof Pipes");
        recipes.addDefault("WoodenWaterProofPipe=" + getItemID(Transport, "pipeLiquidsWood") + d + 0 + d + values.get("Wooden Waterproof Pipe"));
        recipes.addDefault("CobblestoneWaterProofPipe=" + getItemID(Transport, "pipeLiquidsCobblestone") + d + 0 + d + values.get("Cobblestone Waterproof Pipe"));
        recipes.addDefault("StoneWaterProofPipe=" + getItemID(Transport, "pipeLiquidsStone") + d + 0 + d + values.get("Stone Waterproof Pipe"));
        recipes.addDefault("IronWaterProofPipe=" + getItemID(Transport, "pipeLiquidsIron") + d + 0 + d + values.get("Iron Waterproof Pipe"));
        recipes.addDefault("GoldWaterProofPipe=" + getItemID(Transport, "pipeLiquidsGold") + d + 0 + d + values.get("Gold Waterproof Pipe"));
        recipes.addDefault("# Conductive Pipes");
        recipes.addDefault("WoodenConductivePipe=" + getItemID(Transport, "pipePowerWood") + d + 0 + d + values.get("Wooden Conductive Pipe"));
        recipes.addDefault("StoneConductivePipe=" + getItemID(Transport, "pipePowerStone") + d + 0 + d + values.get("Stone Conductive Pipe"));
        recipes.addDefault("GoldenConductivePipe=" + getItemID(Transport, "pipePowerGold") + d + 0 + d + values.get("Golden Conductive Pipe"));
        recipes.addDefault("# Machines");
        recipes.addDefault("MiningWell=" + getBlockID(Factory, "miningWellBlock") + d + 0 + d + values.get("Mining Well"));
        recipes.addDefault("Pump=" + getBlockID(Factory, "pumpBlock") + d + 0 + d + values.get("Pump"));
        recipes.addDefault("AutoWorkbench=" + getBlockID(Factory, "autoWorkbenchBlock") + d + 0 + d + values.get("Auto Workbench"));
        recipes.addDefault("Quarry=" + getBlockID(Factory, "quarryBlock") + d + 0 + d + values.get("Quarry"));
        recipes.addDefault("Filler=" + getBlockID(Builders, "fillerBlock") + d + 0 + d + values.get("Filler"));
        if (!denLib.detect("mod_BuildCraftSilicon")) {
            recipes.addDefault("Builder=" + getBlockID(Builders, "builderBlock") + d + 0 + d + values.get("Builder"));
            recipes.addDefault("TemplateDrawer=" + getBlockID(Builders, "templateBlock") + d + 0 + d + values.get("Template Drawer"));
        }
        recipes.addDefault("Refinery=" + getBlockID(Factory, "refineryBlock") + d + 0 + d + values.get("Refinery"));
        recipes.writeConfig();
        recipes.readFile();
    }

    public static void load(pluginBase parent) {
        baseModule b = new BuildcraftEMCModule(parent);
        b.register();
    }

    private int getItemID(String mod, String i) {
        return denLib.getItem(mod, i).shiftedIndex;
    }

    private int getBlockID(String mod, String b) {
        return denLib.getBlock(mod, b).blockID;
    }

    private String createGearName(String g) {
        return g.toLowerCase() + this.gearSuffix;
    }
}
