package net.minecraft.src.denoflionsx.plugins.Forestry;

import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.items.multiItem;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.BC2.addLiquidBC2;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.BC3.addLiquidBC3;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.denoflionsx.API.API;

public class LiquidContainerSystem {

    // This is to streamline liquid container creation.
    private static int bucket = 1000;

    public static void create(multiItem m) {
        Iterator i = m.metaMap.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            String name = pairs.getKey().toString();
            Integer dmg = Integer.valueOf(pairs.getValue().toString());
            ItemStack filled = new ItemStack(m, 1, dmg);
            ItemStack empty;
            if (name.contains("Capsule") && !name.contains("_Red")) {
                empty = ItemInterface.getItem("waxCapsule");
                genericCap(m, filled, empty);
            } else if (name.contains("Capsule_Red")) {
                empty = ItemInterface.getItem("refractoryEmpty");
                genericCap(m, filled, empty);
            } else if (name.contains("Can")) {
                empty = ItemInterface.getItem("canEmpty");
                genericCap(m, filled, empty);
            } else if (name.contains("Bucket")) {
                bucket(m, filled);
            } else if (name.contains("Bottle")) {
                bottle(m, filled);
            } else if (name.contains("Barrel")){
                empty = API.getItem("barrel");
                barrel(m, empty,filled);
            }
        }
    }

    public static void createWithOverride(multiItem m, int liquid, ItemStack filledBottle, boolean noBucket) {

        Iterator i = m.metaMap.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pairs = (Map.Entry) i.next();
            String name = pairs.getKey().toString();
            Integer dmg = Integer.valueOf(pairs.getValue().toString());
            ItemStack filled = new ItemStack(m, 1, dmg);
            ItemStack empty;
            if (name.contains("Capsule") && !name.contains("_Red")) {
                empty = ItemInterface.getItem("waxCapsule");
                genericCapWithOverride(m, filled, empty, liquid);
            } else if (name.contains("Capsule_Red")) {
                empty = ItemInterface.getItem("refractoryEmpty");
                genericCapWithOverride(m, filled, empty, liquid);
            } else if (name.contains("Can")) {
                empty = ItemInterface.getItem("canEmpty");
                genericCapWithOverride(m, filled, empty, liquid);
            } else if (name.contains("Bucket")) {
                if (!noBucket) {
                    bucket(m, filled);
                }
            } else if (name.contains("Bottle")) {
                bottleOverride(filledBottle, liquid);
            } else if (name.contains("Barrel")){
                empty = API.getItem(denLib.toLowerCaseNoSpaces(name));
                barrelOverride(empty,filled,liquid);
            }
        }
    }

    public static void genericCap(multiItem m, ItemStack filled, ItemStack empty) {
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(m.shiftedIndex, bucket), filled, empty, false));
        unpack(filled, m.shiftedIndex, bucket);
        pack(empty, filled, m.shiftedIndex, bucket);
    }

    public static void genericCapWithOverride(multiItem m, ItemStack filled, ItemStack empty, int liquid) {
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(liquid, bucket), filled, empty, false));
        unpack(filled, liquid, bucket);
        pack(empty, filled, liquid, bucket);
    }

    public static void bucket(multiItem m, ItemStack filled) {
        ItemStack empty = new ItemStack(Item.bucketEmpty);
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(m.shiftedIndex, bucket), filled, empty, true));
        unpack(filled, m.shiftedIndex, bucket);
        pack(empty, filled, m.shiftedIndex, bucket);
        if (denLib.detect("mod_BuildCraftSilicon")) {
            addLiquidBC3.add(m.shiftedIndex, filled);
        } else {
            if (denLib.detect("mod_BuildCraftCore")) {
                if (denLib.convertToBoolean(core.config.getOption("EnableBC2TankIntegration"))) {
                    addLiquidBC2.add(m.shiftedIndex, filled.getItem());
                }

            }
        }
    }

    public static void bottle(multiItem m, ItemStack filled) {
        ItemStack empty = new ItemStack(Item.glassBottle);
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(m.shiftedIndex, EnumContainers.Containers.BOTTLE.getQuantity()), filled, empty, false));
        unpack(filled, m.shiftedIndex, EnumContainers.Containers.BOTTLE.getQuantity());
        pack(empty, filled, m.shiftedIndex, EnumContainers.Containers.BOTTLE.getQuantity());
    }
    
    public static void barrel(multiItem m, ItemStack empty, ItemStack filled){
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(m.shiftedIndex,EnumContainers.Containers.BARREL.getQuantity()),filled,empty,false));
        unpack(filled,m.shiftedIndex,EnumContainers.Containers.BARREL.getQuantity());
        pack(empty,filled,m.shiftedIndex,EnumContainers.Containers.BARREL.getQuantity());
    }
    
    public static void barrelOverride(ItemStack empty, ItemStack filled, int liquid){
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(liquid,EnumContainers.Containers.BARREL.getQuantity()),filled,empty,false));
        unpack(filled,liquid,EnumContainers.Containers.BARREL.getQuantity());
        pack(empty,filled,liquid,EnumContainers.Containers.BARREL.getQuantity());
    }

    public static void bottleOverride(ItemStack filled, int liquid) {
        ItemStack empty = new ItemStack(Item.glassBottle);
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(liquid, EnumContainers.Containers.BOTTLE.getQuantity()), filled, empty, false));
        unpack(filled, liquid, EnumContainers.Containers.BOTTLE.getQuantity());
        pack(empty, filled, liquid, EnumContainers.Containers.BOTTLE.getQuantity());
    }

    public static void unpack(ItemStack filled, int liquid, int amount) {
        RecipeManagers.squeezerManager.addRecipe(5, new ItemStack[]{filled}, new LiquidStack(liquid, amount));
    }

    public static void pack(ItemStack empty, ItemStack filled, int liquid, int amount) {
        RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(liquid, amount), empty, filled);
    }
    
    public static void unpackBucket(ItemStack filled, int liquid, int amount){
        RecipeManagers.squeezerManager.addRecipe(5, new ItemStack[]{filled}, new LiquidStack(liquid, amount),new ItemStack(Item.bucketEmpty),100);
    }

    public static void registerMilkBucket(int liquid) {
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(liquid, bucket), new ItemStack(Item.bucketMilk), new ItemStack(Item.bucketEmpty), true));
        pack(new ItemStack(Item.bucketEmpty), new ItemStack(Item.bucketMilk), liquid, bucket);
        unpackBucket(new ItemStack(Item.bucketMilk), liquid, bucket);
        addLiquidBC2.add(liquid, Item.bucketMilk);
    }
}
