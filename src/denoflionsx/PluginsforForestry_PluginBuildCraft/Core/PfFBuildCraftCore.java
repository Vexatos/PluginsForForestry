package denoflionsx.PluginsforForestry_PluginBuildCraft.Core;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.ForestryIntegration.BackpackData;
import denoflionsx.LiquidRoundup.ForestryIntegration.PluginLiquidRoundup;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.BackpackUtils;
import denoflionsx.PluginsforForestry_PluginBuildCraft.Backpack.BackpackBuildcraft;
import denoflionsx.PluginsforForestry_PluginBuildCraft.Config.BuildCraftTuning;
import denoflionsx.PluginsforForestry_PluginBuildCraft.Liquids.LiquidHeavyWater;
import denoflionsx.PluginsforForestry_PluginBuildCraft.PfFBuildCraft;
import denoflionsx.denLib.denLib;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;

public class PfFBuildCraftCore implements IPfFCore {

    private int eventID;
    private LiquidStack fuel;
    private LiquidStack oil;
    private IPfFLiquid heavy;
    public static ArrayList<ItemStack> valid = new ArrayList();

    @Override
    public void preloadTextures() {
        MinecraftForge.EVENT_BUS.register(this);
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
    }

    @Override
    public void setupBlocks() {
    }

    @Override
    public void setupConfig() {
    }

    @Override
    public void setupItems() {
        if (BuildCraftTuning.Enables.HeavyWater_Enabled) {
            heavy = new LiquidHeavyWater().createLiquid();
        }
        if (BuildCraftTuning.Enables.Backpacks_Enabled) {
            BackpackBuildcraft backpack = new BackpackBuildcraft(valid, "BuildCraft Backpack", new Color(242, 212, 46));
            PluginLiquidRoundup.backpacks.add(new BackpackData(new int[]{BuildCraftTuning.Items.BackpackT1_ItemID, BuildCraftTuning.Items.BackpackT2_ItemID}, backpack, new ItemStack(denLib.ReflectionHelper.getItem("buildcraft.BuildCraftTransport", "pipeItemsStone"))));
        }
        if (BuildCraftTuning.Enables.Quarry_SpeedModifier){
            denLib.ReflectionHelper.setStaticInt("buildcraft.factory.TileQuarry", "MAX_ENERGY", BuildCraftTuning.Tuning.Quarry_EnergyBuffer);
        }
    }

    @PfFSubscribe
    public void onConfigEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            BuildCraftTuning.tuning_enabled = true;
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }

    @ForgeSubscribe
    public void onLiquidEvent(LiquidRegisterEvent event) {
        if (event.Name.toLowerCase().equals("fuel")) {
            fuel = event.Liquid;
        } else if (event.Name.toLowerCase().equals("oil")) {
            oil = event.Liquid;
        }
    }
    public static boolean WorldLoaded = false;

    @ForgeSubscribe
    public void onWorldEvent(WorldEvent.Load event) {
        if (!WorldLoaded) {
            if (PfFBuildCraft.load) {
                try {
                    if (fuel == null) {
                        // Assume old BC version. Reflect it.
                        Class c = denLib.ReflectionHelper.getClass("buildcraft.BuildCraftEnergy");
                        // Use this instead of the denLib method so the Exception can be handled gracefully.
                        Field f = c.getField("fuel");
                        Object o = denLib.ReflectionHelper.getObjectFromFieldNull(f);
                        Item fueli = (Item) o;
                        ItemStack fuelItem = new ItemStack(fueli);
                        fuel = new LiquidStack(fuelItem.itemID, 1000, fuelItem.getItemDamage());
                    }
                } catch (NoSuchFieldException ex) {
                    PfF.Proxy.print("Can't find BC fuel!");
                    return;
                }
                // Biogas it.
                APIWrappers.forestry.biogas.addSafeFuel(fuel, BuildCraftTuning.Fuels.Fuel_MJt, BuildCraftTuning.Fuels.Fuel_BurnTime);
                if (BuildCraftTuning.Enables.Fuel_CreateInStill) {
                    if (oil == null) {
                        oil = LiquidDictionary.getLiquid("Oil", 1000);
                        if (oil == null) {
                            try {
                                // Assume old BC version. Reflect it.
                                Class c = denLib.ReflectionHelper.getClass("buildcraft.BuildCraftEnergy");
                                // Use this instead of the denLib method so the Exception can be handled gracefully.
                                Field f = c.getField("oilStill");
                                Object o = denLib.ReflectionHelper.getObjectFromFieldNull(f);
                                Block fueli = (Block) o;
                                ItemStack oilStill = new ItemStack(fueli);
                                oil = new LiquidStack(oilStill.itemID, 1000);
                            } catch (Exception ex) {
                                return;
                            }
                        }
                    }
                    APIWrappers.forestry.still.registerRecipe(StackUtils.getNewStack(oil, 10), StackUtils.getNewStack(fuel, 10));
                }
                // We're done here. Unhook.
                try {
                    MinecraftForge.EVENT_BUS.unregister(this);
                } catch (Exception ex) {
                }
                PfF.Proxy.getAllReferences("buildcraft.BuildCraftTransport", valid);
                PfF.Proxy.getAllReferences("buildcraft.BuildCraftCore", valid);
                PfF.Proxy.getAllReferences("buildcraft.BuildCraftBuilders", valid);
                PfF.Proxy.getAllReferences("buildcraft.BuildCraftEnergy", valid);
                PfF.Proxy.getAllReferences("buildcraft.BuildCraftFactory", valid);
                PfF.Proxy.getAllReferences("buildcraft.BuildCraftSilicon", valid);
                valid.add(new ItemStack(Block.glass));
                valid.add(new ItemStack(Block.brick));
                PfF.Proxy.saveList(valid, "buildcraft");
                PfF.Proxy.loadList(BackpackUtils.config, "buildcraft", valid);
                WorldLoaded = true;
            }
        }
    }

    @Override
    public void lateCode() {
    }
}
