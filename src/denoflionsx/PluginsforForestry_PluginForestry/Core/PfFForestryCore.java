package denoflionsx.PluginsforForestry_PluginForestry.Core;

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
import denoflionsx.PluginsforForestry.Utils.ForestryLiquids;
import denoflionsx.PluginsforForestry.Utils.ReflectUtils;
import denoflionsx.PluginsforForestry_PluginForestry.Backpack.BackpackForestry;
import denoflionsx.PluginsforForestry_PluginForestry.Config.ForestryTuning;
import denoflionsx.PluginsforForestry_PluginForestry.Item.ItemPeat;
import denoflionsx.PluginsforForestry_PluginForestry.Liquids.LiquidPeat;
import denoflionsx.denLib.FMLWrapper;
import forestry.api.core.ItemInterface;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class PfFForestryCore implements IPfFCore {

    private int eventID;
    public static IPfFLiquid peat;
    public static ArrayList<ItemStack> valid = new ArrayList();

    @Override
    public void preloadTextures() {
        MinecraftForge.EVENT_BUS.register(this);
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
    }

    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            ForestryTuning.tuning_enabled = true;
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }

    @Override
    public void setupBlocks() {
    }

    @Override
    public void setupConfig() {
    }

    @Override
    public void setupItems() {
        if (ForestryTuning.Enables.LiquidPeat_Enabled) {
            peat = new LiquidPeat().createLiquid();
        }
        if (ForestryTuning.Enables.Backpacks_Enabled) {
            BackpackForestry backpack = new BackpackForestry(valid, "Forestry Backpack", new Color(186, 242, 138));
            BackpackData data = new BackpackData(new int[]{ForestryTuning.Items.BackpackT1_ItemID, ForestryTuning.Items.BackpackT2_ItemID}, backpack, ItemInterface.getItem("gearBronze"));
            PluginLiquidRoundup.backpacks.add(data);
        }
    }

    @Override
    public void lateCode() {
        if (ForestryTuning.Enables.Biofuel_InBiogas) {
            APIWrappers.forestry.biogas.addSafeFuel(new LiquidStack(ForestryLiquids.BIOFUEL.getStack().itemID, 1000), ForestryTuning.Fuels.Biofuel_MJt, ForestryTuning.Fuels.Biofuel_BurnTime);
        }
        ArrayList<ItemStack> bars = OreDictionary.getOres("ingotBronze");
        FMLWrapper.MODE.FML.addShapelessRecipe(bars.get(0), new Object[]{ItemInterface.getItem("sturdyCasing")});
        ItemStack[] seeds = new ItemStack[]{new ItemStack(Item.seeds), new ItemStack(Item.melonSeeds), new ItemStack(Item.pumpkinSeeds)};
        LiquidStack oil = StackUtils.getNewStack(ForestryLiquids.SEEDOIL.getLiquidStack(), 20);
        for (ItemStack i : seeds) {
            APIWrappers.TE.transposer.addExtractionRecipe(150, i, null, oil, false);
        }
        if (peat != null) {
            PfFManagers.Squeeze.registerSqueezable(new ItemPeat());
            PfFManagers.Squeeze.registerSqueezeLiquid(peat);
        }
        PfF.Proxy.getAllReferences("forestry.core.config.ForestryItem", valid);
        PfF.Proxy.getAllReferences("forestry.core.config.ForestryBlock", valid);
        PfF.Proxy.saveList(valid, "forestry");
        PfF.Proxy.loadList(BackpackUtils.config, "forestry", valid);
    }
}
