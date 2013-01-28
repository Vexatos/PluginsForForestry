package denoflionsx.PluginsforForestry.Utils;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class FermenterUtils {

    public static final ArrayList<Fermentable> fermentables = new ArrayList();

    static {
        for (int i = 0; i < 3; i++) {
            fermentables.add(new Fermentable(new ItemStack(Block.sapling, 1, i), (int) (LiquidContainerRegistry.BUCKET_VOLUME * 0.8)));
        }
        fermentables.add(new Fermentable(new ItemStack(Item.wheat), (int) (LiquidContainerRegistry.BUCKET_VOLUME * 0.1)));
        fermentables.add(new Fermentable(new ItemStack(Block.cactus), (int) (LiquidContainerRegistry.BUCKET_VOLUME * 0.2)));
        fermentables.add(new Fermentable(new ItemStack(Item.reed), (int) (LiquidContainerRegistry.BUCKET_VOLUME * 0.2)));
        fermentables.add(new Fermentable(new ItemStack(Block.mushroomBrown), (int) (LiquidContainerRegistry.BUCKET_VOLUME * 0.2)));
        fermentables.add(new Fermentable(new ItemStack(Block.mushroomRed), (int) (LiquidContainerRegistry.BUCKET_VOLUME * 0.2)));
    }

    public static void registerFermenterBooster(LiquidStack liquid, float bonus) {
        for (Fermentable f : fermentables) {
            APIWrappers.forestry.fermenter.addRecipe(f.getStack(), f.getAmount(), bonus, new LiquidStack(ForestryLiquids.BIOMASS.getStack().itemID, 1), liquid);
        }
        LiquidContainerData[] d = LiquidContainerRegistry.getRegisteredLiquidContainerData();
        for (LiquidContainerData q : d) {
            if (q.container.itemID != CoreTuning.Items.barrel && q.container.itemID != Item.bucketEmpty.itemID) {
                if (q.stillLiquid.isLiquidEqual(liquid)) {
                    ItemStack z = LiquidContainerRegistry.fillLiquidContainer(new LiquidStack(ForestryLiquids.APPLEJUICE.getStack().itemID, 1000, ForestryLiquids.APPLEJUICE.getStack().getItemDamage()), q.container);
                    if (z != null) {
                        APIWrappers.forestry.carpenter.addRecipe(5, null, null, z, new Object[]{"QXX", "XXX", "XXX", Character.valueOf('Q'), q.filled});
                    }
                }
            }
        }
    }

    public static class Fermentable {

        private ItemStack stack;
        private int amount;

        public Fermentable(ItemStack stack, int amount) {
            this.stack = stack;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public ItemStack getStack() {
            return stack;
        }
    }
}
