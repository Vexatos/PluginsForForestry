package denoflionsx.Old.EE;

//package denoflionsx.plugins.EE;
//
//import forestry.api.core.ItemInterface;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import net.minecraft.src.Block;
//import net.minecraft.src.Item;
//
//public class DefaultForestryValues {
//
//    public static HashMap<String, Integer> values = new HashMap();
//    private static Class EEMaps;
//    private static Field alchemicalValues_Field;
//    private static HashMap<Integer, HashMap> alchemicalValues = new HashMap();
//
//    public static void setup() {
//        hookEE();
//        // Ingots
//        values.put("Gold", getEEValue(Item.ingotGold.shiftedIndex, 0));
//        values.put("Tin", getEEValue(ItemInterface.getItem("ingotTin").getItem().shiftedIndex, 0));
//        values.put("Bronze", getEEValue(ItemInterface.getItem("ingotBronze").getItem().shiftedIndex, 0));
//        values.put("Copper", getEEValue(ItemInterface.getItem("ingotCopper").getItem().shiftedIndex, 0));
//        values.put("Iron", getEEValue(Item.ingotIron.shiftedIndex, 0));
//
//        //Misc
//        values.put("Glass", getEEValue(Block.glass.blockID, 0));
//        values.put("Piston", getEEValue(Block.pistonBase.blockID, 0));
//        values.put("Stick", getEEValue(Item.stick.shiftedIndex, 0));
//        values.put("Cobblestone", getEEValue(Block.cobblestone.blockID, 0));
//        values.put("Redstone", getEEValue(Item.redstone.shiftedIndex, 0));
//        values.put("Diamond", getEEValue(Item.diamond.shiftedIndex, 0));
//        values.put("Pumpkin", getEEValue(Block.pumpkin.blockID, 0));
//        values.put("Melon", getEEValue(Block.melon.blockID, 0));
//        values.put("Cactus", getEEValue(Block.cactus.blockID, 0));
//        values.put("Red Mushroom", getEEValue(Block.mushroomRed.blockID, 0));
//        values.put("Brown Mushroom", getEEValue(Block.mushroomBrown.blockID, 0));
//        values.put("Sugar Cane", getEEValue(Item.reed.shiftedIndex, 0));
//        values.put("Nether Wart", getEEValue(Item.netherStalkSeeds.shiftedIndex, 0));
//        values.put("Chest", getEEValue(Block.chest.blockID, 0));
//        values.put("Glass Pane", 1);
//        values.put("Wood Plank", getEEValue(Block.planks.blockID,0));
//
//        //BC Parts.
//        values.put("Wooden Gear", (values.get("Stick") * 4));
//        values.put("Stone Gear", (values.get("Cobblestone") * 4) + values.get("Wooden Gear"));
//
//        // IC2 Treetap
//        values.put("ic2 tree tap", (values.get("Wood Plank") * 5));
//
//        //Forestry Parts
//        values.put("Sturdy Machine", (values.get("Bronze") * 8));
//        values.put("Hardened Machine", (values.get("Sturdy Machine")) + (values.get("Diamond") * 4));
//        values.put("Fertilizer", 24);
//        // Gears
//        values.put("Bronze Gear", (values.get("Bronze") * 4) + values.get("Stone Gear"));
//        values.put("Copper Gear", (values.get("Copper") * 4) + (values.get("Stone Gear")));
//        values.put("Tin Gear", (values.get("Tin") * 4) + (values.get("Stone Gear")));
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
//    }
//
//    private static void hookEE() {
//        try {
//            EEMaps = Class.forName("ee.EEMaps");
//            alchemicalValues_Field = EEMaps.getField("alchemicalValues");
//            alchemicalValues = (HashMap) alchemicalValues_Field.get(null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static int getEEValue(int id, int dmg) {
//        hookEE();
//        HashMap<Integer, Integer> h = alchemicalValues.get(id);
//        int value = h.get(dmg);
//        return value;
//    }
//
//    public static void forceSetValue(int id, HashMap<Integer, Integer> values) {
//        try {
//            // Recall EE hook to refresh values.
//            hookEE();
//            alchemicalValues.put(id, values);
//            alchemicalValues_Field.set(null, alchemicalValues);
//        } catch (Exception ex) {
//        }
//    }
//}
