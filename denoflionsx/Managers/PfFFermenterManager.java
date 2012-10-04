package denoflionsx.Managers;

import denoflionsx.API.Interfaces.IPfFFermenterManager;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.plugins.Forestry.Helpers.FermenterHelper;
import java.util.ArrayList;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PfFFermenterManager implements IPfFFermenterManager {

    public static ArrayList<Fermentable> items = new ArrayList();
    public static ArrayList<Liquid> liquids = new ArrayList();
    public static ArrayList<Fermentable> vanillaItems = new ArrayList();
    public static ArrayList<Liquid> vanillaLiquids = new ArrayList();
    public static ArrayList<Liquid> forestryLiquids = new ArrayList();

    public PfFFermenterManager() {
        for (int i = 0; i != 3; i++) {
            vanillaItems.add(new Fermentable(new ItemStack(Block.sapling, 1, i), 800));
        }
        vanillaItems.add(new Fermentable(new ItemStack(Block.cactus), 200));
        vanillaItems.add(new Fermentable(new ItemStack(Item.wheat), 100));
        vanillaItems.add(new Fermentable(new ItemStack(Item.reed), 200));
        vanillaItems.add(new Fermentable(new ItemStack(Block.mushroomBrown), 200));
        vanillaItems.add(new Fermentable(new ItemStack(Block.mushroomRed), 200));

        vanillaLiquids.add(new Liquid(new ItemStack(Block.waterStill), 1.0f));

        forestryLiquids.add(new Liquid(EnumForestryLiquids.APPLEJUICE.getLiquid(), 1.5f));
        forestryLiquids.add(new Liquid(EnumForestryLiquids.HONEY.getLiquid(), 1.5f));
    }

    @Override
    public void addItem(ItemStack item, int bonus, ItemStack liquid) {
        items.add(new Fermentable(item, bonus, liquid));
    }

    @Override
    public void addItem(ItemStack item, int bonus) {
        items.add(new Fermentable(item, bonus));
    }

    @Override
    public void registerPfFLiquid(ItemStack liquid, float bonus) {
        liquids.add(new Liquid(liquid, bonus));
    }

    @Override
    public void createRecipes() {
        // Lots of loops.
        for (Liquid l : liquids) {
            for (Fermentable f : vanillaItems) {
                FermenterHelper.add(f.item, l.liquid, f.bonus, f.product, l.bonus);
            }
            if (!items.isEmpty()) {
                for (Fermentable f : items) {
                    FermenterHelper.add(f.item, l.liquid, f.bonus, f.product, l.bonus);
                }
            }
        }
        for (Liquid l : vanillaLiquids) {
            if (!items.isEmpty()) {
                for (Fermentable f : items) {
                    FermenterHelper.add(f.item, l.liquid, f.bonus, f.product, l.bonus);
                }
            }
        }
        for (Liquid l : forestryLiquids) {
            if (!items.isEmpty()) {
                for (Fermentable f : items) {
                    FermenterHelper.add(f.item, l.liquid, f.bonus, f.product, l.bonus);
                }
            }
        }
    }

    public static class Fermentable {

        private ItemStack item;
        int bonus;
        private ItemStack product;

        public Fermentable(ItemStack item, int bonus) {
            this.item = item;
            this.bonus = bonus;
            this.product = EnumForestryLiquids.BIOMASS.getLiquid();
        }

        public Fermentable(ItemStack item, int bonus, ItemStack product) {
            this.item = item;
            this.bonus = bonus;
            this.product = product;
        }
    }

    public static class Liquid {

        private ItemStack liquid;
        float bonus;

        public Liquid(ItemStack liquid, float bonus) {
            this.liquid = liquid;
            this.bonus = bonus;
        }
    }
}
