package denoflionsx.PluginsforForestry_PluginIc2.Core;

import denoflionsx.LiquidRoundup.ForestryIntegration.BackpackData;
import denoflionsx.LiquidRoundup.ForestryIntegration.PluginLiquidRoundup;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.BackpackUtils;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;
import denoflionsx.PluginsforForestry_PluginIc2.Backpack.BackpackIc2;
import denoflionsx.PluginsforForestry_PluginIc2.Config.Ic2Tuning;
import denoflionsx.PluginsforForestry_PluginIc2.Items.ItemPortableReactor;
import denoflionsx.PluginsforForestry_PluginIc2.Items.ItemRadioactiveWaste;
import denoflionsx.PluginsforForestry_PluginIc2.Liquids.LiquidUranium;
import ic2.api.Items;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class PfFIc2Core implements IPfFCore {

    private int eventID;
    public String spritesheet;
    public static ItemPortableReactor r;
    public static ItemRadioactiveWaste goo;
    public static IPfFLiquid waste;
    public static ArrayList<ItemStack> valid = new ArrayList();

    @Override
    public void preloadTextures() {
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
        spritesheet = PfF.Proxy.preloadTextures(PfFConstants.Ic2spritesheet);
    }

    @Override
    public void setupBlocks() {
    }

    @PfFSubscribe
    public void onEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            this.setupConfig();
            PfFManagers.Events.SystemEvents.unregisterListener(eventID);
        }
    }

    @Override
    public void setupConfig() {
        Ic2Tuning.tuning_enabled = true;
    }

    @Override
    public void setupItems() {
        if (Ic2Tuning.Enables.PortableReactor_Enabled) {
            r = new ItemPortableReactor();
        }
        if (Ic2Tuning.Enables.RadioactiveWaste_Enabled){
            goo = new ItemRadioactiveWaste();
            waste = new LiquidUranium().createLiquid();
        }
        if (Ic2Tuning.Enables.Backpack_Enabled){
            BackpackIc2 backpack = new BackpackIc2(valid,"Industrialcraft Backpack", new Color(200,200,200));
            BackpackData data = new BackpackData(new int[]{Ic2Tuning.Items.BackpackT1_ItemID,Ic2Tuning.Items.BackpackT2_ItemID},backpack,Items.getItem("uraniumIngot"));
            PluginLiquidRoundup.backpacks.add(data);
        }
    }

    @Override
    public void lateCode() {
        if (r != null) {
            r.recipe();
        }
        if (goo != null && waste != null){
            goo.recipe();
            PfFManagers.Squeeze.registerSqueezable(goo);
            PfFManagers.Squeeze.registerSqueezeLiquid(waste);
        }
        PfF.Proxy.getAllReferences("ic2.core.Ic2Items", valid);
        PfF.Proxy.saveList(valid, "ic2");
        PfF.Proxy.loadList(BackpackUtils.config, "ic2", valid);
    }
}
