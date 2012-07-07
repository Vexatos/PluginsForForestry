package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.EE.DefaultForestryValues;
import net.minecraft.server.denoflionsx.plugins.EE.customEMCParser;
import net.minecraft.server.denoflionsx.plugins.Forestry.circuitBoards;
import net.minecraft.server.denoflionsx.plugins.Forestry.tubes;
import forestry.api.core.ForestryBlock;
import forestry.api.core.ItemInterface;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.server.Block;
import net.minecraft.server.Item;

public class pluginEE extends pluginBase
{
    private static Config recipes;

    public pluginEE()
    {
        this.name = "pluginEE";
        this.mod = "mod_EE";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();
            this.runConfig();

            if (this.loaded = this.init())
            {
                this.recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    protected void defaults() {}

    protected boolean init()
    {
        if (!this.detect())
        {
            return this.hooked;
        }
        else
        {
            DefaultForestryValues.setup();
            this.Forestry();
            Iterator var1 = recipes.Options.entrySet().iterator();

            while (var1.hasNext())
            {
                Entry var2 = (Entry)var1.next();
                String var3 = var2.getValue().toString();
                customEMCParser.parse(var3);
            }

            Iterator var10 = customEMCParser.Values.entrySet().iterator();

            while (var10.hasNext())
            {
                Entry var11 = (Entry)var10.next();
                HashMap var4 = (HashMap)var11.getValue();
                Integer var5 = Integer.valueOf(var11.getKey().toString());
                Iterator var6 = var4.entrySet().iterator();

                while (var6.hasNext())
                {
                    Entry var7 = (Entry)var6.next();
                    Integer var8 = Integer.valueOf(var7.getKey().toString());
                    Integer var9 = Integer.valueOf(var7.getValue().toString());
                    this.addEMC(var5.intValue(), var8.intValue(), var9.intValue());
                }
            }

            this.hooked = true;
            return this.hooked;
        }
    }

    protected void Forestry()
    {
        recipes = new Config("pluginEE_CustomEMCValues.cfg");
        recipes.addDefault("[Define Custom EMC Here]");
        recipes.addDefault("# NameTag=ItemID,Damage Value,EMC Value");
        recipes.addDefault("# Forestry Parts");
        recipes.addDefault("SturdyMachine=" + ItemInterface.getItem("sturdyMachine").getItem().id + "," + 0 + "," + DefaultForestryValues.values.get("Sturdy Machine"));
        recipes.addDefault("HardenedMachine=" + ItemInterface.getItem("hardenedMachine").id + "," + 0 + "," + DefaultForestryValues.values.get("Hardened Machine"));
        recipes.addDefault("Fertilizer=" + ItemInterface.getItem("fertilizerCompound").id + "," + 0 + "," + DefaultForestryValues.values.get("Fertilizer"));
        recipes.addDefault("# Gears");
        recipes.addDefault("BronzeGear=" + ItemInterface.getItem("gearBronze").getItem().id + "," + 0 + "," + DefaultForestryValues.values.get("Bronze Gear"));
        recipes.addDefault("CopperGear=" + ItemInterface.getItem("gearCopper").getItem().id + "," + 0 + "," + DefaultForestryValues.values.get("Copper Gear"));
        recipes.addDefault("TinGear=" + ItemInterface.getItem("gearTin").getItem().id + "," + 0 + "," + DefaultForestryValues.values.get("Tin Gear"));
        recipes.addDefault("# Electron Tubes");
        recipes.addDefault("GoldenElectronTube=" + tubes.goldTube.id + "," + tubes.goldTube.getData() + "," + DefaultForestryValues.values.get("Golden Electron Tube"));
        recipes.addDefault("DiamondElectronTube=" + tubes.diamondTube.id + "," + tubes.diamondTube.getData() + "," + DefaultForestryValues.values.get("Diamond Tube"));
        recipes.addDefault("TinElectronTube=" + tubes.tinTube.id + "," + tubes.tinTube.getData() + "," + DefaultForestryValues.values.get("Tin Tube"));
        recipes.addDefault("CopperElectronTube=" + tubes.copperTube.id + "," + tubes.copperTube.getData() + "," + DefaultForestryValues.values.get("Copper Tube"));
        recipes.addDefault("IronElectronTube=" + tubes.ironTube.id + "," + tubes.ironTube.getData() + "," + DefaultForestryValues.values.get("Iron Tube"));
        recipes.addDefault("BronzeElectronTube=" + tubes.bronzeTube.id + "," + tubes.bronzeTube.getData() + "," + DefaultForestryValues.values.get("Bronze Tube"));
        recipes.addDefault("# Circuit Boards");
        recipes.addDefault("SmallCircuitBoard=" + circuitBoards.smallCircuitBoard.id + "," + circuitBoards.smallCircuitBoard.getData() + "," + DefaultForestryValues.values.get("Small Circuit Board"));
        recipes.addDefault("MediumCircuitBoard=" + circuitBoards.mediumCircuitBoard.id + "," + circuitBoards.mediumCircuitBoard.getData() + "," + DefaultForestryValues.values.get("Medium Circuit Board"));
        recipes.addDefault("LargeCircuitBoard=" + circuitBoards.largeCircuitBoard.id + "," + circuitBoards.largeCircuitBoard.getData() + "," + DefaultForestryValues.values.get("Large Circuit Board"));
        recipes.addDefault("# Engines");
        recipes.addDefault("BiogasEngine=" + ForestryBlock.engine.id + "," + 0 + "," + DefaultForestryValues.values.get("Biogas Engine"));
        recipes.addDefault("PeatEngine=" + ForestryBlock.engine.id + "," + 1 + "," + DefaultForestryValues.values.get("Peat Engine"));
        recipes.addDefault("ElectricalEngine=" + ForestryBlock.engine.id + "," + 2 + "," + DefaultForestryValues.values.get("Electrical Engine"));
        recipes.addDefault("# Containers");
        recipes.addDefault("Can=" + ItemInterface.getItem("canEmpty").id + "," + 0 + "," + DefaultForestryValues.values.get("Can"));
        recipes.addDefault("# Farm Machines");
        recipes.addDefault("TreeFarm=" + ForestryBlock.planter.id + "," + 0 + "," + DefaultForestryValues.values.get("Tree Farm"));
        recipes.addDefault("Logger=" + ForestryBlock.harvester.id + "," + 0 + "," + DefaultForestryValues.values.get("Logger"));
        recipes.addDefault("WheatFarm=" + ForestryBlock.planter.id + "," + 1 + "," + DefaultForestryValues.values.get("Wheat Farm"));
        recipes.addDefault("Combine=" + ForestryBlock.harvester.id + "," + 1 + "," + DefaultForestryValues.values.get("Combine"));
        recipes.addDefault("RubberFarm=" + ForestryBlock.planter.id + "," + 2 + "," + DefaultForestryValues.values.get("Tree Farm"));
        recipes.addDefault("RubberHavester=" + ForestryBlock.harvester.id + "," + 2 + "," + DefaultForestryValues.values.get("Logger"));
        recipes.addDefault("PumpkinFarm=" + ForestryBlock.planter.id + "," + 3 + "," + DefaultForestryValues.values.get("Pumpkin Farm"));
        recipes.addDefault("PumpkinHarvester=" + ForestryBlock.harvester.id + "," + 3 + "," + DefaultForestryValues.values.get("Pumpkin Harvester"));
        recipes.addDefault("PeatBog=" + ForestryBlock.planter.id + "," + 4 + "," + DefaultForestryValues.values.get("Peat Bog"));
        recipes.addDefault("Turbary=" + ForestryBlock.harvester.id + "," + 4 + "," + DefaultForestryValues.values.get("Turbary"));
        recipes.addDefault("CactusHarvester=" + ForestryBlock.harvester.id + "," + 5 + "," + DefaultForestryValues.values.get("Cactus Harvester"));
        recipes.addDefault("MushroomFarm=" + ForestryBlock.planter.id + "," + 5 + "," + DefaultForestryValues.values.get("Mushroom Farm"));
        recipes.addDefault("MushroomHarvester=" + ForestryBlock.harvester.id + "," + 6 + "," + DefaultForestryValues.values.get("Mushroom Harvester"));
        recipes.addDefault("SugarcaneHarvester=" + ForestryBlock.harvester.id + "," + 7 + "," + DefaultForestryValues.values.get("Sugar Cane Harvester"));
        recipes.addDefault("NetherFarm=" + ForestryBlock.planter.id + "," + 6 + "," + DefaultForestryValues.values.get("Nether Farm"));
        recipes.addDefault("NetherHarvester=" + ForestryBlock.harvester.id + "," + 8 + "," + DefaultForestryValues.values.get("Nether Harvester"));
        recipes.addDefault("# Factory Machines");
        recipes.addDefault("Fermenter=" + ForestryBlock.machine.id + "," + 0 + "," + DefaultForestryValues.values.get("Fermenter"));
        recipes.addDefault("Still=" + ForestryBlock.machine.id + "," + 1 + "," + DefaultForestryValues.values.get("Still"));
        recipes.addDefault("Bottler=" + ForestryBlock.machine.id + "," + 2 + "," + DefaultForestryValues.values.get("Bottler"));
        recipes.addDefault("Raintank=" + ForestryBlock.machine.id + "," + 3 + "," + DefaultForestryValues.values.get("Raintank"));
        recipes.addDefault("BiopowerGenerator=" + ForestryBlock.machine.id + "," + 4 + "," + DefaultForestryValues.values.get("BiopowerGenerator"));
        recipes.addDefault("Carpenter=" + ForestryBlock.machine.id + "," + 5 + "," + DefaultForestryValues.values.get("Carpenter"));
        recipes.addDefault("Moistener=" + ForestryBlock.machine.id + "," + 6 + "," + DefaultForestryValues.values.get("Moistener"));
        recipes.addDefault("Apiary=" + ForestryBlock.machine.id + "," + 7 + "," + DefaultForestryValues.values.get("Apiary"));
        recipes.addDefault("Centrifuge=" + ForestryBlock.machine.id + "," + 8 + "," + DefaultForestryValues.values.get("Centrifuge"));
        recipes.addDefault("Squeezer=" + ForestryBlock.machine.id + "," + 9 + "," + DefaultForestryValues.values.get("Squeezer"));
        recipes.addDefault("ThermionicFabricator=" + ForestryBlock.machine.id + "," + 11 + "," + DefaultForestryValues.values.get("Fabricator"));
        recipes.addDefault("Beealyzer=" + ItemInterface.getItem("beealyzer").id + "," + 0 + "," + DefaultForestryValues.values.get("Beealzyer"));
        recipes.addDefault("Analzyer=" + ForestryBlock.mill.id + "," + 4 + "," + DefaultForestryValues.values.get("Analyzer"));
        recipes.addDefault("# Since the Forester can be crafted from any farm, peat bog was used for the calculation.");
        recipes.addDefault("Forester=" + ForestryBlock.mill.id + "," + 0 + "," + DefaultForestryValues.values.get("Forester"));
        recipes.addDefault("Rainmaker=" + ForestryBlock.mill.id + "," + 1 + "," + DefaultForestryValues.values.get("Rainmaker"));
        recipes.addDefault("Treetap=" + ForestryBlock.mill.id + "," + 2 + "," + DefaultForestryValues.values.get("Treetap"));
        recipes.addDefault("Apichest=" + ForestryBlock.mill.id + "," + 3 + "," + DefaultForestryValues.values.get("Apichest"));
        recipes.addDefault("# Mail Stuff");
        recipes.addDefault("Mailbox=" + ForestryBlock.mill.id + "," + 5 + "," + DefaultForestryValues.values.get("Mailbox"));
        recipes.addDefault("TradeStation=" + ForestryBlock.mill.id + "," + 6 + "," + DefaultForestryValues.values.get("TradeStation"));
        recipes.addDefault("StampCollector=" + ForestryBlock.mill.id + "," + 7 + "," + DefaultForestryValues.values.get("StampCollector"));
        recipes.addDefault("# Forestry Soil");
        recipes.addDefault("Humus=" + ForestryBlock.soil.id + "," + 0 + "," + DefaultForestryValues.values.get("Humus"));
        recipes.addDefault("Bog=" + ForestryBlock.soil.id + "," + 1 + "," + DefaultForestryValues.values.get("Bog"));
        recipes.writeConfig();
        recipes.readFile();
    }

    protected void addEMC(int var1, int var2, int var3)
    {
        try
        {
            try
            {
                Class var4 = Class.forName("ee.EEMaps");
                Field var5 = var4.getField("alchemicalValues");
                HashMap var6 = (HashMap)var5.get((Object)null);
                HashMap var7 = new HashMap();

                if (var6.get(Integer.valueOf(var1)) != null)
                {
                    var7 = (HashMap)var6.get(Integer.valueOf(var1));
                }

                var7.put(Integer.valueOf(var2), Integer.valueOf(var3));
                var6.put(Integer.valueOf(var1), var7);
                var5.set((Object)null, var6);
            }
            catch (Exception var11)
            {
                var11.printStackTrace();
            }
        }
        finally
        {
            ;
        }
    }

    protected void blacklistItem(Item var1)
    {
        try
        {
            Class var2 = Class.forName("ee.EEMaps");
            Field var3 = var2.getField("alchemicalValues");
            var3.setAccessible(true);
            HashMap var4 = (HashMap)var3.get((Object)null);
            HashMap var5 = new HashMap();
            var5.put(Integer.valueOf(0), Integer.valueOf(0));
            var4.remove(Integer.valueOf(var1.id));
            var4.put(Integer.valueOf(var1.id), var5);
            var3.set((Object)null, var4);
        }
        catch (Exception var9)
        {
            var9.printStackTrace();
        }
        finally
        {
            core.print("Removed EMC Value for " + var1.getName());
        }
    }

    protected void blacklistItem(Block var1)
    {
        try
        {
            Class var2 = Class.forName("ee.EEMaps");
            Field var3 = var2.getField("alchemicalValues");
            var3.setAccessible(true);
            HashMap var4 = (HashMap)var3.get((Object)null);
            HashMap var5 = new HashMap();
            var5.put(Integer.valueOf(0), Integer.valueOf(0));
            var4.remove(Integer.valueOf(var1.id));
            var4.put(Integer.valueOf(var1.id), var5);
            var3.set((Object)null, var4);
        }
        catch (Exception var9)
        {
            var9.printStackTrace();
        }
        finally
        {
            core.print("Removed EMC Value for " + var1.q());
        }
    }

    protected void killEEItem(String var1)
    {
        try
        {
            Class var2 = Class.forName("ee.EEItem");
            Field var3 = var2.getField(var1);
            var3.setAccessible(true);
            Item var4 = (Item)var3.get((Object)null);
            int var5 = var4.id;
            Item.byId[var5] = null;
        }
        catch (Exception var9)
        {
            var9.printStackTrace();
        }
        finally
        {
            core.print("Killed EE Item " + var1);
        }
    }

    protected void recipes() {}
}
