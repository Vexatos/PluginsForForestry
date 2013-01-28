package denoflionsx.PluginsforForestry.Core;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;

public class OmniPlantList {

    public static final String cat = "core.Omniplant_WhiteList".toLowerCase();

    public static void registerList() {
        addToWhiteList(Item.book);
        addToWhiteList(Item.fishRaw);
        addToWhiteList(Item.silk);
        PfF.Core.config.save();
        ConfigCategory c = PfF.Core.config.getCategory(cat);
        for (Property p : c.getValues().values()) {
            String a = p.value;
            String[] parse = a.split(",");
            int id = Integer.valueOf(parse[0]);
            int amt = Integer.valueOf(parse[1]);
            int meta = Integer.valueOf(parse[2]);
            ItemStack product = new ItemStack(id, amt, meta);
            PfFManagers.OmniPlant.registerPlant(product);
        }
    }

    public static void addToWhiteList(ItemStack item) {
        String l = item.getItemName() + "_OmniPlant";
        String l2 = item.itemID + "," + item.stackSize + "," + item.getItemDamage();
        PfF.Core.config.get(cat, l, l2);
        PfF.Core.config.addCustomCategoryComment(cat, "Syntax: ID, amount, meta");
    }

    public static void addToWhiteList(Item item) {
        addToWhiteList(new ItemStack(item));
    }

    public static class OmniplantExternalPlants {

        public static final String cat2 = "core.OmniplantExternalPlant_WhiteList".toLowerCase();

        public static void addToWhiteList(ItemStack seed, ItemStack plant) {
            String l = plant.getItemName() + "_OmniPlantExternal";
            String l2 = plant.itemID + "," + plant.stackSize + "," + plant.getItemDamage() + "," + seed.itemID + "," + seed.getItemDamage();
            PfF.Core.config.get(cat2, l, l2);
            PfF.Core.config.addCustomCategoryComment(cat2, "Syntax: grown item id, grown item amount, grown item meta, seed id, seed meta");
            PfF.Core.config.save();
        }

        public static void registerList() {
            ConfigCategory c = PfF.Core.config.getCategory(cat2);
            for (Property p : c.getValues().values()) {
                String a = p.value;
                String parse[] = a.split(",");
                int plantid = Integer.valueOf(parse[0]);
                int plantamount = Integer.valueOf(parse[1]);
                int plantmeta = Integer.valueOf(parse[2]);
                //-
                int seedid = Integer.valueOf(parse[3]);
                int seedmeta = Integer.valueOf(parse[4]);
                //-
                ItemStack plant = new ItemStack(plantid, plantamount, plantmeta);
                ItemStack seed = new ItemStack(seedid, 1, seedmeta);
                PfFManagers.OmniPlant.registerPlantWithSeed(plant, seed);
            }
        }
    }
}
