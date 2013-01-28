package denoflionsx.PluginsforForestry_PluginThaumcraft.Core;

import denoflionsx.LiquidRoundup.ForestryIntegration.BackpackData;
import denoflionsx.LiquidRoundup.ForestryIntegration.PluginLiquidRoundup;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.BackpackUtils;
import denoflionsx.PluginsforForestry_PluginThaumcraft.Backpack.BackpackThaumcraft;
import denoflionsx.PluginsforForestry_PluginThaumcraft.Config.ThaumcraftTuning;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PfFThaumCore implements IPfFCore {

    public String spritesheet;
//    public static ItemTestAuraDrain test;
    public static IPfFLiquid vis;
//    public static BlockVisCollector vc;
    private int eventID;
    public static ArrayList<ItemStack> valid = new ArrayList();

    @Override
    public void preloadTextures() {
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
        spritesheet = PfF.Proxy.preloadTextures("/denoflionsx/PluginsforForestry_PluginThaumcraft/gfx/plugin_thaumcraft.png");
        //ResearchUtils.addResearchFile("/denoflionsx/PluginsforForestry_PluginThaumcraft/research.xml");
    }

    @Override
    public void setupBlocks() {
        if (ThaumcraftTuning.Enables.AuraCollector_Enabled) {
//            FMLWrapper.MODE.FML.registerTileEntity(TileEntityVisCollector.class, "PfFVis");
//            vc = new BlockVisCollector();
        }
    }

    @PfFSubscribe
    public void onEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            ThaumcraftTuning.tuning_enabled = true;
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }

    @Override
    public void setupConfig() {
    }

    @Override
    public void setupItems() {
        if (ThaumcraftTuning.Enables.AuraCollector_Enabled) {
//            test = new ItemTestAuraDrain();
//            vis = new LiquidVis().createLiquid();
        }
        if (ThaumcraftTuning.Enables.Backpacks_Enabled) {
            BackpackThaumcraft backpack = new BackpackThaumcraft(valid, "Thaumcraft Backpack", new Color(153, 51, 191));
            BackpackData data = new BackpackData(new int[]{ThaumcraftTuning.Items.BackpackT1_ItemID, ThaumcraftTuning.Items.BackpackT2_ItemID}, backpack, new ItemStack(Item.blazeRod));
            PluginLiquidRoundup.backpacks.add(data);
        }
    }

    @Override
    public void lateCode() {
        //ResearchItemWrapper abc = new ResearchItemWrapper("PFFTEST", new ObjectTagsWrapper(),10,1, PfFManagers.Items.getItemByTag("woodenbucket")).registerResearchItem();
        PfF.Proxy.getAllReferences("thaumcraft.common.Config", valid);
        PfF.Proxy.saveList(valid, "thaumcraft");
        PfF.Proxy.loadList(BackpackUtils.config, "thaumcraft", valid);
    }
}
