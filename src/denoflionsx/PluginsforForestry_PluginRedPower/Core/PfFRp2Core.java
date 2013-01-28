package denoflionsx.PluginsforForestry_PluginRedPower.Core;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Core.OmniPlantList;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.ForestryLiquids;
import denoflionsx.PluginsforForestry_PluginRedPower.Config.RedpowerTuning;
import denoflionsx.PluginsforForestry_PluginRedPower.PfFRp2;
import denoflionsx.denLib.denLib;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.liquids.LiquidStack;

public class PfFRp2Core implements IPfFCore {
    
    private int eventID;
    public static ArrayList<ItemStack> valid = new ArrayList();
    
    @Override
    public void preloadTextures() {
        MinecraftForge.EVENT_BUS.register(this);
        PfFManagers.Events.SystemEvents.registerListener(this);
    }
    
    @Override
    public void setupBlocks() {
    }
    
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            this.setupConfig();
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }
    
    @Override
    public void setupConfig() {
        RedpowerTuning.tuning_enabled = true;
    }
    
    @Override
    public void setupItems() {
//        if (RedpowerTuning.Enables.Backpack_Enabled){
//            BackpackRedpower backpack = new BackpackRedpower(valid, "Redpower Backpack", new Color(173,18,56));
//            BackpackData data = new BackpackData(new int[]{RedpowerTuning.Items.BackpackT1_ItemID,RedpowerTuning.Items.BackpackT2_ItemID},backpack,new ItemStack(Item.redstone));
//            PluginLiquidRoundup.backpacks.add(data);
//        }
    }
    
    @Override
    public void lateCode() {
        Block blockPlants = denLib.ReflectionHelper.getBlock("com.eloraam.redpower.RedPowerWorld", "blockPlants");
        APIWrappers.forestry.flowers.registerFlower(new ItemStack(blockPlants));
        Item seeds = denLib.ReflectionHelper.getItem("com.eloraam.redpower.RedPowerWorld", "itemSeeds");
        LiquidStack oil = StackUtils.getNewStack(ForestryLiquids.SEEDOIL.getLiquidStack(), 20);
        APIWrappers.TE.transposer.addExtractionRecipe(150, new ItemStack(seeds), null, oil, false);
        APIWrappers.forestry.squeezer.addRecipe(5, new ItemStack[]{new ItemStack(seeds)}, oil);
        ItemStack string = new ItemStack(Item.silk, 3, 0);
        if (RedpowerTuning.Enables.Flax_Omniplant) {
            OmniPlantList.OmniplantExternalPlants.addToWhiteList(new ItemStack(seeds), string);
        }
        PfF.Proxy.print("Redpower integration enabled.");
    }
    public static boolean WorldLoaded = false;
    
    @ForgeSubscribe
    public void onWorldLoaded(WorldEvent.Load event) {
        if (!WorldLoaded) {
            if (PfFRp2.load) {
                this.lateCode();
                try {
                    MinecraftForge.EVENT_BUS.unregister(this);
                } catch (Exception ex) {
                }
//            String path = "com.eloraam.redpower.core.CreativeExtraTabs";
//            Object o = denLib.ReflectionHelper.getObjectFromFieldNull(denLib.ReflectionHelper.getField("tabWires", denLib.ReflectionHelper.getClass(path)));
//            CreativeTabs wire = (CreativeTabs) o;
//            ArrayList<ItemStack> wires = CreativeUtils.getAllInTab(wire);
//            valid.addAll(wires);
            }
            WorldLoaded = true;
        }
    }
}
