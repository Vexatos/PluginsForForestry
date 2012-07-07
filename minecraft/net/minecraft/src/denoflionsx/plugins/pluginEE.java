package net.minecraft.src.denoflionsx.plugins;

import forestry.api.core.ForestryBlock;
import forestry.api.core.ItemInterface;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.EE.DefaultForestryValues;
import net.minecraft.src.denoflionsx.plugins.EE.customEMCParser;
import net.minecraft.src.denoflionsx.plugins.Forestry.circuitBoards;
import net.minecraft.src.denoflionsx.plugins.Forestry.tubes;

public class pluginEE extends pluginBase {

    private static Config recipes;

    public pluginEE() {
        this.name = "pluginEE";
        this.mod = "mod_EE";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!this.loaded) {
            this.defaults();
            this.runConfig();
            if (this.loaded = init()) {
                recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    @Override
    protected void defaults() {
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return this.hooked;
        }
        DefaultForestryValues.setup();
        this.Forestry();
        Iterator i = recipes.Options.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            String value = pairs.getValue().toString();
            customEMCParser.parse(value);
        }
        Iterator q = customEMCParser.Values.entrySet().iterator();
        while (q.hasNext()) {
            Map.Entry pairs = (Map.Entry) q.next();
            HashMap<Integer, Integer> t = (HashMap) pairs.getValue();
            Integer id = Integer.valueOf(pairs.getKey().toString());
            Iterator w = t.entrySet().iterator();
            while (w.hasNext()) {
                Map.Entry pairs2 = (Map.Entry) w.next();
                Integer dmg = Integer.valueOf(pairs2.getKey().toString());
                Integer value = Integer.valueOf(pairs2.getValue().toString());
                addEMC(id, dmg, value);
            }
        }

        this.hooked = true;
        return this.hooked;
    }

