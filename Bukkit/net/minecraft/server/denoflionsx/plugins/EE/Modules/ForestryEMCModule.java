package net.minecraft.server.denoflionsx.plugins.EE.Modules;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.pluginEE;
import net.minecraft.server.denoflionsx.plugins.Forestry.circuitBoards;
import net.minecraft.server.denoflionsx.plugins.Forestry.tubes;
import forestry.api.core.ForestryBlock;
import forestry.api.core.ItemInterface;
import java.util.HashMap;
import net.minecraft.server.Block;
import net.minecraft.server.Item;

public class ForestryEMCModule extends baseModule
{
    public static Config recipes;
    public HashMap values = new HashMap();
    private boolean hasBC = false;

    public ForestryEMCModule(pluginBase var1)
    {
        super(var1);
    }

    protected void defaults() {}

    protected void init()
    {
        if (denLib.detect("mod_BuildCraftCore"))
        {
            this.hasBC = true;
        }

        this.values.put("Gold", Integer.valueOf(pluginEE.getEEValue(Item.GOLD_INGOT.id, 0)));
        this.values.put("Tin", Integer.valueOf(pluginEE.getEEValue(ItemInterface.getItem("ingotTin").getItem().id, 0)));
        this.values.put("Bronze", Integer.valueOf(pluginEE.getEEValue(ItemInterface.getItem("ingotBronze").getItem().id, 0)));
        this.values.put("Copper", Integer.valueOf(pluginEE.getEEValue(ItemInterface.getItem("ingotCopper").getItem().id, 0)));
        this.values.put("Iron", Integer.valueOf(pluginEE.getEEValue(Item.IRON_INGOT.id, 0)));
        this.values.put("Glass", Integer.valueOf(pluginEE.getEEValue(Block.GLASS.id, 0)));
        this.values.put("Piston", Integer.valueOf(pluginEE.getEEValue(Block.PISTON.id, 0)));
        this.values.put("Stick", Integer.valueOf(pluginEE.getEEValue(Item.STICK.id, 0)));
        this.values.put("Cobblestone", Integer.valueOf(pluginEE.getEEValue(Block.COBBLESTONE.id, 0)));
        this.values.put("Redstone", Integer.valueOf(pluginEE.getEEValue(Item.REDSTONE.id, 0)));
        this.values.put("Diamond", Integer.valueOf(pluginEE.getEEValue(Item.DIAMOND.id, 0)));
        this.values.put("Pumpkin", Integer.valueOf(pluginEE.getEEValue(Block.PUMPKIN.id, 0)));
        this.values.put("Melon", Integer.valueOf(pluginEE.getEEValue(Block.MELON.id, 0)));
        this.values.put("Cactus", Integer.valueOf(pluginEE.getEEValue(Block.CACTUS.id, 0)));
        this.values.put("Red Mushroom", Integer.valueOf(pluginEE.getEEValue(Block.RED_MUSHROOM.id, 0)));
        this.values.put("Brown Mushroom", Integer.valueOf(pluginEE.getEEValue(Block.BROWN_MUSHROOM.id, 0)));
        this.values.put("Sugar Cane", Integer.valueOf(pluginEE.getEEValue(Item.SUGAR_CANE.id, 0)));
        this.values.put("Nether Wart", Integer.valueOf(pluginEE.getEEValue(Item.NETHER_STALK.id, 0)));
        this.values.put("Chest", Integer.valueOf(pluginEE.getEEValue(Block.CHEST.id, 0)));
        this.values.put("Glass Pane", Integer.valueOf(1));
        this.values.put("Wood Plank", Integer.valueOf(pluginEE.getEEValue(Block.WOOD.id, 0)));
        this.values.put("Sand", Integer.valueOf(pluginEE.getEEValue(Block.SAND.id, 0)));
        int var1 = 0;
        int var2 = 0;

        if (this.hasBC)
        {
            var1 = ((Integer)this.values.get("Stick")).intValue() * 4;
            var2 = ((Integer)this.values.get("Cobblestone")).intValue() * 4 + var1;
        }

        this.values.put("Wooden Gear", Integer.valueOf(var1));
        this.values.put("Stone Gear", Integer.valueOf(var2));
        this.values.put("ic2 tree tap", Integer.valueOf(((Integer)this.values.get("Wood Plank")).intValue() * 5));
        this.values.put("Sturdy Machine", Integer.valueOf(((Integer)this.values.get("Bronze")).intValue() * 8));
        this.values.put("Hardened Machine", Integer.valueOf(((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Diamond")).intValue() * 4));
        this.values.put("Apatite", Integer.valueOf(pluginEE.getEEValue(ItemInterface.getItem("apatite").id, 0)));
        int var3 = pluginEE.flatten(new int[] {((Integer)this.values.get("Sand")).intValue() * 2, ((Integer)this.values.get("Apatite")).intValue()}, 8);
        this.values.put("Fertilizer", Integer.valueOf(var3));
        int var4;
        int var5;
        int var6;

        if (this.hasBC)
        {
            var4 = ((Integer)this.values.get("Bronze")).intValue() * 4 + ((Integer)this.values.get("Stone Gear")).intValue();
            var5 = ((Integer)this.values.get("Copper")).intValue() * 4 + ((Integer)this.values.get("Stone Gear")).intValue();
            var6 = ((Integer)this.values.get("Tin")).intValue() * 4 + ((Integer)this.values.get("Stone Gear")).intValue();
        }
        else
        {
            var4 = ((Integer)this.values.get("Bronze")).intValue() * 4;
            var5 = ((Integer)this.values.get("Copper")).intValue() * 4;
            var6 = ((Integer)this.values.get("Tin")).intValue() * 4;
        }

        this.values.put("Bronze Gear", Integer.valueOf(var4));
        this.values.put("Copper Gear", Integer.valueOf(var5));
        this.values.put("Tin Gear", Integer.valueOf(var6));
        this.values.put("Small Circuit Board", Integer.valueOf(((Integer)this.values.get("Tin")).intValue() + ((Integer)this.values.get("Redstone")).intValue() * 6));
        this.values.put("Medium Circuit Board", Integer.valueOf(((Integer)this.values.get("Redstone")).intValue() * 6 + ((Integer)this.values.get("Bronze")).intValue() * 3));
        this.values.put("Large Circuit Board", Integer.valueOf(((Integer)this.values.get("Redstone")).intValue() * 6 + ((Integer)this.values.get("Gold")).intValue() * 3));
        this.values.put("Golden Electron Tube", Integer.valueOf((((Integer)this.values.get("Gold")).intValue() * 5 + ((Integer)this.values.get("Redstone")).intValue() * 2) / 4));
        this.values.put("Diamond Tube", Integer.valueOf((((Integer)this.values.get("Diamond")).intValue() * 5 + ((Integer)this.values.get("Redstone")).intValue() * 2) / 4));
        this.values.put("Bronze Tube", Integer.valueOf((((Integer)this.values.get("Bronze")).intValue() * 5 + ((Integer)this.values.get("Redstone")).intValue() * 2) / 4));
        this.values.put("Iron Tube", Integer.valueOf((((Integer)this.values.get("Iron")).intValue() * 5 + ((Integer)this.values.get("Redstone")).intValue() * 2) / 4));
        this.values.put("Copper Tube", Integer.valueOf((((Integer)this.values.get("Copper")).intValue() * 5 + ((Integer)this.values.get("Redstone")).intValue() * 2) / 4));
        this.values.put("Tin Tube", Integer.valueOf((((Integer)this.values.get("Tin")).intValue() * 5 + ((Integer)this.values.get("Redstone")).intValue() * 2) / 4));
        this.values.put("Can", Integer.valueOf(((Integer)this.values.get("Tin")).intValue() * 3 / 12));
        this.values.put("Peat Engine", Integer.valueOf(((Integer)this.values.get("Copper")).intValue() * 3 + ((Integer)this.values.get("Copper Gear")).intValue() * 2 + ((Integer)this.values.get("Piston")).intValue() + ((Integer)this.values.get("Glass")).intValue()));
        this.values.put("Electrical Engine", Integer.valueOf(((Integer)this.values.get("Tin")).intValue() * 3 + ((Integer)this.values.get("Piston")).intValue() + ((Integer)this.values.get("Glass")).intValue() + ((Integer)this.values.get("Tin Gear")).intValue() * 2));
        this.values.put("Biogas Engine", Integer.valueOf(((Integer)this.values.get("Piston")).intValue() + ((Integer)this.values.get("Glass")).intValue() + ((Integer)this.values.get("Bronze Gear")).intValue() * 2 + ((Integer)this.values.get("Bronze")).intValue() * 3));
        this.values.put("Tree Farm", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Golden Electron Tube")).intValue() * 3 + ((Integer)this.values.get("Small Circuit Board")).intValue()));
        this.values.put("Logger", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Diamond Tube")).intValue() * 3 + ((Integer)this.values.get("Small Circuit Board")).intValue()));
        this.values.put("Wheat Farm", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Bronze Tube")).intValue() * 3 + ((Integer)this.values.get("Small Circuit Board")).intValue()));
        this.values.put("Pumpkin Farm", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Pumpkin")).intValue() * 2 + ((Integer)this.values.get("Melon")).intValue() * 2 + ((Integer)this.values.get("Tree Farm")).intValue()));
        this.values.put("Combine", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Iron Tube")).intValue() * 3 + ((Integer)this.values.get("Small Circuit Board")).intValue()));
        this.values.put("Pumpkin Harvester", Integer.valueOf(((Integer)this.values.get("Pumpkin")).intValue() * 2 + ((Integer)this.values.get("Melon")).intValue() * 2 + ((Integer)this.values.get("Logger")).intValue()));
        this.values.put("Peat Bog", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Copper Tube")).intValue() * 3 + ((Integer)this.values.get("Small Circuit Board")).intValue()));
        this.values.put("Turbary", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Tin Tube")).intValue() * 3 + ((Integer)this.values.get("Small Circuit Board")).intValue()));
        this.values.put("Cactus Harvester", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Cactus")).intValue() * 4 + ((Integer)this.values.get("Logger")).intValue()));
        this.values.put("Mushroom Farm", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Red Mushroom")).intValue() * 2 + ((Integer)this.values.get("Brown Mushroom")).intValue() * 2 + ((Integer)this.values.get("Tree Farm")).intValue()));
        this.values.put("Mushroom Harvester", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Red Mushroom")).intValue() * 2 + ((Integer)this.values.get("Brown Mushroom")).intValue() * 2 + ((Integer)this.values.get("Logger")).intValue()));
        this.values.put("Sugar Cane Harvester", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sugar Cane")).intValue() * 4 + ((Integer)this.values.get("Logger")).intValue()));
        this.values.put("Nether Farm", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Nether Wart")).intValue() * 4 + ((Integer)this.values.get("Tree Farm")).intValue()));
        this.values.put("Nether Harvester", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Nether Wart")).intValue() * 4 + ((Integer)this.values.get("Logger")).intValue()));
        this.values.put("Fermenter", Integer.valueOf(((Integer)this.values.get("Bronze Gear")).intValue() * 4 + ((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Still", Integer.valueOf(((Integer)this.values.get("Redstone")).intValue() * 4 + ((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Bottler", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Can")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Raintank", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 2 + ((Integer)this.values.get("Iron")).intValue() * 6 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("BiopowerGenerator", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 2 + ((Integer)this.values.get("Gold")).intValue() * 6 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Carpenter", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 2 + ((Integer)this.values.get("Bronze")).intValue() * 2 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Moistener", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Copper Gear")).intValue() * 4 + ((Integer)this.values.get("Fermenter")).intValue()));
        this.values.put("Apiary", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 2 + ((Integer)this.values.get("Tin Gear")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Centrifuge", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 2 + ((Integer)this.values.get("Copper")).intValue() * 6 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Squeezer", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 2 + ((Integer)this.values.get("Tin")).intValue() * 6 + ((Integer)this.values.get("Sturdy Machine")).intValue()));
        this.values.put("Fabricator", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 3 + ((Integer)this.values.get("Gold")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Chest")).intValue()));
        this.values.put("Beealzyer", Integer.valueOf(((Integer)this.values.get("Glass Pane")).intValue() * 2 + ((Integer)this.values.get("Tin")).intValue() * 4 + ((Integer)this.values.get("Redstone")).intValue() * 2 + ((Integer)this.values.get("Diamond")).intValue() * 1));
        this.values.put("Analyzer", Integer.valueOf(((Integer)this.values.get("Beealzyer")).intValue() + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Bronze")).intValue() * 4));
        this.values.put("Forester", Integer.valueOf(((Integer)this.values.get("Peat Bog")).intValue() + ((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Diamond")).intValue() * 4));
        this.values.put("Rainmaker", Integer.valueOf(((Integer)this.values.get("Hardened Machine")).intValue() + ((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Tin Gear")).intValue() * 4));
        this.values.put("Treetap", Integer.valueOf(((Integer)this.values.get("Glass")).intValue() * 4 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("ic2 tree tap")).intValue() * 4));
        this.values.put("Apichest", this.values.get("Glass"));
        this.values.put("Mailbox", Integer.valueOf(((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Chest")).intValue() * 3 + ((Integer)this.values.get("Tin")).intValue() * 3));
        this.values.put("TradeStation", Integer.valueOf(((Integer)this.values.get("Iron Tube")).intValue() * 2 + ((Integer)this.values.get("Bronze Tube")).intValue() * 3 + ((Integer)this.values.get("Sturdy Machine")).intValue() + ((Integer)this.values.get("Chest")).intValue() * 2 + ((Integer)this.values.get("Large Circuit Board")).intValue()));
        this.values.put("StampCollector", this.values.get("Glass"));
        this.values.put("Humus", Integer.valueOf((((Integer)this.values.get("Fertilizer")).intValue() + 8) / 8));
        this.values.put("Bog", Integer.valueOf(9));
        this.values.put("Wax", this.values.get("Cactus"));
        this.recipes();
        pluginEE.readEMC(recipes);
    }

    protected void recipes()
    {
        recipes = new Config("pluginEE_ForestryEMCValues.cfg");
        recipes.addDefault("[Define Custom EMC Here]");
        recipes.addDefault("# NameTag=ItemID,Damage Value,EMC Value");
        recipes.addDefault("# Forestry Parts");
        recipes.addDefault("SturdyMachine=" + ItemInterface.getItem("sturdyMachine").getItem().id + "," + 0 + "," + this.values.get("Sturdy Machine"));
        recipes.addDefault("HardenedMachine=" + ItemInterface.getItem("hardenedMachine").id + "," + 0 + "," + this.values.get("Hardened Machine"));
        recipes.addDefault("Fertilizer=" + ItemInterface.getItem("fertilizerCompound").id + "," + 0 + "," + this.values.get("Fertilizer"));
        recipes.addDefault("# Gears");
        recipes.addDefault("BronzeGear=" + ItemInterface.getItem("gearBronze").getItem().id + "," + 0 + "," + this.values.get("Bronze Gear"));
        recipes.addDefault("CopperGear=" + ItemInterface.getItem("gearCopper").getItem().id + "," + 0 + "," + this.values.get("Copper Gear"));
        recipes.addDefault("TinGear=" + ItemInterface.getItem("gearTin").getItem().id + "," + 0 + "," + this.values.get("Tin Gear"));
        recipes.addDefault("# Electron Tubes");
        recipes.addDefault("GoldenElectronTube=" + tubes.goldTube.id + "," + tubes.goldTube.getData() + "," + this.values.get("Golden Electron Tube"));
        recipes.addDefault("DiamondElectronTube=" + tubes.diamondTube.id + "," + tubes.diamondTube.getData() + "," + this.values.get("Diamond Tube"));
        recipes.addDefault("TinElectronTube=" + tubes.tinTube.id + "," + tubes.tinTube.getData() + "," + this.values.get("Tin Tube"));
        recipes.addDefault("CopperElectronTube=" + tubes.copperTube.id + "," + tubes.copperTube.getData() + "," + this.values.get("Copper Tube"));
        recipes.addDefault("IronElectronTube=" + tubes.ironTube.id + "," + tubes.ironTube.getData() + "," + this.values.get("Iron Tube"));
        recipes.addDefault("BronzeElectronTube=" + tubes.bronzeTube.id + "," + tubes.bronzeTube.getData() + "," + this.values.get("Bronze Tube"));
        recipes.addDefault("# Circuit Boards");
        recipes.addDefault("SmallCircuitBoard=" + circuitBoards.smallCircuitBoard.id + "," + circuitBoards.smallCircuitBoard.getData() + "," + this.values.get("Small Circuit Board"));
        recipes.addDefault("MediumCircuitBoard=" + circuitBoards.mediumCircuitBoard.id + "," + circuitBoards.mediumCircuitBoard.getData() + "," + this.values.get("Medium Circuit Board"));
        recipes.addDefault("LargeCircuitBoard=" + circuitBoards.largeCircuitBoard.id + "," + circuitBoards.largeCircuitBoard.getData() + "," + this.values.get("Large Circuit Board"));
        recipes.addDefault("# Engines");
        recipes.addDefault("BiogasEngine=" + ForestryBlock.engine.id + "," + 0 + "," + this.values.get("Biogas Engine"));
        recipes.addDefault("PeatEngine=" + ForestryBlock.engine.id + "," + 1 + "," + this.values.get("Peat Engine"));
        recipes.addDefault("ElectricalEngine=" + ForestryBlock.engine.id + "," + 2 + "," + this.values.get("Electrical Engine"));
        recipes.addDefault("# Containers");
        recipes.addDefault("Can=" + ItemInterface.getItem("canEmpty").id + "," + 0 + "," + this.values.get("Can"));
        recipes.addDefault("# Farm Machines");
        recipes.addDefault("TreeFarm=" + ForestryBlock.planter.id + "," + 0 + "," + this.values.get("Tree Farm"));
        recipes.addDefault("Logger=" + ForestryBlock.harvester.id + "," + 0 + "," + this.values.get("Logger"));
        recipes.addDefault("WheatFarm=" + ForestryBlock.planter.id + "," + 1 + "," + this.values.get("Wheat Farm"));
        recipes.addDefault("Combine=" + ForestryBlock.harvester.id + "," + 1 + "," + this.values.get("Combine"));
        recipes.addDefault("RubberFarm=" + ForestryBlock.planter.id + "," + 2 + "," + this.values.get("Tree Farm"));
        recipes.addDefault("RubberHavester=" + ForestryBlock.harvester.id + "," + 2 + "," + this.values.get("Logger"));
        recipes.addDefault("PumpkinFarm=" + ForestryBlock.planter.id + "," + 3 + "," + this.values.get("Pumpkin Farm"));
        recipes.addDefault("PumpkinHarvester=" + ForestryBlock.harvester.id + "," + 3 + "," + this.values.get("Pumpkin Harvester"));
        recipes.addDefault("PeatBog=" + ForestryBlock.planter.id + "," + 4 + "," + this.values.get("Peat Bog"));
        recipes.addDefault("Turbary=" + ForestryBlock.harvester.id + "," + 4 + "," + this.values.get("Turbary"));
        recipes.addDefault("CactusHarvester=" + ForestryBlock.harvester.id + "," + 5 + "," + this.values.get("Cactus Harvester"));
        recipes.addDefault("MushroomFarm=" + ForestryBlock.planter.id + "," + 5 + "," + this.values.get("Mushroom Farm"));
        recipes.addDefault("MushroomHarvester=" + ForestryBlock.harvester.id + "," + 6 + "," + this.values.get("Mushroom Harvester"));
        recipes.addDefault("SugarcaneHarvester=" + ForestryBlock.harvester.id + "," + 7 + "," + this.values.get("Sugar Cane Harvester"));
        recipes.addDefault("NetherFarm=" + ForestryBlock.planter.id + "," + 6 + "," + this.values.get("Nether Farm"));
        recipes.addDefault("NetherHarvester=" + ForestryBlock.harvester.id + "," + 8 + "," + this.values.get("Nether Harvester"));
        recipes.addDefault("# Factory Machines");
        recipes.addDefault("Fermenter=" + ForestryBlock.machine.id + "," + 0 + "," + this.values.get("Fermenter"));
        recipes.addDefault("Still=" + ForestryBlock.machine.id + "," + 1 + "," + this.values.get("Still"));
        recipes.addDefault("Bottler=" + ForestryBlock.machine.id + "," + 2 + "," + this.values.get("Bottler"));
        recipes.addDefault("Raintank=" + ForestryBlock.machine.id + "," + 3 + "," + this.values.get("Raintank"));
        recipes.addDefault("BiopowerGenerator=" + ForestryBlock.machine.id + "," + 4 + "," + this.values.get("BiopowerGenerator"));
        recipes.addDefault("Carpenter=" + ForestryBlock.machine.id + "," + 5 + "," + this.values.get("Carpenter"));
        recipes.addDefault("Moistener=" + ForestryBlock.machine.id + "," + 6 + "," + this.values.get("Moistener"));
        recipes.addDefault("Apiary=" + ForestryBlock.machine.id + "," + 7 + "," + this.values.get("Apiary"));
        recipes.addDefault("Centrifuge=" + ForestryBlock.machine.id + "," + 8 + "," + this.values.get("Centrifuge"));
        recipes.addDefault("Squeezer=" + ForestryBlock.machine.id + "," + 9 + "," + this.values.get("Squeezer"));
        recipes.addDefault("ThermionicFabricator=" + ForestryBlock.machine.id + "," + 11 + "," + this.values.get("Fabricator"));
        recipes.addDefault("Beealyzer=" + ItemInterface.getItem("beealyzer").id + "," + 0 + "," + this.values.get("Beealzyer"));
        recipes.addDefault("Analzyer=" + ForestryBlock.mill.id + "," + 4 + "," + this.values.get("Analyzer"));
        recipes.addDefault("# Since the Forester can be crafted from any farm, peat bog was used for the calculation.");
        recipes.addDefault("Forester=" + ForestryBlock.mill.id + "," + 0 + "," + this.values.get("Forester"));
        recipes.addDefault("Rainmaker=" + ForestryBlock.mill.id + "," + 1 + "," + this.values.get("Rainmaker"));
        recipes.addDefault("Treetap=" + ForestryBlock.mill.id + "," + 2 + "," + this.values.get("Treetap"));
        recipes.addDefault("Apichest=" + ForestryBlock.mill.id + "," + 3 + "," + this.values.get("Apichest"));
        recipes.addDefault("# Mail Stuff");
        recipes.addDefault("Mailbox=" + ForestryBlock.mill.id + "," + 5 + "," + this.values.get("Mailbox"));
        recipes.addDefault("TradeStation=" + ForestryBlock.mill.id + "," + 6 + "," + this.values.get("TradeStation"));
        recipes.addDefault("StampCollector=" + ForestryBlock.mill.id + "," + 7 + "," + this.values.get("StampCollector"));
        recipes.addDefault("# Forestry Soil");
        recipes.addDefault("Humus=" + ForestryBlock.soil.id + "," + 0 + "," + this.values.get("Humus"));
        recipes.addDefault("Bog=" + ForestryBlock.soil.id + "," + 1 + "," + this.values.get("Bog"));
        recipes.addDefault("# Bee Stuff");
        recipes.addDefault("Wax=" + ItemInterface.getItem("beeswax").id + "," + 0 + "," + this.values.get("Wax"));
        recipes.writeConfig();
        recipes.readFile();
    }

    public static void load(pluginBase var0)
    {
        ForestryEMCModule var1 = new ForestryEMCModule(var0);
        var1.register();
    }
}
