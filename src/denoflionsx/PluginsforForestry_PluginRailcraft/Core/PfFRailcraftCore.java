package denoflionsx.PluginsforForestry_PluginRailcraft.Core;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.ForestryIntegration.BackpackData;
import denoflionsx.LiquidRoundup.ForestryIntegration.PluginLiquidRoundup;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Interfaces.IPfFCore;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericBiogasFuel.EngineFuel;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidImportBiogasFuel;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.BackpackUtils;
import denoflionsx.PluginsforForestry_PluginRailcraft.Backpack.BackpackRailcraft;
import denoflionsx.PluginsforForestry_PluginRailcraft.Config.RailcraftTuning;
import denoflionsx.PluginsforForestry_PluginRailcraft.Items.ItemSugarCharcoal;
import denoflionsx.PluginsforForestry_PluginRailcraft.Items.Recipes.SugarCharcoalCraftingChain;
import denoflionsx.PluginsforForestry_PluginRailcraft.Liquids.LiquidCactusSyrup;
import denoflionsx.PluginsforForestry_PluginRailcraft.Liquids.LiquidSugarSyrup;
import denoflionsx.PluginsforForestry_PluginRailcraft.Liquids.LiquidSulfuricAcid;
import denoflionsx.denLib.denLib;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.oredict.OreDictionary;
import railcraft.common.api.core.items.ItemRegistry;

public class PfFRailcraftCore implements IPfFCore {

    public String spritesheet;
    public static IPfFLiquid creosote;
    public LiquidDictionary.LiquidRegisterEvent event;
    public static ItemSugarCharcoal sc;
    public static IPfFLiquid scs;
    public static IPfFLiquid acid;
    public static IPfFLiquid ccs;
    private int eventID;
    public static ArrayList<ItemStack> valid = new ArrayList();

    public PfFRailcraftCore() {
        reg();
    }

    public final void reg() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @ForgeSubscribe
    public void registerCreosote(LiquidDictionary.LiquidRegisterEvent event) {
        if (event.Name.equals("Creosote Oil")) {
            this.event = event;
        }
    }

    @Override
    public void preloadTextures() {
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
        spritesheet = PfF.Proxy.preloadTextures("/denoflionsx/PluginsforForestry_PluginRailcraft/gfx/plugin_railcraft.png");
    }

    @Override
    public void setupBlocks() {
    }

    @PfFSubscribe
    public void onEvent(PfFEvent event) {
        if (event.getMsg().equals("config ready!")) {
            RailcraftTuning.tuning_enabled = true;
        } else if (event.getOrigin().equals("Fuel")) {
            if (event.getMsg().equals("Liquid Peat")) {
                EngineFuel f = (EngineFuel) event.getObj();
                APIWrappers.railcraft.boiler.addBoilerFuelNoConversion(f);
            } else {
                EngineFuel f = (EngineFuel) event.getObj();
                APIWrappers.railcraft.boiler.addBoilerFuel(f);
            }
        }
    }

    @Override
    public void setupConfig() {
    }

    @Override
    public void setupItems() {
        if (RailcraftTuning.Enables.Backpack_Enabled) {
            BackpackRailcraft backpack = new BackpackRailcraft(valid, "Railcraft Backpack", new Color(75, 75, 75));
            BackpackData data = new BackpackData(new int[]{RailcraftTuning.Items.BackpackT1_ItemID, RailcraftTuning.Items.BackpackT2_ItemID}, backpack, new ItemStack(Block.railDetector));
            PluginLiquidRoundup.backpacks.add(data);
        }
        if (RailcraftTuning.Enables.sugarcharcoal_enabled) {
            sc = new ItemSugarCharcoal();
            scs = new LiquidSugarSyrup().createLiquid();
            acid = new LiquidSulfuricAcid().createLiquid();
            if (RailcraftTuning.Enables.cactuscharcoal_enabled) {
                ccs = new LiquidCactusSyrup().createLiquid();
            }
        }
    }

    @Override
    public void lateCode() {
        if (RailcraftTuning.Enables.sugarcharcoal_enabled) {
            ArrayList<ItemStack> sulfur = OreDictionary.getOres("dustSulfur");
            for (ItemStack i : sulfur) {
                PfFManagers.Squeeze.registerSqueezable("Sulfur", i, RailcraftTuning.Tuning.SulfuricAcid_AmountPerSqueeze);
            }
            SugarCharcoalCraftingChain.acid = LiquidDictionary.getLiquid("Sulfuric Acid", 10000);
            SugarCharcoalCraftingChain.createCraftingChain(sc, LiquidDictionary.getLiquid("Sugar Syrup", 1000));
            if (RailcraftTuning.Enables.cactuscharcoal_enabled) {
                SugarCharcoalCraftingChain.createCraftingChainB(sc, LiquidDictionary.getLiquid("Cactus Syrup", 1000));
            }
            PfFManagers.Squeeze.registerSqueezeLiquid(scs);
            PfFManagers.Squeeze.registerSqueezeLiquid(acid);
            PfFManagers.Squeeze.registerSqueezeLiquid(ccs);
        }
        if (RailcraftTuning.Enables.creosote_biogas) {
            creosote = new LiquidImportBiogasFuel(event, 4, 14000).createLiquid();
        }
        for (Object o : denLib.dumpMapValues(ItemRegistry.getItems())) {
            valid.add((ItemStack) o);
        }
        valid.add(new ItemStack(Block.rail));
        valid.add(new ItemStack(Block.railDetector));
        valid.add(new ItemStack(Block.railPowered));
        valid.add(new ItemStack(Item.minecartCrate));
        valid.add(new ItemStack(Item.minecartEmpty));
        valid.add(new ItemStack(Item.minecartPowered));
        PfF.Proxy.saveList(valid, "railcraft");
        PfF.Proxy.loadList(BackpackUtils.config, "railcraft", valid);
    }
}