    protected void Forestry() {
        recipes = new Config("pluginEE_CustomEMCValues.cfg");
        recipes.addDefault("[Define Custom EMC Here]");
        recipes.addDefault("# NameTag=ItemID,Damage Value,EMC Value");
        recipes.addDefault("# Forestry Parts");
        recipes.addDefault("SturdyMachine=" + ItemInterface.getItem("sturdyMachine").getItem().shiftedIndex + "," + 0 + "," + DefaultForestryValues.values.get("Sturdy Machine"));
        recipes.addDefault("HardenedMachine=" + ItemInterface.getItem("hardenedMachine").itemID + "," + 0 + "," + DefaultForestryValues.values.get("Hardened Machine"));
        recipes.addDefault("Fertilizer=" + ItemInterface.getItem("fertilizerCompound").itemID + "," + 0 + "," + DefaultForestryValues.values.get("Fertilizer"));
        recipes.addDefault("# Gears");
        recipes.addDefault("BronzeGear=" + ItemInterface.getItem("gearBronze").getItem().shiftedIndex + "," + 0 + "," + DefaultForestryValues.values.get("Bronze Gear"));
        recipes.addDefault("CopperGear=" + ItemInterface.getItem("gearCopper").getItem().shiftedIndex + "," + 0 + "," + DefaultForestryValues.values.get("Copper Gear"));
        recipes.addDefault("TinGear=" + ItemInterface.getItem("gearTin").getItem().shiftedIndex + "," + 0 + "," + DefaultForestryValues.values.get("Tin Gear"));
        recipes.addDefault("# Electron Tubes");
        recipes.addDefault("GoldenElectronTube=" + tubes.goldTube.itemID + "," + tubes.goldTube.getItemDamage() + "," + DefaultForestryValues.values.get("Golden Electron Tube"));
        recipes.addDefault("DiamondElectronTube=" + tubes.diamondTube.itemID + "," + tubes.diamondTube.getItemDamage() + "," + DefaultForestryValues.values.get("Diamond Tube"));
        recipes.addDefault("TinElectronTube=" + tubes.tinTube.itemID + "," + tubes.tinTube.getItemDamage() + "," + DefaultForestryValues.values.get("Tin Tube"));
        recipes.addDefault("CopperElectronTube=" + tubes.copperTube.itemID + "," + tubes.copperTube.getItemDamage() + "," + DefaultForestryValues.values.get("Copper Tube"));
        recipes.addDefault("IronElectronTube=" + tubes.ironTube.itemID + "," + tubes.ironTube.getItemDamage() + "," + DefaultForestryValues.values.get("Iron Tube"));
        recipes.addDefault("BronzeElectronTube=" + tubes.bronzeTube.itemID + "," + tubes.bronzeTube.getItemDamage() + "," + DefaultForestryValues.values.get("Bronze Tube"));
        recipes.addDefault("# Circuit Boards");
        recipes.addDefault("SmallCircuitBoard=" + circuitBoards.smallCircuitBoard.itemID + "," + circuitBoards.smallCircuitBoard.getItemDamage() + "," + DefaultForestryValues.values.get("Small Circuit Board"));
        recipes.addDefault("MediumCircuitBoard=" + circuitBoards.mediumCircuitBoard.itemID + "," + circuitBoards.mediumCircuitBoard.getItemDamage() + "," + DefaultForestryValues.values.get("Medium Circuit Board"));
        recipes.addDefault("LargeCircuitBoard=" + circuitBoards.largeCircuitBoard.itemID + "," + + circuitBoards.largeCircuitBoard.getItemDamage() + "," + DefaultForestryValues.values.get("Large Circuit Board"));
        recipes.addDefault("# Engines");
        recipes.addDefault("BiogasEngine=" + ForestryBlock.engine.blockID + "," + 0 + "," + DefaultForestryValues.values.get("Biogas Engine"));
        recipes.addDefault("PeatEngine=" + ForestryBlock.engine.blockID + "," + 1 + "," + DefaultForestryValues.values.get("Peat Engine"));
        recipes.addDefault("ElectricalEngine=" + ForestryBlock.engine.blockID + "," + 2 + "," + DefaultForestryValues.values.get("Electrical Engine"));
        recipes.addDefault("# Containers");
        recipes.addDefault("Can=" + ItemInterface.getItem("canEmpty").itemID + "," + 0 + "," + DefaultForestryValues.values.get("Can"));
        recipes.addDefault("# Farm Machines");
        recipes.addDefault("TreeFarm=" + ForestryBlock.planter.blockID + "," + 0 + "," + DefaultForestryValues.values.get("Tree Farm"));
        recipes.addDefault("Logger=" + ForestryBlock.harvester.blockID + "," + 0 + "," + DefaultForestryValues.values.get("Logger"));
        recipes.addDefault("WheatFarm=" + ForestryBlock.planter.blockID + "," + 1 + "," + DefaultForestryValues.values.get("Wheat Farm"));
        recipes.addDefault("Combine=" + ForestryBlock.harvester.blockID + "," + 1 + "," + DefaultForestryValues.values.get("Combine"));
        recipes.addDefault("RubberFarm=" + ForestryBlock.planter.blockID + "," + 2 + "," + DefaultForestryValues.values.get("Tree Farm"));
        recipes.addDefault("RubberHavester=" + ForestryBlock.harvester.blockID + "," + 2 + "," + DefaultForestryValues.values.get("Logger"));
        recipes.addDefault("PumpkinFarm=" + ForestryBlock.planter.blockID + "," + 3 + "," + DefaultForestryValues.values.get("Pumpkin Farm"));
        recipes.addDefault("PumpkinHarvester=" + ForestryBlock.harvester.blockID + "," + 3 + "," + DefaultForestryValues.values.get("Pumpkin Harvester"));
        recipes.addDefault("PeatBog=" + ForestryBlock.planter.blockID + "," + 4 + "," + DefaultForestryValues.values.get("Peat Bog"));
        recipes.addDefault("Turbary=" + ForestryBlock.harvester.blockID + "," + 4 + "," + DefaultForestryValues.values.get("Turbary"));
        recipes.addDefault("CactusHarvester=" + ForestryBlock.harvester.blockID + "," + 5 + "," + DefaultForestryValues.values.get("Cactus Harvester"));
        recipes.addDefault("MushroomFarm=" + ForestryBlock.planter.blockID + "," + 5 + "," + DefaultForestryValues.values.get("Mushroom Farm"));
        recipes.addDefault("MushroomHarvester=" + ForestryBlock.harvester.blockID + "," + 6 + "," + DefaultForestryValues.values.get("Mushroom Harvester"));
        recipes.addDefault("SugarcaneHarvester=" + ForestryBlock.harvester.blockID + "," + 7 + "," + DefaultForestryValues.values.get("Sugar Cane Harvester"));
        recipes.addDefault("NetherFarm=" + ForestryBlock.planter.blockID + "," + 6 + "," + DefaultForestryValues.values.get("Nether Farm"));
        recipes.addDefault("NetherHarvester=" + ForestryBlock.harvester.blockID + "," + 8 + "," + DefaultForestryValues.values.get("Nether Harvester"));
        recipes.addDefault("# Factory Machines");
        recipes.addDefault("Fermenter=" + ForestryBlock.machine.blockID + "," + 0 + "," + DefaultForestryValues.values.get("Fermenter"));
        recipes.addDefault("Still=" + ForestryBlock.machine.blockID + "," + 1 + "," + DefaultForestryValues.values.get("Still"));
        recipes.addDefault("Bottler=" + ForestryBlock.machine.blockID + "," + 2 + "," + DefaultForestryValues.values.get("Bottler"));
        recipes.addDefault("Raintank=" + ForestryBlock.machine.blockID + "," + 3 + "," + DefaultForestryValues.values.get("Raintank"));
        recipes.addDefault("BiopowerGenerator=" + ForestryBlock.machine.blockID + "," + 4 + "," + DefaultForestryValues.values.get("BiopowerGenerator"));
        recipes.addDefault("Carpenter=" + ForestryBlock.machine.blockID + "," + 5 + "," + DefaultForestryValues.values.get("Carpenter"));
        recipes.addDefault("Moistener=" + ForestryBlock.machine.blockID + "," + 6 + "," + DefaultForestryValues.values.get("Moistener"));
        recipes.addDefault("Apiary=" + ForestryBlock.machine.blockID + "," + 7 + "," + DefaultForestryValues.values.get("Apiary"));
        recipes.addDefault("Centrifuge=" + ForestryBlock.machine.blockID + "," + 8 + "," + DefaultForestryValues.values.get("Centrifuge"));
        recipes.addDefault("Squeezer=" + ForestryBlock.machine.blockID + "," + 9 + "," + DefaultForestryValues.values.get("Squeezer"));
        recipes.addDefault("ThermionicFabricator=" + ForestryBlock.machine.blockID + "," + 11 + "," + DefaultForestryValues.values.get("Fabricator"));
        recipes.addDefault("Beealyzer=" + ItemInterface.getItem("beealyzer").itemID + "," + 0 + "," + DefaultForestryValues.values.get("Beealzyer"));
        recipes.addDefault("Analzyer=" + ForestryBlock.mill.blockID + "," + 4 + "," + DefaultForestryValues.values.get("Analyzer"));
        recipes.addDefault("# Since the Forester can be crafted from any farm, peat bog was used for the calculation.");
        recipes.addDefault("Forester=" + ForestryBlock.mill.blockID + "," + 0 + "," + DefaultForestryValues.values.get("Forester"));
        recipes.addDefault("Rainmaker=" + ForestryBlock.mill.blockID + "," + 1 + "," + DefaultForestryValues.values.get("Rainmaker"));
        recipes.addDefault("Treetap=" + ForestryBlock.mill.blockID + "," + 2 + "," + DefaultForestryValues.values.get("Treetap"));
        recipes.addDefault("Apichest=" + ForestryBlock.mill.blockID + "," + 3 + "," + DefaultForestryValues.values.get("Apichest"));
        recipes.addDefault("# Mail Stuff");
        recipes.addDefault("Mailbox=" + ForestryBlock.mill.blockID + "," + 5 + "," + DefaultForestryValues.values.get("Mailbox"));
        recipes.addDefault("TradeStation=" + ForestryBlock.mill.blockID + "," + 6 + "," + DefaultForestryValues.values.get("TradeStation"));
        recipes.addDefault("StampCollector=" + ForestryBlock.mill.blockID + "," + 7 + "," + DefaultForestryValues.values.get("StampCollector"));
        recipes.addDefault("# Forestry Soil");
        recipes.addDefault("Humus=" + ForestryBlock.soil.blockID + "," + 0 + "," + DefaultForestryValues.values.get("Humus"));
        recipes.addDefault("Bog=" + ForestryBlock.soil.blockID + "," + 1 + "," + DefaultForestryValues.values.get("Bog"));
        recipes.writeConfig();
        recipes.readFile();
    }

