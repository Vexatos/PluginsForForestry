package net.minecraft.server.denoflionsx.plugins.EE.Modules;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.pluginEE;
import java.io.File;
import java.util.HashMap;
import net.minecraft.server.Block;
import net.minecraft.server.Item;

public class BuildcraftEMCModule extends baseModule
{
    private String d = ",";
    public HashMap values = new HashMap();
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
    private HashMap damage = new HashMap();
    private boolean isTest = false;

    public BuildcraftEMCModule(pluginBase var1)
    {
        super(var1);
    }

    protected void defaults() {}

    protected void init()
    {
        this.Core = "BuildCraftCore";
        this.Energy = "BuildCraftEnergy";
        this.Transport = "BuildCraftTransport";
        this.Factory = "BuildCraftFactory";
        this.Builders = "BuildCraftBuilders";

        if (core.isBukkit)
        {
            this.Core = core.BukkitShift(this.Core);
            this.Energy = core.BukkitShift(this.Energy);
            this.Transport = core.BukkitShift(this.Transport);
            this.Factory = core.BukkitShift(this.Factory);
            this.Builders = core.BukkitShift(this.Builders);
        }

        this.values.put("Iron", Integer.valueOf(pluginEE.getEEValue(Item.IRON_INGOT.id, 0)));
        this.values.put("Gold", Integer.valueOf(pluginEE.getEEValue(Item.GOLD_INGOT.id, 0)));
        this.values.put("Diamond", Integer.valueOf(pluginEE.getEEValue(Item.DIAMOND.id, 0)));
        this.values.put("Wood Plank", Integer.valueOf(pluginEE.getEEValue(Block.WOOD.id, 0)));
        this.values.put("Cobblestone", Integer.valueOf(pluginEE.getEEValue(Block.COBBLESTONE.id, 0)));
        this.values.put("Stone", Integer.valueOf(pluginEE.getEEValue(Block.STONE.id, 0)));
        this.values.put("Stick", Integer.valueOf(pluginEE.getEEValue(Item.STICK.id, 0)));
        this.values.put("Piston", Integer.valueOf(pluginEE.getEEValue(Block.PISTON.id, 0)));
        this.values.put("Glass", Integer.valueOf(pluginEE.getEEValue(Block.GLASS.id, 0)));
        this.values.put("Cactus", Integer.valueOf(pluginEE.getEEValue(Block.CACTUS.id, 0)));
        this.values.put("Obsidian", Integer.valueOf(pluginEE.getEEValue(Block.OBSIDIAN.id, 0)));
        this.values.put("Redstone", Integer.valueOf(pluginEE.getEEValue(Item.REDSTONE.id, 0)));
        this.values.put("Iron Pick", Integer.valueOf(pluginEE.getEEValue(Item.IRON_PICKAXE.id, 0)));
        this.values.put("Workbench", Integer.valueOf(pluginEE.getEEValue(Block.WORKBENCH.id, 0)));
        this.values.put("Diamond Pick", Integer.valueOf(pluginEE.getEEValue(Item.DIAMOND_PICKAXE.id, 0)));
        this.values.put("Lapiz", Integer.valueOf(pluginEE.getEEValue(Item.INK_SACK.id, 4)));
        this.values.put("Redstone Torch", Integer.valueOf(pluginEE.getEEValue(Block.REDSTONE_TORCH_ON.id, 0)));
        this.values.put("Ink", Integer.valueOf(pluginEE.getEEValue(Item.INK_SACK.id, 0)));
        this.values.put("Chest", Integer.valueOf(pluginEE.getEEValue(Block.CHEST.id, 0)));
        this.values.put("Yellow", Integer.valueOf(pluginEE.getEEValue(Item.INK_SACK.id, 11)));
        this.values.put("Paper", Integer.valueOf(pluginEE.getEEValue(Item.PAPER.id, 0)));
        this.values.put("Wooden Gear", Integer.valueOf(((Integer)this.values.get("Stick")).intValue() * 4));
        this.values.put("Stone Gear", Integer.valueOf(((Integer)this.values.get("Cobblestone")).intValue() * 4 + ((Integer)this.values.get("Wooden Gear")).intValue()));
        this.values.put("Iron Gear", Integer.valueOf(((Integer)this.values.get("Stone Gear")).intValue() + ((Integer)this.values.get("Iron")).intValue() * 4));
        this.values.put("Gold Gear", Integer.valueOf(((Integer)this.values.get("Iron Gear")).intValue() + ((Integer)this.values.get("Gold")).intValue() * 4));
        this.values.put("Diamond Gear", Integer.valueOf(((Integer)this.values.get("Gold Gear")).intValue() + ((Integer)this.values.get("Diamond")).intValue() * 4));
        this.damage.put("Redstone", Integer.valueOf(0));
        this.damage.put("Stone", Integer.valueOf(1));
        this.damage.put("Iron", Integer.valueOf(2));
        this.engineID = this.getBlockID(this.Energy, this.engine);
        this.values.put("Redstone Engine", Integer.valueOf(((Integer)this.values.get("Wooden Gear")).intValue() * 2 + ((Integer)this.values.get("Wood Plank")).intValue() * 3 + ((Integer)this.values.get("Piston")).intValue() + ((Integer)this.values.get("Glass")).intValue()));
        this.values.put("Stone Engine", Integer.valueOf(((Integer)this.values.get("Stone Gear")).intValue() * 2 + ((Integer)this.values.get("Cobblestone")).intValue() * 3 + ((Integer)this.values.get("Piston")).intValue() + ((Integer)this.values.get("Glass")).intValue()));
        this.values.put("Iron Engine", Integer.valueOf(((Integer)this.values.get("Iron Gear")).intValue() * 2 + ((Integer)this.values.get("Iron")).intValue() * 3 + ((Integer)this.values.get("Piston")).intValue() + ((Integer)this.values.get("Glass")).intValue()));
        this.values.put("Waterproof", this.values.get("Cactus"));
        this.values.put("Tank", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 8));
        this.values.put("Marker", Integer.valueOf(((Integer)this.values.get("Redstone Torch")).intValue() + ((Integer)this.values.get("Lapiz")).intValue()));
        this.values.put("Template", Integer.valueOf(((Integer)this.values.get("Paper")).intValue() * 8 + ((Integer)this.values.get("Ink")).intValue()));
        int[] var1 = new int[] {((Integer)this.values.get("Wood Plank")).intValue(), ((Integer)this.values.get("Wood Plank")).intValue(), ((Integer)this.values.get("Glass")).intValue()};
        int var2 = pluginEE.flatten(var1, 8);
        this.values.put("Wood Pipe", Integer.valueOf(var2));
        this.values.put("Cobblestone Pipe", Integer.valueOf(pluginEE.flatten(new int[] {((Integer)this.values.get("Cobblestone")).intValue() * 2, ((Integer)this.values.get("Glass")).intValue()}, 8)));
        this.values.put("Stone Pipe", Integer.valueOf(pluginEE.flatten(new int[] {((Integer)this.values.get("Stone")).intValue() * 2, ((Integer)this.values.get("Glass")).intValue()}, 8)));
        this.values.put("Gold Pipe", Integer.valueOf(pluginEE.flatten(new int[] {((Integer)this.values.get("Gold")).intValue() * 2, ((Integer)this.values.get("Glass")).intValue()}, 8)));
        this.values.put("Iron Pipe", Integer.valueOf(pluginEE.flatten(new int[] {((Integer)this.values.get("Iron")).intValue() * 2, ((Integer)this.values.get("Glass")).intValue()}, 8)));
        this.values.put("Diamond Pipe", Integer.valueOf(pluginEE.flatten(new int[] {((Integer)this.values.get("Diamond")).intValue() * 2, ((Integer)this.values.get("Glass")).intValue()}, 8)));
        this.values.put("Obsidian Pipe", Integer.valueOf(pluginEE.flatten(new int[] {((Integer)this.values.get("Obsidian")).intValue() * 2, ((Integer)this.values.get("Glass")).intValue()}, 8)));
        this.values.put("Wooden Waterproof Pipe", Integer.valueOf(((Integer)this.values.get("Wood Pipe")).intValue() + ((Integer)this.values.get("Waterproof")).intValue()));
        this.values.put("Cobblestone Waterproof Pipe", Integer.valueOf(((Integer)this.values.get("Cobblestone Pipe")).intValue() + ((Integer)this.values.get("Waterproof")).intValue()));
        this.values.put("Stone Waterproof Pipe", Integer.valueOf(((Integer)this.values.get("Stone Pipe")).intValue() + ((Integer)this.values.get("Waterproof")).intValue()));
        this.values.put("Gold Waterproof Pipe", Integer.valueOf(((Integer)this.values.get("Gold Pipe")).intValue() + ((Integer)this.values.get("Waterproof")).intValue()));
        this.values.put("Iron Waterproof Pipe", Integer.valueOf(((Integer)this.values.get("Iron Pipe")).intValue() + ((Integer)this.values.get("Waterproof")).intValue()));
        this.values.put("Wooden Conductive Pipe", Integer.valueOf(((Integer)this.values.get("Wood Pipe")).intValue() + ((Integer)this.values.get("Redstone")).intValue()));
        this.values.put("Stone Conductive Pipe", Integer.valueOf(((Integer)this.values.get("Stone Pipe")).intValue() + ((Integer)this.values.get("Redstone")).intValue()));
        this.values.put("Golden Conductive Pipe", Integer.valueOf(((Integer)this.values.get("Gold Pipe")).intValue() + ((Integer)this.values.get("Redstone")).intValue()));
        this.values.put("Mining Well", Integer.valueOf(((Integer)this.values.get("Iron")).intValue() * 6 + ((Integer)this.values.get("Redstone")).intValue() + ((Integer)this.values.get("Iron Gear")).intValue() + ((Integer)this.values.get("Iron Pick")).intValue()));
        this.values.put("Pump", Integer.valueOf(((Integer)this.values.get("Mining Well")).intValue() + ((Integer)this.values.get("Tank")).intValue()));
        this.values.put("Auto Workbench", Integer.valueOf(((Integer)this.values.get("Workbench")).intValue() + ((Integer)this.values.get("Wooden Gear")).intValue() * 4));
        this.values.put("Quarry", Integer.valueOf(((Integer)this.values.get("Iron Gear")).intValue() * 3 + ((Integer)this.values.get("Gold Gear")).intValue() * 2 + ((Integer)this.values.get("Redstone")).intValue() + ((Integer)this.values.get("Diamond Gear")).intValue() * 2 + ((Integer)this.values.get("Diamond Pick")).intValue()));
        this.values.put("Filler", Integer.valueOf(((Integer)this.values.get("Gold Gear")).intValue() * 2 + ((Integer)this.values.get("Yellow")).intValue() * 2 + ((Integer)this.values.get("Ink")).intValue() * 2 + ((Integer)this.values.get("Chest")).intValue() + ((Integer)this.values.get("Workbench")).intValue() + ((Integer)this.values.get("Marker")).intValue()));
        this.values.put("Builder", Integer.valueOf(((Integer)this.values.get("Filler")).intValue() - ((Integer)this.values.get("Gold Gear")).intValue() * 2 + ((Integer)this.values.get("Diamond Gear")).intValue() * 2));
        this.values.put("Template Drawer", Integer.valueOf(((Integer)this.values.get("Builder")).intValue() - ((Integer)this.values.get("Chest")).intValue() + ((Integer)this.values.get("Template")).intValue()));
        this.values.put("Refinery", Integer.valueOf(((Integer)this.values.get("Redstone Torch")).intValue() * 2 + ((Integer)this.values.get("Tank")).intValue() * 3 + ((Integer)this.values.get("Diamond Gear")).intValue() * 1));

