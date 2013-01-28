package denoflionsx.LiquidRoundup.ForestryIntegration;

import cpw.mods.fml.common.network.IGuiHandler;
import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Config.LRTuning;
import forestry.api.core.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.command.ICommand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class PluginLiquidRoundup implements IPlugin {

    public static ArrayList<BackpackData> backpacks = new ArrayList();
    public static ArrayList<ItemStack> valid = new ArrayList();

    @Override
    public void doInit() {
    }

    public static void create() {
        LRTuning.tuning_enabled = true;

        for (LiquidContainerData d : LiquidContainerRegistry.getRegisteredLiquidContainerData()) {
            valid.add(d.filled);
        }
        BackpackLiquids backpack = new BackpackLiquids(valid, "Liquid Container Backpack", new Color(244, 150, 219));
        BackpackData LiquidBackpack = new BackpackData(new int[]{LRTuning.Items.LiquidBackpackT1_ItemID, LRTuning.Items.LiquidBackpackT2_ItemID}, backpack, new ItemStack(Item.bucketEmpty));
        if (LRTuning.Enables.LiquidBackpacks_Enabled) {
            backpacks.add(LiquidBackpack);
        }
    }

    public static void createBackpacks() {
        for (BackpackData b : backpacks) {
            APIWrappers.forestry.backpacks.registerBackpackTiers(b);
        }
    }

    @Override
    public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
    }

    @Override
    public ICommand[] getConsoleCommands() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Liquid Roundup!";
    }

    @Override
    public IOreDictionaryHandler getDictionaryHandler() {
        return null;
    }

    @Override
    public IGuiHandler getGuiHandler() {
        return null;
    }

    @Override
    public IPacketHandler getPacketHandler() {
        return null;
    }

    @Override
    public IPickupHandler getPickupHandler() {
        return null;
    }

    @Override
    public IResupplyHandler getResupplyHandler() {
        return null;
    }

    @Override
    public ISaveEventHandler getSaveEventHandler() {
        return null;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void postInit() {
    }

    @Override
    public void preInit() {
    }
}