    protected void addEMC(int id, int dmg, int v) {
        try {
            // core.print("Damage: " + dmg);
            Class EEMaps = Class.forName("ee.EEMaps");
            Field alchemicalValues_Field = EEMaps.getField("alchemicalValues");
            HashMap<Integer, HashMap> alchemicalValues = (HashMap) alchemicalValues_Field.get(null);
            HashMap<Integer, Integer> temp = new HashMap();
            if (alchemicalValues.get(id) != null) {
                //core.print("Duplicate EMC found.");
                temp = alchemicalValues.get(id);
            }
            temp.put(dmg, v);
            alchemicalValues.put(id, temp);
            alchemicalValues_Field.set(null, alchemicalValues);
            //core.print(temp.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //core.print("Added Alchemical Value for: " + id + " " + dmg);
        }
    }

    protected void blacklistItem(Item i) {
        try {
            Class EEMaps = Class.forName("ee.EEMaps");
            Field Values = EEMaps.getField("alchemicalValues");
            Values.setAccessible(true);
            HashMap alchemicalValues = (HashMap) Values.get(null);
            HashMap Temp = new HashMap();
            Temp.put(0, 0);
            alchemicalValues.remove(i.shiftedIndex);
            alchemicalValues.put(i.shiftedIndex, Temp);
            Values.set(null, alchemicalValues);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            core.print("Removed EMC Value for " + i.getItemName());
        }
    }

    protected void blacklistItem(Block i) {
        try {
            Class EEMaps = Class.forName("ee.EEMaps");
            Field Values = EEMaps.getField("alchemicalValues");
            Values.setAccessible(true);
            HashMap alchemicalValues = (HashMap) Values.get(null);
            HashMap Temp = new HashMap();
            Temp.put(0, 0);
            alchemicalValues.remove(i.blockID);
            alchemicalValues.put(i.blockID, Temp);
            Values.set(null, alchemicalValues);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            core.print("Removed EMC Value for " + i.getBlockName());
        }
    }

    protected void killEEItem(String i) {
        try {
            Class EEItems = Class.forName("ee.EEItem");
            Field item = EEItems.getField(i);
            item.setAccessible(true);
            Item z = (Item) item.get(null);
            int id = z.shiftedIndex;
            Item.itemsList[id] = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            core.print("Killed EE Item " + i);
        }

    }

    @Override
    protected void recipes() {
    }
}