        if (this.isTest)
        {
            File var3 = new File(Config.ConfigDir + "pluginEE_BuildcraftEMCValues.cfg");

            if (var3.exists())
            {
                var3.delete();
            }
        }

        this.recipes();
        pluginEE.readEMC(recipes);
    }

    protected void recipes()
    {
        recipes.addDefault("[Define Custom EMC Here]");
        recipes.addDefault("# Gears");
        recipes.addDefault("WoodenGear=" + this.getItemID(this.Core, this.createGearName("wooden")) + this.d + 0 + this.d + this.values.get("Wooden Gear"));
        recipes.addDefault("StoneGear=" + this.getItemID(this.Core, this.createGearName("stone")) + this.d + 0 + this.d + this.values.get("Stone Gear"));
        recipes.addDefault("IronGear=" + this.getItemID(this.Core, this.createGearName("iron")) + this.d + 0 + this.d + this.values.get("Iron Gear"));
        recipes.addDefault("GoldGear=" + this.getItemID(this.Core, this.createGearName("gold")) + this.d + 0 + this.d + this.values.get("Gold Gear"));
        recipes.addDefault("DiamondGear=" + this.getItemID(this.Core, this.createGearName("diamond")) + this.d + 0 + this.d + this.values.get("Diamond Gear"));
        recipes.addDefault("# Engines");
        recipes.addDefault("RedstoneEngine=" + this.engineID + this.d + this.damage.get("Redstone") + this.d + this.values.get("Redstone Engine"));
        recipes.addDefault("StoneEngine=" + this.engineID + this.d + this.damage.get("Stone") + this.d + this.values.get("Stone Engine"));
        recipes.addDefault("IronEngine=" + this.engineID + this.d + this.damage.get("Iron") + this.d + this.values.get("Iron Engine"));
        recipes.addDefault("# Parts");
        recipes.addDefault("Waterproof=" + this.getItemID(this.Transport, this.waterproof) + this.d + 0 + this.d + this.values.get("Waterproof"));
        recipes.addDefault("Tank=" + this.getBlockID(this.Factory, "tankBlock") + this.d + 0 + this.d + this.values.get("Tank"));
        recipes.addDefault("Marker=" + this.getBlockID(this.Builders, "markerBlock") + this.d + 0 + this.d + this.values.get("Marker"));
        recipes.addDefault("Template=" + this.getItemID(this.Builders, "templateItem") + this.d + 0 + this.d + this.values.get("Template"));
        recipes.addDefault("# Pipes");
        recipes.addDefault("WoodPipe=" + this.getItemID(this.Transport, "pipeItemsWood") + this.d + 0 + this.d + this.values.get("Wood Pipe"));
        recipes.addDefault("CobblestonePipe=" + this.getItemID(this.Transport, "pipeItemsCobblestone") + this.d + 0 + this.d + this.values.get("Cobblestone Pipe"));
        recipes.addDefault("StonePipe=" + this.getItemID(this.Transport, "pipeItemsStone") + this.d + 0 + this.d + this.values.get("Stone Pipe"));
        recipes.addDefault("IronPipe=" + this.getItemID(this.Transport, "pipeItemsIron") + this.d + 0 + this.d + this.values.get("Iron Pipe"));
        recipes.addDefault("GoldPipe=" + this.getItemID(this.Transport, "pipeItemsGold") + this.d + 0 + this.d + this.values.get("Gold Pipe"));
        recipes.addDefault("DiamondPipe=" + this.getItemID(this.Transport, "pipeItemsDiamond") + this.d + 0 + this.d + this.values.get("Diamond Pipe"));
        recipes.addDefault("ObsidianPipe=" + this.getItemID(this.Transport, "pipeItemsObsidian") + this.d + 0 + this.d + this.values.get("Obsidian Pipe"));
        recipes.addDefault("# Waterproof Pipes");
        recipes.addDefault("WoodenWaterProofPipe=" + this.getItemID(this.Transport, "pipeLiquidsWood") + this.d + 0 + this.d + this.values.get("Wooden Waterproof Pipe"));
        recipes.addDefault("CobblestoneWaterProofPipe=" + this.getItemID(this.Transport, "pipeLiquidsCobblestone") + this.d + 0 + this.d + this.values.get("Cobblestone Waterproof Pipe"));
        recipes.addDefault("StoneWaterProofPipe=" + this.getItemID(this.Transport, "pipeLiquidsStone") + this.d + 0 + this.d + this.values.get("Stone Waterproof Pipe"));
        recipes.addDefault("IronWaterProofPipe=" + this.getItemID(this.Transport, "pipeLiquidsIron") + this.d + 0 + this.d + this.values.get("Iron Waterproof Pipe"));
        recipes.addDefault("GoldWaterProofPipe=" + this.getItemID(this.Transport, "pipeLiquidsGold") + this.d + 0 + this.d + this.values.get("Gold Waterproof Pipe"));
        recipes.addDefault("# Conductive Pipes");
        recipes.addDefault("WoodenConductivePipe=" + this.getItemID(this.Transport, "pipePowerWood") + this.d + 0 + this.d + this.values.get("Wooden Conductive Pipe"));
        recipes.addDefault("StoneConductivePipe=" + this.getItemID(this.Transport, "pipePowerStone") + this.d + 0 + this.d + this.values.get("Stone Conductive Pipe"));
        recipes.addDefault("GoldenConductivePipe=" + this.getItemID(this.Transport, "pipePowerGold") + this.d + 0 + this.d + this.values.get("Golden Conductive Pipe"));
        recipes.addDefault("# Machines");
        recipes.addDefault("MiningWell=" + this.getBlockID(this.Factory, "miningWellBlock") + this.d + 0 + this.d + this.values.get("Mining Well"));
        recipes.addDefault("Pump=" + this.getBlockID(this.Factory, "pumpBlock") + this.d + 0 + this.d + this.values.get("Pump"));
        recipes.addDefault("AutoWorkbench=" + this.getBlockID(this.Factory, "autoWorkbenchBlock") + this.d + 0 + this.d + this.values.get("Auto Workbench"));
        recipes.addDefault("Quarry=" + this.getBlockID(this.Factory, "quarryBlock") + this.d + 0 + this.d + this.values.get("Quarry"));
        recipes.addDefault("Filler=" + this.getBlockID(this.Builders, "fillerBlock") + this.d + 0 + this.d + this.values.get("Filler"));

        if (!denLib.detect("mod_BuildCraftSilicon"))
        {
            recipes.addDefault("Builder=" + this.getBlockID(this.Builders, "builderBlock") + this.d + 0 + this.d + this.values.get("Builder"));
            recipes.addDefault("TemplateDrawer=" + this.getBlockID(this.Builders, "templateBlock") + this.d + 0 + this.d + this.values.get("Template Drawer"));
        }

        recipes.addDefault("Refinery=" + this.getBlockID(this.Factory, "refineryBlock") + this.d + 0 + this.d + this.values.get("Refinery"));
        recipes.writeConfig();
        recipes.readFile();
    }

    public static void load(pluginBase var0)
    {
        BuildcraftEMCModule var1 = new BuildcraftEMCModule(var0);
        var1.register();
    }

    private int getItemID(String var1, String var2)
    {
        return denLib.getItem(var1, var2).id;
    }

    private int getBlockID(String var1, String var2)
    {
        return denLib.getBlock(var1, var2).id;
    }

    private String createGearName(String var1)
    {
        return var1.toLowerCase() + this.gearSuffix;
    }
}
