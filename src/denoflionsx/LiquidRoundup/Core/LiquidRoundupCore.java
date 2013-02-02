package denoflionsx.LiquidRoundup.Core;

import denoflionsx.LiquidRoundup.API.Interfaces.ILRContainerItem;
import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.LiquidRoundup.API.Objects.LRContainer;
import denoflionsx.LiquidRoundup.API.Objects.LRLiquid;
import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.CreativeTab.RoundupTab;
import denoflionsx.LiquidRoundup.ForestryIntegration.PluginLiquidRoundup;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.LiquidRoundup.Managers.LRLiquidManager;
import denoflionsx.LiquidRoundup.UpdateHelper.UpdateHelper;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.denLib.Interfaces.IDenCore;
import denoflionsx.denLib.denLib;
import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidRoundupCore implements IDenCore {

    public File c;
    public Configuration config;
    public RoundupTab tab;
    public int bucketTextureID = 16;
    public int white = new Color(255, 255, 255).getRGB();
    private Property purge;
    private UpdateHelper liquidPurge;

    @Override
    public void preloadTextures() {
        config.addCustomCategoryComment(LRLiquidManager.cat, "DO NOT CHANGE THESE!");
    }

    @Override
    public void setupBlocks() {
    }

    @Override
    public void setupConfig() {
        c = new File(LiquidRoundup.Proxy.getConfigDir() + "LiquidRoundup.cfg");
        config = new Configuration(c);
        if (c.exists()) {
            config.load();
        }
        purge = config.get("EMERGENCY_USE_ONLY", "PurgeBustedTanks", false);
        purge.comment = "I AM NOT RESPONSIBLE IF YOU DESTROY YOUR WORLD WIH THIS!";
    }

    @Override
    public void setupItems() {
        tab = new RoundupTab();
        config.save();
    }
    private ArrayList<LiquidStack> hold = new ArrayList();

    public void lateCode() {
        // I had to delay this instead of doing each liquid as it came in from Forge.
        // Otherwise my system kept skipping a liquid or two for some reason...
        for (LiquidRegisterEvent event : LiquidRoundup.events) {
            LRManagers.Liquids.registerLiquid(event);
        }
        // Some liquids don't seem to follow the rules... looking at you BC.
        // Time to scan for rogue ones.
        // The manager will check for duplicates at add time.
        Class clazz = denLib.ReflectionHelper.getClass("net.minecraftforge.liquids.LiquidContainerRegistry");
        Field f = denLib.ReflectionHelper.getField("liquids", clazz);
        Object o = denLib.ReflectionHelper.getObjectFromFieldNull(f);
        ArrayList<LiquidContainerData> liquid = (ArrayList<LiquidContainerData>) o;
        for (LiquidContainerData l : liquid) {
            // This check prevents water from having a duplicate entry.
            if (l.stillLiquid.itemID != Block.waterMoving.blockID) {
                hold.add(l.stillLiquid);
            }
        }
        // Gotta do the adding after the scan or you'll get a concurrent mod error.
        for (LiquidStack l : hold) {
            try {
                if (l != null) {
                    if (l.asItemStack() != null && l.asItemStack().getItemName() != null) {
                        LRManagers.Liquids.registerLiquid(new LiquidRegisterEvent(l.asItemStack().getItemName(), l));
                    }
                } else {
                    LiquidRoundup.Proxy.print("Null found in liquid dictionary. Ignoring.");
                }
            } catch (Exception ex) {
                LiquidRoundup.Proxy.print("Something went wrong when trying to assimilate a liquid!");
            }
        }
        for (LRLiquid l : LRLiquidManager.custom) {
            for (LRContainer r : LRManagers.Containers.getContainerList()) {
                if (r != null) {
                    boolean banned = LiquidRoundup.Core.config.get("banlist.PfFLiquids", denLib.toLowerCaseNoSpaces(l.getName()), false).getBoolean(false);
                    LiquidRoundup.Core.config.save();
                    if (banned) {
                        continue;
                    }
                    LiquidStack copy = StackUtils.getNewStack(l.getLiquid(), r.getCapacity());
                    ILRContainerItem i = (ILRContainerItem) r.getStack().getItem();
                    ItemStack con = i.add(l.getName() + " " + r.getName(), l.getInternal(), l.getColor());
                    LiquidContainerData d = new LiquidContainerData(copy, con, r.getEmpty());
                    LiquidContainerRegistry.registerLiquid(d);
                    LRManagers.Liquids.registerContainer(l.getName() + " " + r.getName(), r.getStack());
                    APIWrappers.forestry.squeezer.addRecipe(5, new ItemStack[]{con}, copy);
                    APIWrappers.TE.transposer.addFillRecipe(d.stillLiquid.amount, d.container, d.filled, d.stillLiquid, true);
                }
            }
        }
        LiquidRoundup.Proxy.registerFX();

        if (purge.getBoolean(false)) {
            liquidPurge = new UpdateHelper();
        }

        PluginLiquidRoundup.create();
        PluginLiquidRoundup.createBackpacks();
    }

    public void debugCode() {
    }
}
