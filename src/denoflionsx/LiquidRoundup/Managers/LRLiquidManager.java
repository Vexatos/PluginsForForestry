package denoflionsx.LiquidRoundup.Managers;

import denoflionsx.LiquidRoundup.API.Interfaces.ILRContainerItem;
import denoflionsx.LiquidRoundup.API.Interfaces.ILRLiquidManager;
import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.LiquidRoundup.API.Objects.LRContainer;
import denoflionsx.LiquidRoundup.API.Objects.LRLiquid;
import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Items.LRIDs;
import denoflionsx.LiquidRoundup.Items.LiquidBase;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.denLib;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import net.minecraftforge.liquids.LiquidStack;

public class LRLiquidManager implements ILRLiquidManager {

    // NOTE: This system does NOT guarantee a proper sprite coloring.
    // It tries it's best, but some methods of making sprites can beat it.
    // In that case we can override the color manually in a plugin.
    private static int currentId = -1;
    private static final int[] limits = new int[256];
    private static final ArrayList<LiquidStack> liquids = new ArrayList(1024);
    public static final ArrayList<LRLiquid> custom = new ArrayList();
    public static final HashMap<String, ItemStack> containers = new HashMap();
    public static final String cat = "Internal_Liquid_IDs".toLowerCase();
    private static final HashMap<Integer, String> spritesheets = new HashMap();
    private static int spriteSheetRegistry = -1;
    private static final HashMap<Integer, LiquidBase> liquidBases = new HashMap();

    static {
        int a = 0;
        for (int i = 0; i < 256; i++) {
            a += 256;
            limits[i] = a;
        }
        if (spritesheets.isEmpty()) {
            //----------------------------------------------
            // Create first sprite sheet and load it up.
            // All sprite sheets handled for LR are dynamic.
            //----------------------------------------------
            spriteSheetStuff();
        }
        ConfigCategory c = LiquidRoundup.Core.config.getCategory(cat);
        if (c != null) {
            Map<String, Property> map = c.getValues();
            ArrayList<Integer> list = new ArrayList();
            for (Property p : map.values()) {
                int id = p.getInt();
                list.add(id);
                // LiquidRoundup.Proxy.print("Restored ID " + id);
            }
            Collections.sort(list);
            if (!list.isEmpty()) {
                currentId = list.get(list.size() - 1) - 1;
            }
            for (int i = 0; i < 1024; i++) {
                liquids.add(null);
            }
        }
    }

    @Override
    public ItemStack getContainer(String name) {
        if (containers.get(name) != null) {
            return containers.get(name);
        } else {
            LiquidRoundup.Proxy.print("No container " + name + " found.");
            LiquidRoundup.Proxy.print("Printing all valid name keys...");
            ArrayList<String> keys = denLib.dumpMapKeys(containers);
            for (String k : keys) {
                LiquidRoundup.Proxy.print(k);
            }
            LiquidRoundup.Proxy.print("Scanning for match...");
            for (String k : keys) {
                if (k.equals(name)) {
                    LiquidRoundup.Proxy.print("Match found.");
                    return containers.get(k);
                }
            }
            return new ItemStack(Item.bucketWater);
        }
    }

    @Override
    public void registerContainer(String name, ItemStack i) {
        containers.put(name, i);
    }

    private static void spriteSheetStuff() {
        spriteSheetRegistry++;
        int id = spriteSheetRegistry;
        LiquidRoundup.Proxy.print("Setting up liquid spritesheet #" + id);
        String sheet = LiquidRoundup.Proxy.createNewSpriteSheet("liquidsheet_" + id + ".png");
        spritesheets.put(id, sheet);
        int itemID = LiquidRoundup.Core.config.getItem("Liquid" + id + "_ItemID", LRIDs.LiquidIDs[id]).getInt();
        LiquidBase liquid = new LiquidBase(itemID);
        liquid.setItemName("Liquid" + id);
        liquid.setTextureFile(sheet);
        liquidBases.put(id, liquid);
    }

