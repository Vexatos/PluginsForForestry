package denoflionsx.Old.EE.Modules;

//package denoflionsx.plugins.EE.Modules;
//
//import forestry.api.core.ItemInterface;
//import java.util.HashMap;
//import net.minecraft.src.Block;
//import net.minecraft.src.Item;
//import denoflionsx.denLib.Config.Config;
//import denoflionsx.denLib.denLib;
//import denoflionsx.plugins.Forestry.Helpers.ForestryBlock;
//import denoflionsx.plugins.Forestry.Helpers.circuitBoards;
//import denoflionsx.plugins.Forestry.Helpers.tubes;
//import denoflionsx.plugins.baseModule;
//import denoflionsx.plugins.pluginBase;
//import denoflionsx.plugins.pluginEE;
//
//public class ForestryEMCModule extends baseModule {
//
//    public static Config recipes;
//    public HashMap<String, Integer> values = new HashMap();
//    private boolean hasBC = false;
//
//    public ForestryEMCModule(pluginBase parent) {
//        super(parent);
//    }
//
//    @Override
//    protected void defaults() {
//    }
//
//    @Override
//    protected void init() {
//        if (denLib.detect("mod_BuildCraftCore")) {
//            this.hasBC = true;
//        }
//        // Ingots
//        values.put("Gold", pluginEE.getEEValue(Item.ingotGold.shiftedIndex, 0));
//        values.put("Tin", pluginEE.getEEValue(ItemInterface.getItem("ingotTin").getItem().shiftedIndex, 0));
//        values.put("Bronze", pluginEE.getEEValue(ItemInterface.getItem("ingotBronze").getItem().shiftedIndex, 0));
//        values.put("Copper", pluginEE.getEEValue(ItemInterface.getItem("ingotCopper").getItem().shiftedIndex, 0));
//        values.put("Iron", pluginEE.getEEValue(Item.ingotIron.shiftedIndex, 0));
//
//        //Misc
//        values.put("Glass", pluginEE.getEEValue(Block.glass.blockID, 0));
//        values.put("Piston", pluginEE.getEEValue(Block.pistonBase.blockID, 0));
//        values.put("Stick", pluginEE.getEEValue(Item.stick.shiftedIndex, 0));
//        values.put("Cobblestone", pluginEE.getEEValue(Block.cobblestone.blockID, 0));
//        values.put("Redstone", pluginEE.getEEValue(Item.redstone.shiftedIndex, 0));
//        values.put("Diamond", pluginEE.getEEValue(Item.diamond.shiftedIndex, 0));
//        values.put("Pumpkin", pluginEE.getEEValue(Block.pumpkin.blockID, 0));
//        values.put("Melon", pluginEE.getEEValue(Block.melon.blockID, 0));
//        values.put("Cactus", pluginEE.getEEValue(Block.cactus.blockID, 0));
//        values.put("Red Mushroom", pluginEE.getEEValue(Block.mushroomRed.blockID, 0));
//        values.put("Brown Mushroom", pluginEE.getEEValue(Block.mushroomBrown.blockID, 0));
//        values.put("Sugar Cane", pluginEE.getEEValue(Item.reed.shiftedIndex, 0));
//        values.put("Nether Wart", pluginEE.getEEValue(Item.netherStalkSeeds.shiftedIndex, 0));
//        values.put("Chest", pluginEE.getEEValue(Block.chest.blockID, 0));
//        values.put("Glass Pane", 1);
//        values.put("Wood Plank", pluginEE.getEEValue(Block.planks.blockID, 0));
//        values.put("Sand", pluginEE.getEEValue(Block.sand.blockID,0));
//
//        //BC Parts.
//        int woodgear = 0;
//        int stonegear = 0;
//        if (this.hasBC) {
//            woodgear = (values.get("Stick") * 4);
//            stonegear = (values.get("Cobblestone") * 4) + woodgear;
//        }
//        values.put("Wooden Gear", woodgear);
//        values.put("Stone Gear", stonegear);
//        // IC2 Treetap
//        values.put("ic2 tree tap", (values.get("Wood Plank") * 5));
//
//        //Forestry Parts
//        values.put("Sturdy Machine", (values.get("Bronze") * 8));
//        values.put("Hardened Machine", (values.get("Sturdy Machine")) + (values.get("Diamond") * 4));
//        values.put("Apatite", pluginEE.getEEValue(ItemInterface.getItem("apatite").itemID,0));
//        int f = pluginEE.flatten(new int[]{(values.get("Sand") * 2),values.get("Apatite")},8);
//        values.put("Fertilizer", f);
//        // Gears
//        int bronzegear;
//        int coppergear;
//        int tingear;
//        if (this.hasBC){
//           bronzegear = (values.get("Bronze") * 4) + values.get("Stone Gear");
//           coppergear = (values.get("Copper") * 4) + (values.get("Stone Gear"));
//           tingear = (values.get("Tin") * 4) + (values.get("Stone Gear"));
//        }else{
//            bronzegear = (values.get("Bronze") * 4);
//            coppergear = (values.get("Copper") * 4);
//            tingear = (values.get("Tin") * 4);
//        }
//        values.put("Bronze Gear", bronzegear);
//        values.put("Copper Gear", coppergear);
//        values.put("Tin Gear", tingear);
//        // Circuit Boards
//        values.put("Small Circuit Board", (values.get("Tin")) + (values.get("Redstone") * 6));
//        values.put("Medium Circuit Board", (values.get("Redstone") * 6) + (values.get("Bronze") * 3));
//        values.put("Large Circuit Board", (values.get("Redstone") * 6) + (values.get("Gold") * 3));
//        // Tubes
//        values.put("Golden Electron Tube", ((values.get("Gold") * 5) + (values.get("Redstone") * 2)) / 4);
//        values.put("Diamond Tube", ((values.get("Diamond") * 5) + (values.get("Redstone") * 2)) / 4);
//        values.put("Bronze Tube", ((values.get("Bronze") * 5) + (values.get("Redstone") * 2)) / 4);
//        values.put("Iron Tube", ((values.get("Iron") * 5) + (values.get("Redstone") * 2)) / 4);
//        values.put("Copper Tube", ((values.get("Copper") * 5) + (values.get("Redstone") * 2)) / 4);
//        values.put("Tin Tube", ((values.get("Tin") * 5) + (values.get("Redstone") * 2)) / 4);
//        // Containers
//        values.put("Can", (values.get("Tin") * 3) / 12);
//
//        //Forestry Engines
//        values.put("Peat Engine", (values.get("Copper") * 3) + (values.get("Copper Gear") * 2) + (values.get("Piston") + (values.get("Glass"))));
//        values.put("Electrical Engine", (values.get("Tin") * 3) + (values.get("Piston") + (values.get("Glass") + (values.get("Tin Gear") * 2))));
//        values.put("Biogas Engine", values.get("Piston") + values.get("Glass") + (values.get("Bronze Gear") * 2) + (values.get("Bronze") * 3));
//
//        //Forestry Farming Machines
//        values.put("Tree Farm", (values.get("Glass") * 4) + (values.get("Sturdy Machine")) + (values.get("Golden Electron Tube") * 3) + (values.get("Small Circuit Board")));
//        values.put("Logger", (values.get("Glass") * 4) + (values.get("Sturdy Machine")) + (values.get("Diamond Tube") * 3) + (values.get("Small Circuit Board")));
//        values.put("Wheat Farm", (values.get("Glass") * 4) + (values.get("Sturdy Machine")) + (values.get("Bronze Tube") * 3) + (values.get("Small Circuit Board")));
//        values.put("Pumpkin Farm", (values.get("Glass") * 4) + (values.get("Pumpkin") * 2) + (values.get("Melon") * 2) + (values.get("Tree Farm")));
//        values.put("Combine", (values.get("Glass") * 4) + (values.get("Sturdy Machine") + (values.get("Iron Tube") * 3) + values.get("Small Circuit Board")));
//        values.put("Pumpkin Harvester", (values.get("Pumpkin") * 2) + (values.get("Melon") * 2) + (values.get("Logger")));
//        values.put("Peat Bog", (values.get("Glass") * 4) + (values.get("Sturdy Machine")) + (values.get("Copper Tube") * 3) + values.get("Small Circuit Board"));
//        values.put("Turbary", (values.get("Glass") * 4) + (values.get("Sturdy Machine")) + (values.get("Tin Tube") * 3) + values.get("Small Circuit Board"));
//        values.put("Cactus Harvester", (values.get("Glass") * 4) + (values.get("Cactus") * 4) + (values.get("Logger")));
//        values.put("Mushroom Farm", (values.get("Glass") * 4) + (values.get("Red Mushroom") * 2) + (values.get("Brown Mushroom") * 2) + (values.get("Tree Farm")));
//        values.put("Mushroom Harvester", (values.get("Glass") * 4) + (values.get("Red Mushroom") * 2) + (values.get("Brown Mushroom") * 2) + (values.get("Logger")));
//        values.put("Sugar Cane Harvester", (values.get("Glass") * 4) + (values.get("Sugar Cane") * 4) + (values.get("Logger")));
//        values.put("Nether Farm", (values.get("Glass") * 4) + (values.get("Nether Wart") * 4) + (values.get("Tree Farm")));
//        values.put("Nether Harvester", (values.get("Glass") * 4) + (values.get("Nether Wart") * 4) + (values.get("Logger")));
//
//        //Forestry Factory Machines
//        values.put("Fermenter", (values.get("Bronze Gear") * 4) + (values.get("Glass") * 4) + (values.get("Sturdy Machine")));
//        values.put("Still", (values.get("Redstone") * 4) + (values.get("Glass") * 4) + (values.get("Sturdy Machine")));
//        values.put("Bottler", (values.get("Glass") * 4) + (values.get("Can") * 4) + (values.get("Sturdy Machine")));
//        values.put("Raintank", (values.get("Glass") * 2) + (values.get("Iron") * 6) + (values.get("Sturdy Machine")));
//        values.put("BiopowerGenerator", (values.get("Glass") * 2) + (values.get("Gold") * 6) + (values.get("Sturdy Machine")));
//        values.put("Carpenter", (values.get("Glass") * 2) + (values.get("Bronze") * 2) + (values.get("Sturdy Machine")));
//        values.put("Moistener", (values.get("Glass") * 4) + (values.get("Copper Gear") * 4) + (values.get("Fermenter")));
//        values.put("Apiary", (values.get("Glass") * 2) + (values.get("Tin Gear") * 4) + (values.get("Sturdy Machine")));
//        values.put("Centrifuge", (values.get("Glass") * 2) + (values.get("Copper") * 6) + (values.get("Sturdy Machine")));
//        values.put("Squeezer", (values.get("Glass") * 2) + (values.get("Tin") * 6) + (values.get("Sturdy Machine")));
//        values.put("Fabricator", (values.get("Glass") * 3) + (values.get("Gold") * 4) + (values.get("Sturdy Machine") + (values.get("Chest"))));
//        values.put("Beealzyer", (values.get("Glass Pane") * 2) + (values.get("Tin") * 4) + (values.get("Redstone") * 2) + (values.get("Diamond") * 1));
//        values.put("Analyzer", (values.get("Beealzyer") + (values.get("Sturdy Machine") + (values.get("Bronze") * 4))));
//        values.put("Forester", (values.get("Peat Bog") + (values.get("Glass") * 4) + (values.get("Diamond") * 4)));
//        values.put("Rainmaker", (values.get("Hardened Machine") + (values.get("Glass") * 4) + (values.get("Tin Gear") * 4)));
//        values.put("Treetap", (values.get("Glass") * 4) + (values.get("Sturdy Machine") + (values.get("ic2 tree tap") * 4)));
//        values.put("Apichest", (values.get("Glass")));
//
//        //Forestry Mail Stuff
//        values.put("Mailbox", (values.get("Sturdy Machine") + (values.get("Chest") * 3) + (values.get("Tin") * 3)));
//        values.put("TradeStation", (values.get("Iron Tube") * 2) + (values.get("Bronze Tube") * 3) + (values.get("Sturdy Machine") + (values.get("Chest") * 2) + (values.get("Large Circuit Board"))));
//        values.put("StampCollector", (values.get("Glass")));
//
//        //Forestry Soil
//        values.put("Humus", (values.get("Fertilizer") + 8) / 8);
//        values.put("Bog", 9);
//        
//        // Bee Stuff
//        values.put("Wax", (values.get("Cactus")));
//
//        recipes();
//        pluginEE.readEMC(recipes);
//    }
//
//    @Override
//    protected void recipes() {
//        recipes = new Config("pluginEE_ForestryEMCValues.cfg");
//        recipes.addDefault("[Define Custom EMC Here]");
//        recipes.addDefault("# NameTag=ItemID,Damage Value,EMC Value");
//        recipes.addDefault("# Forestry Parts");
//        recipes.addDefault("SturdyMachine=" + ItemInterface.getItem("sturdyMachine").getItem().shiftedIndex + "," + 0 + "," + values.get("Sturdy Machine"));
//        recipes.addDefault("HardenedMachine=" + ItemInterface.getItem("hardenedMachine").itemID + "," + 0 + "," + values.get("Hardened Machine"));
//        recipes.addDefault("Fertilizer=" + ItemInterface.getItem("fertilizerCompound").itemID + "," + 0 + "," + values.get("Fertilizer"));
//        recipes.addDefault("# Gears");
//        recipes.addDefault("BronzeGear=" + ItemInterface.getItem("gearBronze").getItem().shiftedIndex + "," + 0 + "," + values.get("Bronze Gear"));
//        recipes.addDefault("CopperGear=" + ItemInterface.getItem("gearCopper").getItem().shiftedIndex + "," + 0 + "," + values.get("Copper Gear"));
//        recipes.addDefault("TinGear=" + ItemInterface.getItem("gearTin").getItem().shiftedIndex + "," + 0 + "," + values.get("Tin Gear"));
//        recipes.addDefault("# Electron Tubes");
//        recipes.addDefault("GoldenElectronTube=" + tubes.goldTube.itemID + "," + tubes.goldTube.getItemDamage() + "," + values.get("Golden Electron Tube"));
//        recipes.addDefault("DiamondElectronTube=" + tubes.diamondTube.itemID + "," + tubes.diamondTube.getItemDamage() + "," + values.get("Diamond Tube"));
//        recipes.addDefault("TinElectronTube=" + tubes.tinTube.itemID + "," + tubes.tinTube.getItemDamage() + "," + values.get("Tin Tube"));
//        recipes.addDefault("CopperElectronTube=" + tubes.copperTube.itemID + "," + tubes.copperTube.getItemDamage() + "," + values.get("Copper Tube"));
//        recipes.addDefault("IronElectronTube=" + tubes.ironTube.itemID + "," + tubes.ironTube.getItemDamage() + "," + values.get("Iron Tube"));
//        recipes.addDefault("BronzeElectronTube=" + tubes.bronzeTube.itemID + "," + tubes.bronzeTube.getItemDamage() + "," + values.get("Bronze Tube"));
//        recipes.addDefault("# Circuit Boards");
//        recipes.addDefault("SmallCircuitBoard=" + circuitBoards.smallCircuitBoard.itemID + "," + circuitBoards.smallCircuitBoard.getItemDamage() + "," + values.get("Small Circuit Board"));
//        recipes.addDefault("MediumCircuitBoard=" + circuitBoards.mediumCircuitBoard.itemID + "," + circuitBoards.mediumCircuitBoard.getItemDamage() + "," + values.get("Medium Circuit Board"));
//        recipes.addDefault("LargeCircuitBoard=" + circuitBoards.largeCircuitBoard.itemID + "," + +circuitBoards.largeCircuitBoard.getItemDamage() + "," + values.get("Large Circuit Board"));
//        recipes.addDefault("# Engines");
//        recipes.addDefault("BiogasEngine=" + ForestryBlock.engine.blockID + "," + 0 + "," + values.get("Biogas Engine"));
//        recipes.addDefault("PeatEngine=" + ForestryBlock.engine.blockID + "," + 1 + "," + values.get("Peat Engine"));
//        recipes.addDefault("ElectricalEngine=" + ForestryBlock.engine.blockID + "," + 2 + "," + values.get("Electrical Engine"));
//        recipes.addDefault("# Containers");
//        recipes.addDefault("Can=" + ItemInterface.getItem("canEmpty").itemID + "," + 0 + "," + values.get("Can"));
//        recipes.addDefault("# Farm Machines");
//        recipes.addDefault("TreeFarm=" + ForestryBlock.planter.blockID + "," + 0 + "," + values.get("Tree Farm"));
//        recipes.addDefault("Logger=" + ForestryBlock.harvester.blockID + "," + 0 + "," + values.get("Logger"));
//        recipes.addDefault("WheatFarm=" + ForestryBlock.planter.blockID + "," + 1 + "," + values.get("Wheat Farm"));
//        recipes.addDefault("Combine=" + ForestryBlock.harvester.blockID + "," + 1 + "," + values.get("Combine"));
//        recipes.addDefault("RubberFarm=" + ForestryBlock.planter.blockID + "," + 2 + "," + values.get("Tree Farm"));
//        recipes.addDefault("RubberHavester=" + ForestryBlock.harvester.blockID + "," + 2 + "," + values.get("Logger"));
//        recipes.addDefault("PumpkinFarm=" + ForestryBlock.planter.blockID + "," + 3 + "," + values.get("Pumpkin Farm"));
//        recipes.addDefault("PumpkinHarvester=" + ForestryBlock.harvester.blockID + "," + 3 + "," + values.get("Pumpkin Harvester"));
//        recipes.addDefault("PeatBog=" + ForestryBlock.planter.blockID + "," + 4 + "," + values.get("Peat Bog"));
//        recipes.addDefault("Turbary=" + ForestryBlock.harvester.blockID + "," + 4 + "," + values.get("Turbary"));
//        recipes.addDefault("CactusHarvester=" + ForestryBlock.harvester.blockID + "," + 5 + "," + values.get("Cactus Harvester"));
//        recipes.addDefault("MushroomFarm=" + ForestryBlock.planter.blockID + "," + 5 + "," + values.get("Mushroom Farm"));
//        recipes.addDefault("MushroomHarvester=" + ForestryBlock.harvester.blockID + "," + 6 + "," + values.get("Mushroom Harvester"));
//        recipes.addDefault("SugarcaneHarvester=" + ForestryBlock.harvester.blockID + "," + 7 + "," + values.get("Sugar Cane Harvester"));
//        recipes.addDefault("NetherFarm=" + ForestryBlock.planter.blockID + "," + 6 + "," + values.get("Nether Farm"));
//        recipes.addDefault("NetherHarvester=" + ForestryBlock.harvester.blockID + "," + 8 + "," + values.get("Nether Harvester"));
//        recipes.addDefault("# Factory Machines");
//        recipes.addDefault("Fermenter=" + ForestryBlock.machine.blockID + "," + 0 + "," + values.get("Fermenter"));
//        recipes.addDefault("Still=" + ForestryBlock.machine.blockID + "," + 1 + "," + values.get("Still"));
//        recipes.addDefault("Bottler=" + ForestryBlock.machine.blockID + "," + 2 + "," + values.get("Bottler"));
//        recipes.addDefault("Raintank=" + ForestryBlock.machine.blockID + "," + 3 + "," + values.get("Raintank"));
//        recipes.addDefault("BiopowerGenerator=" + ForestryBlock.machine.blockID + "," + 4 + "," + values.get("BiopowerGenerator"));
//        recipes.addDefault("Carpenter=" + ForestryBlock.machine.blockID + "," + 5 + "," + values.get("Carpenter"));
//        recipes.addDefault("Moistener=" + ForestryBlock.machine.blockID + "," + 6 + "," + values.get("Moistener"));
//        recipes.addDefault("Apiary=" + ForestryBlock.machine.blockID + "," + 7 + "," + values.get("Apiary"));
//        recipes.addDefault("Centrifuge=" + ForestryBlock.machine.blockID + "," + 8 + "," + values.get("Centrifuge"));
//        recipes.addDefault("Squeezer=" + ForestryBlock.machine.blockID + "," + 9 + "," + values.get("Squeezer"));
//        recipes.addDefault("ThermionicFabricator=" + ForestryBlock.machine.blockID + "," + 11 + "," + values.get("Fabricator"));
//        recipes.addDefault("Beealyzer=" + ItemInterface.getItem("beealyzer").itemID + "," + 0 + "," + values.get("Beealzyer"));
//        recipes.addDefault("Analzyer=" + ForestryBlock.mill.blockID + "," + 4 + "," + values.get("Analyzer"));
//        recipes.addDefault("# Since the Forester can be crafted from any farm, peat bog was used for the calculation.");
//        recipes.addDefault("Forester=" + ForestryBlock.mill.blockID + "," + 0 + "," + values.get("Forester"));
//        recipes.addDefault("Rainmaker=" + ForestryBlock.mill.blockID + "," + 1 + "," + values.get("Rainmaker"));
//        recipes.addDefault("Treetap=" + ForestryBlock.mill.blockID + "," + 2 + "," + values.get("Treetap"));
//        recipes.addDefault("Apichest=" + ForestryBlock.mill.blockID + "," + 3 + "," + values.get("Apichest"));
//        recipes.addDefault("# Mail Stuff");
//        recipes.addDefault("Mailbox=" + ForestryBlock.mill.blockID + "," + 5 + "," + values.get("Mailbox"));
//        recipes.addDefault("TradeStation=" + ForestryBlock.mill.blockID + "," + 6 + "," + values.get("TradeStation"));
//        recipes.addDefault("StampCollector=" + ForestryBlock.mill.blockID + "," + 7 + "," + values.get("StampCollector"));
//        recipes.addDefault("# Forestry Soil");
//        recipes.addDefault("Humus=" + ForestryBlock.soil.blockID + "," + 0 + "," + values.get("Humus"));
//        recipes.addDefault("Bog=" + ForestryBlock.soil.blockID + "," + 1 + "," + values.get("Bog"));
//        recipes.addDefault("# Bee Stuff");
//        recipes.addDefault("Wax=" + ItemInterface.getItem("beeswax").itemID + "," + 0 + "," + values.get("Wax"));
//        recipes.writeConfig();
//        recipes.readFile();
//    }
//
//    public static void load(pluginBase parent) {
//        baseModule b = new ForestryEMCModule(parent);
//        b.register();
//    }
//}
