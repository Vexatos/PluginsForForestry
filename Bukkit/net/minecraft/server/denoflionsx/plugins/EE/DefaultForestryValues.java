package net.minecraft.server.denoflionsx.plugins.EE;

import forestry.api.core.ItemInterface;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.server.Block;
import net.minecraft.server.Item;

public class DefaultForestryValues
{
    public static HashMap values = new HashMap();
    private static Class EEMaps;
    private static Field alchemicalValues_Field;
    private static HashMap alchemicalValues = new HashMap();

    public static void setup()
    {
        hookEE();
        values.put("Gold", Integer.valueOf(getEEValue(Item.GOLD_INGOT.id, 0)));
        values.put("Tin", Integer.valueOf(getEEValue(ItemInterface.getItem("ingotTin").getItem().id, 0)));
        values.put("Bronze", Integer.valueOf(getEEValue(ItemInterface.getItem("ingotBronze").getItem().id, 0)));
        values.put("Copper", Integer.valueOf(getEEValue(ItemInterface.getItem("ingotCopper").getItem().id, 0)));
        values.put("Iron", Integer.valueOf(getEEValue(Item.IRON_INGOT.id, 0)));
        values.put("Glass", Integer.valueOf(getEEValue(Block.GLASS.id, 0)));
        values.put("Piston", Integer.valueOf(getEEValue(Block.PISTON.id, 0)));
        values.put("Stick", Integer.valueOf(getEEValue(Item.STICK.id, 0)));
        values.put("Cobblestone", Integer.valueOf(getEEValue(Block.COBBLESTONE.id, 0)));
        values.put("Redstone", Integer.valueOf(getEEValue(Item.REDSTONE.id, 0)));
        values.put("Diamond", Integer.valueOf(getEEValue(Item.DIAMOND.id, 0)));
        values.put("Pumpkin", Integer.valueOf(getEEValue(Block.PUMPKIN.id, 0)));
        values.put("Melon", Integer.valueOf(getEEValue(Block.MELON.id, 0)));
        values.put("Cactus", Integer.valueOf(getEEValue(Block.CACTUS.id, 0)));
        values.put("Red Mushroom", Integer.valueOf(getEEValue(Block.RED_MUSHROOM.id, 0)));
        values.put("Brown Mushroom", Integer.valueOf(getEEValue(Block.BROWN_MUSHROOM.id, 0)));
        values.put("Sugar Cane", Integer.valueOf(getEEValue(Item.SUGAR_CANE.id, 0)));
        values.put("Nether Wart", Integer.valueOf(getEEValue(Item.NETHER_STALK.id, 0)));
        values.put("Chest", Integer.valueOf(getEEValue(Block.CHEST.id, 0)));
        values.put("Glass Pane", Integer.valueOf(1));
        values.put("Wood Plank", Integer.valueOf(getEEValue(Block.WOOD.id, 0)));
        values.put("Wooden Gear", Integer.valueOf(((Integer)values.get("Stick")).intValue() * 4));
        values.put("Stone Gear", Integer.valueOf(((Integer)values.get("Cobblestone")).intValue() * 4 + ((Integer)values.get("Wooden Gear")).intValue()));
        values.put("ic2 tree tap", Integer.valueOf(((Integer)values.get("Wood Plank")).intValue() * 5));
        values.put("Sturdy Machine", Integer.valueOf(((Integer)values.get("Bronze")).intValue() * 8));
        values.put("Hardened Machine", Integer.valueOf(((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Diamond")).intValue() * 4));
        values.put("Fertilizer", Integer.valueOf(24));
        values.put("Bronze Gear", Integer.valueOf(((Integer)values.get("Bronze")).intValue() * 4 + ((Integer)values.get("Stone Gear")).intValue()));
        values.put("Copper Gear", Integer.valueOf(((Integer)values.get("Copper")).intValue() * 4 + ((Integer)values.get("Stone Gear")).intValue()));
        values.put("Tin Gear", Integer.valueOf(((Integer)values.get("Tin")).intValue() * 4 + ((Integer)values.get("Stone Gear")).intValue()));
        values.put("Small Circuit Board", Integer.valueOf(((Integer)values.get("Tin")).intValue() + ((Integer)values.get("Redstone")).intValue() * 6));
        values.put("Medium Circuit Board", Integer.valueOf(((Integer)values.get("Redstone")).intValue() * 6 + ((Integer)values.get("Bronze")).intValue() * 3));
        values.put("Large Circuit Board", Integer.valueOf(((Integer)values.get("Redstone")).intValue() * 6 + ((Integer)values.get("Gold")).intValue() * 3));
        values.put("Golden Electron Tube", Integer.valueOf((((Integer)values.get("Gold")).intValue() * 5 + ((Integer)values.get("Redstone")).intValue() * 2) / 4));
        values.put("Diamond Tube", Integer.valueOf((((Integer)values.get("Diamond")).intValue() * 5 + ((Integer)values.get("Redstone")).intValue() * 2) / 4));
        values.put("Bronze Tube", Integer.valueOf((((Integer)values.get("Bronze")).intValue() * 5 + ((Integer)values.get("Redstone")).intValue() * 2) / 4));
        values.put("Iron Tube", Integer.valueOf((((Integer)values.get("Iron")).intValue() * 5 + ((Integer)values.get("Redstone")).intValue() * 2) / 4));
        values.put("Copper Tube", Integer.valueOf((((Integer)values.get("Copper")).intValue() * 5 + ((Integer)values.get("Redstone")).intValue() * 2) / 4));
        values.put("Tin Tube", Integer.valueOf((((Integer)values.get("Tin")).intValue() * 5 + ((Integer)values.get("Redstone")).intValue() * 2) / 4));
        values.put("Can", Integer.valueOf(((Integer)values.get("Tin")).intValue() * 3 / 12));
        values.put("Peat Engine", Integer.valueOf(((Integer)values.get("Copper")).intValue() * 3 + ((Integer)values.get("Copper Gear")).intValue() * 2 + ((Integer)values.get("Piston")).intValue() + ((Integer)values.get("Glass")).intValue()));
        values.put("Electrical Engine", Integer.valueOf(((Integer)values.get("Tin")).intValue() * 3 + ((Integer)values.get("Piston")).intValue() + ((Integer)values.get("Glass")).intValue() + ((Integer)values.get("Tin Gear")).intValue() * 2));
        values.put("Biogas Engine", Integer.valueOf(((Integer)values.get("Piston")).intValue() + ((Integer)values.get("Glass")).intValue() + ((Integer)values.get("Bronze Gear")).intValue() * 2 + ((Integer)values.get("Bronze")).intValue() * 3));
        values.put("Tree Farm", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Golden Electron Tube")).intValue() * 3 + ((Integer)values.get("Small Circuit Board")).intValue()));
        values.put("Logger", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Diamond Tube")).intValue() * 3 + ((Integer)values.get("Small Circuit Board")).intValue()));
        values.put("Wheat Farm", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Bronze Tube")).intValue() * 3 + ((Integer)values.get("Small Circuit Board")).intValue()));
        values.put("Pumpkin Farm", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Pumpkin")).intValue() * 2 + ((Integer)values.get("Melon")).intValue() * 2 + ((Integer)values.get("Tree Farm")).intValue()));
        values.put("Combine", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Iron Tube")).intValue() * 3 + ((Integer)values.get("Small Circuit Board")).intValue()));
        values.put("Pumpkin Harvester", Integer.valueOf(((Integer)values.get("Pumpkin")).intValue() * 2 + ((Integer)values.get("Melon")).intValue() * 2 + ((Integer)values.get("Logger")).intValue()));
        values.put("Peat Bog", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Copper Tube")).intValue() * 3 + ((Integer)values.get("Small Circuit Board")).intValue()));
        values.put("Turbary", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Tin Tube")).intValue() * 3 + ((Integer)values.get("Small Circuit Board")).intValue()));
        values.put("Cactus Harvester", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Cactus")).intValue() * 4 + ((Integer)values.get("Logger")).intValue()));
        values.put("Mushroom Farm", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Red Mushroom")).intValue() * 2 + ((Integer)values.get("Brown Mushroom")).intValue() * 2 + ((Integer)values.get("Tree Farm")).intValue()));
        values.put("Mushroom Harvester", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Red Mushroom")).intValue() * 2 + ((Integer)values.get("Brown Mushroom")).intValue() * 2 + ((Integer)values.get("Logger")).intValue()));
        values.put("Sugar Cane Harvester", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sugar Cane")).intValue() * 4 + ((Integer)values.get("Logger")).intValue()));
        values.put("Nether Farm", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Nether Wart")).intValue() * 4 + ((Integer)values.get("Tree Farm")).intValue()));
        values.put("Nether Harvester", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Nether Wart")).intValue() * 4 + ((Integer)values.get("Logger")).intValue()));
        values.put("Fermenter", Integer.valueOf(((Integer)values.get("Bronze Gear")).intValue() * 4 + ((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Still", Integer.valueOf(((Integer)values.get("Redstone")).intValue() * 4 + ((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Bottler", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Can")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Raintank", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 2 + ((Integer)values.get("Iron")).intValue() * 6 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("BiopowerGenerator", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 2 + ((Integer)values.get("Gold")).intValue() * 6 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Carpenter", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 2 + ((Integer)values.get("Bronze")).intValue() * 2 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Moistener", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Copper Gear")).intValue() * 4 + ((Integer)values.get("Fermenter")).intValue()));
        values.put("Apiary", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 2 + ((Integer)values.get("Tin Gear")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Centrifuge", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 2 + ((Integer)values.get("Copper")).intValue() * 6 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Squeezer", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 2 + ((Integer)values.get("Tin")).intValue() * 6 + ((Integer)values.get("Sturdy Machine")).intValue()));
        values.put("Fabricator", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 3 + ((Integer)values.get("Gold")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Chest")).intValue()));
        values.put("Beealzyer", Integer.valueOf(((Integer)values.get("Glass Pane")).intValue() * 2 + ((Integer)values.get("Tin")).intValue() * 4 + ((Integer)values.get("Redstone")).intValue() * 2 + ((Integer)values.get("Diamond")).intValue() * 1));
        values.put("Analyzer", Integer.valueOf(((Integer)values.get("Beealzyer")).intValue() + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Bronze")).intValue() * 4));
        values.put("Forester", Integer.valueOf(((Integer)values.get("Peat Bog")).intValue() + ((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Diamond")).intValue() * 4));
        values.put("Rainmaker", Integer.valueOf(((Integer)values.get("Hardened Machine")).intValue() + ((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Tin Gear")).intValue() * 4));
        values.put("Treetap", Integer.valueOf(((Integer)values.get("Glass")).intValue() * 4 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("ic2 tree tap")).intValue() * 4));
        values.put("Apichest", values.get("Glass"));
        values.put("Mailbox", Integer.valueOf(((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Chest")).intValue() * 3 + ((Integer)values.get("Tin")).intValue() * 3));
        values.put("TradeStation", Integer.valueOf(((Integer)values.get("Iron Tube")).intValue() * 2 + ((Integer)values.get("Bronze Tube")).intValue() * 3 + ((Integer)values.get("Sturdy Machine")).intValue() + ((Integer)values.get("Chest")).intValue() * 2 + ((Integer)values.get("Large Circuit Board")).intValue()));
        values.put("StampCollector", values.get("Glass"));
        values.put("Humus", Integer.valueOf((((Integer)values.get("Fertilizer")).intValue() + 8) / 8));
        values.put("Bog", Integer.valueOf(9));
    }

    private static void hookEE()
    {
        try
        {
            EEMaps = Class.forName("ee.EEMaps");
            alchemicalValues_Field = EEMaps.getField("alchemicalValues");
            alchemicalValues = (HashMap)alchemicalValues_Field.get((Object)null);
        }
        catch (Exception var1)
        {
            var1.printStackTrace();
        }
    }

    public static int getEEValue(int var0, int var1)
    {
        hookEE();
        HashMap var2 = (HashMap)alchemicalValues.get(Integer.valueOf(var0));
        int var3 = ((Integer)var2.get(Integer.valueOf(var1))).intValue();
        return var3;
    }

    public static void forceSetValue(int var0, HashMap var1)
    {
        try
        {
            hookEE();
            alchemicalValues.put(Integer.valueOf(var0), var1);
            alchemicalValues_Field.set((Object)null, alchemicalValues);
        }
        catch (Exception var3)
        {
            ;
        }
    }
}