    public static boolean doesHaveDuplicate(LiquidStack liquid) {
        for (LiquidStack l : liquids) {
            if (l != null) {
                if (l.isLiquidEqual(liquid)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean doesNeedNewSheet() {
        for (int i : limits) {
            if (i == currentId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getCurrentSpritesheetPath() {
        return getSpritesheetPath(getCurrentInt());
    }

    @Override
    public String getSpritesheetPath(int id) {
        return spritesheets.get(id);
    }

    @Override
    public int getCurrentInt() {
        return spriteSheetRegistry;
    }

    @Override
    public String registerLiquid(String name, int r, int g, int b) {
        String path = LiquidRoundup.Proxy.createGenericLiquidTexture(name, r, g, b);
        return path;
    }

    @Override
    public LiquidStack registerLiquid(String name, String texture[]) {
        if (doesNeedNewSheet()) {
            spriteSheetStuff();
        }
        if (LiquidDictionary.getLiquid(name, 1000) != null) {
            LiquidRoundup.Proxy.print("Found pre-existing liquid: " + name);
            return LiquidDictionary.getLiquid(name, 1000);
        }
        String hash = denLib.Hash(name);
        Property q = LiquidRoundup.Core.config.get(cat, denLib.toLowerCaseNoSpaces(hash) + "_InternalID", getNewInternalID());
        q.comment = name;
        int internal = q.getInt();
        // Attempting to shift the id range back down.
        // Anything above 255 will obviously make the sprite stuff explode.
        int internalShift = internal;
        while (internalShift > 255) {
            internalShift--;
        }
        Color color = LiquidRoundup.Proxy.figureOutSpriteColor(texture[0]);
        LiquidRoundup.Proxy.registerLiquidTexture(texture[0], internalShift);
        LiquidStack l = liquidBases.get(this.getCurrentInt()).addNewLiquid(name, internalShift);
        l = LiquidDictionary.getOrCreateLiquid(name, l);
        addLiquid(l, internal);
        custom.add(new LRLiquid(l, name, texture, internalShift, color));
        Color sparkles = LiquidRoundup.Proxy.figureOutSpriteColor(texture[1]);
        LiquidRoundup.Proxy.addTextureFX(color.getRed(), sparkles.getRed(), color.getGreen(), sparkles.getGreen(), color.getBlue(), sparkles.getBlue(), internalShift, spritesheets.get(this.getCurrentInt()));
        LiquidRoundup.Proxy.print("Created liquid " + name + ".");
        return l;
    }

    @Override
    public void registerLiquid(LiquidRegisterEvent event) {
        if (doesHaveDuplicate(event.Liquid)) {
            return;
        }
        if (doesNeedNewSheet()) {
            spriteSheetStuff();
        }
        boolean banned = BANLIST.addBan(event.Name);
        if (banned) {
            return;
        }
        String hash = denLib.Hash("" + event.Liquid.itemID + " | " + event.Liquid.itemMeta);
        Property q = LiquidRoundup.Core.config.get(cat, denLib.toLowerCaseNoSpaces(hash) + "_InternalID", getNewInternalID());
        q.comment = event.Name;
        int internal = q.getInt();
        //-------------------------------------------------------------
        // Attempt to figure out overlay color.
        //-------------------------------------------------------------
        String path = LiquidRoundup.Proxy.ExtractItemStackSprite(event.Liquid.asItemStack());
        Color color = LiquidRoundup.Proxy.figureOutSpriteColor(path);
        // Sprites that don't extract correctly return black.
        // Try a bucket in that case.
        if (color.getRGB() == new Color(0, 0, 0).getRGB()) {
            ItemStack t = LiquidContainerRegistry.fillLiquidContainer(event.Liquid, LiquidContainerRegistry.EMPTY_BUCKET);
            if (t != null) {
                path = LiquidRoundup.Proxy.ExtractItemStackSprite(t);
                color = LiquidRoundup.Proxy.figureOutSpriteColor(path);
            }
        }
        //-------------------------------------------------------------
        for (LRContainer r : LRManagers.Containers.getContainerList()) {
            if (r != null) {
                if (!r.isCustomOnly()) {
                    String name = "";
                    name = event.Name;
                    if (name.contains("tile.")) {
                        name = name.replace("tile.", "");
                        String a = name.substring(0, 1);
                        a = a.toUpperCase();
                        name = name.substring(1);
                        name = a + name;
                    }
                    if (r.isBlackListLava() && name.equals("Lava")) {
                        continue;
                    }
                    ItemStack a = LiquidContainerRegistry.fillLiquidContainer(event.Liquid, r.getEmpty());
                    if (a == null) {
                        LiquidStack copy = event.Liquid.copy();
                        copy.amount = r.getCapacity();
                        ILRContainerItem i = (ILRContainerItem) r.getStack().getItem();
                        ItemStack filled = i.add(name + " " + r.getName(), internal, color);
                        LiquidContainerData d = new LiquidContainerData(copy, filled, r.getEmpty());
                        LiquidContainerRegistry.registerLiquid(d);
                        //LiquidRoundup.Proxy.print("Registered " + r.getName() + " for " + name);
                        this.registerContainer(name + " " + r.getName(), filled);
                        APIWrappers.forestry.squeezer.addRecipe(5, new ItemStack[]{filled}, copy);
                        APIWrappers.TE.transposer.addFillRecipe(d.stillLiquid.amount, d.container, d.filled, d.stillLiquid, true);
                    }
                }
            }
        }
        LiquidRoundup.Core.config.save();
        addLiquid(event.Liquid, internal);
    }

    private void addLiquid(LiquidStack liquid, int id) {
        liquids.add(id, liquid);
    }

    @Override
    public int getNewInternalID() {
        currentId++;
        return currentId;
    }

    public static class BANLIST {

        public static boolean addBan(String tag) {
            boolean b = false;
            if (tag.equals("Steam") || tag.equals("item.itemCoolant")) {
                b = true;
            }
            return LiquidRoundup.Core.config.get("banlist.liquids", denLib.toLowerCaseNoSpaces(tag), b).getBoolean(b);
        }
    }
}
