package denoflionsx.plugins.Forestry.Utility;

import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidContainer {

    // Wraps LiquidData to make it work like the old LiquidContainer.
    private LiquidContainerData liquid;
    public ItemStack filled;
    public ItemStack empty;

    public LiquidContainer(LiquidStack l, ItemStack filled, ItemStack empty) {
        this.liquid = new LiquidContainerData(l, filled, empty);
        this.filled = this.liquid.filled;
        this.empty = this.liquid.container;
    }

    public LiquidContainer(LiquidStack l, ItemStack filled, ItemStack empty, boolean isBucket) {
        this.liquid = new LiquidContainerData(l, filled, empty);
        this.filled = this.liquid.filled;
        this.empty = this.liquid.container;
    }

    public LiquidContainerData getLiquid() {
        return liquid;
    }

    public static class LiquidManagerWrapper {

        public static void registerLiquidContainer(LiquidContainer l) {
            LiquidContainerRegistry.registerLiquid(l.getLiquid());
        }

        public static LiquidContainer getEmptyContainer(ItemStack i, LiquidStack s) {
            ItemStack r = LiquidContainerRegistry.fillLiquidContainer(s, i);
            return new LiquidContainer(s, r, i);
        }
    }
}
